package model;
import java.awt.*;
public class PoligonoRegular extends Figura {
    private int cx, cy, radio, lados;

    public PoligonoRegular(int cx, int cy, int radio, int lados, Color borde, Color relleno) {
        super(borde, relleno);
        this.cx = cx;
        this.cy = cy;
        this.radio = radio;
        this.lados = lados;
    }

    @Override
    public void dibujar(Graphics g) {
        int[] x = new int[lados];
        int[] y = new int[lados];
        double anguloInicial = -Math.PI / 2; // empieza hacia arriba

        for (int i = 0; i < lados; i++) {
            double angulo = anguloInicial + i * 2 * Math.PI / lados;
            x[i] = (int) (cx + radio * Math.cos(angulo));
            y[i] = (int) (cy + radio * Math.sin(angulo));
        }

        if (relleno != null) {
            g.setColor(relleno);
            g.fillPolygon(x, y, lados);
        }
        g.setColor(borde);
        g.drawPolygon(x, y, lados);
    }

    @Override
    public String toSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<polygon points='");
        double anguloInicial = -Math.PI / 2;

        for (int i = 0; i < lados; i++) {
            double angulo = anguloInicial + i * 2 * Math.PI / lados;
            int px = (int) (cx + radio * Math.cos(angulo));
            int py = (int) (cy + radio * Math.sin(angulo));
            sb.append(px).append(",").append(py).append(" ");
        }

        String fill = (relleno != null) ? String.format("rgb(%d,%d,%d)", relleno.getRed(), relleno.getGreen(), relleno.getBlue()) : "none";

        sb.append("' stroke='rgb(")
          .append(borde.getRed()).append(",")
          .append(borde.getGreen()).append(",")
          .append(borde.getBlue()).append(")' fill='")
          .append(fill).append("' stroke-width='2'/>");

        return sb.toString();
    }

    public String getCx() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getCy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getRadio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getLados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
