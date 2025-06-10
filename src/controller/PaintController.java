package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import model.*;
import view.Lienzo;
import db.FiguraDAO;
import db.SVGDAO;

public class PaintController {
    private JPanel panelPrincipal;
    private Lienzo lienzo;
    private JComboBox<String> selectorFiguras;
    private JComboBox<Integer> selectorLados;
    private Color colorBorde = Color.BLACK;
    private Color colorRelleno = Color.RED;
    private ArrayList<Figura> figuras = new ArrayList<>();
    private int clickCount = 0;
    private int x1, y1;
    private List<Point> puntosIrregulares = new ArrayList<>();

    public PaintController() {
        panelPrincipal = new JPanel(new BorderLayout());
        lienzo = new Lienzo(figuras);

        selectorFiguras = new JComboBox<>(new String[]{"Punto", "Recta", "Circunferencia", "Polígono Regular", "Polígono Irregular"});
        selectorLados = new JComboBox<>(new Integer[]{3, 4, 5, 6, 7, 8});

        // BOTONES
        JButton colorBordeBtn = new JButton("Color Borde");
        JButton colorRellenoBtn = new JButton("Color Relleno");
        JButton limpiarBtn = new JButton("Limpiar");
        JButton cerrarPoligonoIrregularBtn = new JButton("Cerrar Polígono");
        JButton guardarBtn = new JButton("Guardar figura");
        JButton cargarBtn = new JButton("Cargar figuras");
        JButton exportarBtn = new JButton("Exportar SVG");

        // ACCIONES DE BOTONES DE COLOR
        colorBordeBtn.addActionListener(e -> {
            Color nuevoColor = JColorChooser.showDialog(null, "Color de borde", colorBorde);
            if (nuevoColor != null) colorBorde = nuevoColor;
        });

        colorRellenoBtn.addActionListener(e -> {
            Color nuevoColor = JColorChooser.showDialog(null, "Color de relleno", colorRelleno);
            if (nuevoColor != null) colorRelleno = nuevoColor;
        });

        // LIMPIAR LIENZO
        limpiarBtn.addActionListener(e -> {
            figuras.clear();
            puntosIrregulares.clear();
            lienzo.repaint();
        });

        // CERRAR POLÍGONO IRREGULAR
        cerrarPoligonoIrregularBtn.addActionListener(e -> {
            if (puntosIrregulares.size() >= 3) {
                figuras.add(new PoligonoIrregular(new ArrayList<>(puntosIrregulares), colorBorde, colorRelleno));
                puntosIrregulares.clear();
                lienzo.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar al menos 3 puntos.");
            }
        });

        // GUARDAR FIGURA EN BD
        guardarBtn.addActionListener(e -> {
            if (!figuras.isEmpty()) {
                Figura ultima = figuras.get(figuras.size() - 1);
                boolean ok = FiguraDAO.guardarFigura(ultima);
                if (ok) {
                    JOptionPane.showMessageDialog(null, "Figura guardada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar la figura.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay figuras para guardar.");
            }
        });

        // CARGAR FIGURAS DE BD
        cargarBtn.addActionListener(e -> {
            figuras.clear();
            figuras.addAll(FiguraDAO.leerFiguras());
            lienzo.repaint();
        });

        // EXPORTAR A SVG
        exportarBtn.addActionListener(e -> {
            boolean ok = SVGDAO.exportarSVG(figuras, "exportado.svg", 600, 400);
            if (ok) {
                JOptionPane.showMessageDialog(null, "Archivo SVG exportado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al exportar SVG.");
            }
        });

        // PANEL DE CONTROLES
        JPanel controles = new JPanel();
        controles.add(selectorFiguras);
        controles.add(new JLabel("Lados:"));
        controles.add(selectorLados);
        controles.add(colorBordeBtn);
        controles.add(colorRellenoBtn);
        controles.add(limpiarBtn);
        controles.add(cerrarPoligonoIrregularBtn);
        controles.add(guardarBtn);
        controles.add(cargarBtn);
        controles.add(exportarBtn);

        // DETECCIÓN DE CLICS EN EL LIENZO
        lienzo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String tipo = (String) selectorFiguras.getSelectedItem();

                switch (tipo) {
                    case "Punto":
                        figuras.add(new Punto(e.getX(), e.getY(), colorBorde));
                        break;

                    case "Recta":
                        if (clickCount == 0) {
                            x1 = e.getX();
                            y1 = e.getY();
                            clickCount = 1;
                        } else {
                            figuras.add(new Linea(x1, y1, e.getX(), e.getY(), colorBorde));
                            clickCount = 0;
                        }
                        break;

                    case "Circunferencia":
                        if (clickCount == 0) {
                            x1 = e.getX();
                            y1 = e.getY();
                            clickCount = 1;
                        } else {
                            int dx = e.getX() - x1;
                            int dy = e.getY() - y1;
                            int r = (int) Math.sqrt(dx * dx + dy * dy);
                            figuras.add(new Circulo(x1, y1, r, colorBorde, colorRelleno));
                            clickCount = 0;
                        }
                        break;

                    case "Polígono Regular":
                        if (clickCount == 0) {
                            x1 = e.getX();
                            y1 = e.getY();
                            clickCount = 1;
                        } else {
                            int dx = e.getX() - x1;
                            int dy = e.getY() - y1;
                            int r = (int) Math.sqrt(dx * dx + dy * dy);
                            int lados = (int) selectorLados.getSelectedItem();
                            figuras.add(new PoligonoRegular(x1, y1, r, lados, colorBorde, colorRelleno));
                            clickCount = 0;
                        }
                        break;

                    case "Polígono Irregular":
                        puntosIrregulares.add(new Point(e.getX(), e.getY()));
                        break;
                }

                lienzo.repaint();
            }
        });

        panelPrincipal.add(controles, BorderLayout.NORTH);
        panelPrincipal.add(lienzo, BorderLayout.CENTER);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}
