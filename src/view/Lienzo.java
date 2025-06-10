package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Figura;

public class Lienzo extends JPanel {
    private List<Figura> figuras;

    public Lienzo(List<Figura> figuras) {
        this.figuras = figuras;
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figura f : figuras) {
            f.dibujar(g);
        }
    }
}
