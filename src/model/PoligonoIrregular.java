package model;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class PoligonoIrregular extends Figura {
    private List<Point> vertices;

    public PoligonoIrregular(List<Point> vertices, Color borde, Color relleno) {
        super(borde, relleno);
        this.vertices = new ArrayList<>(vertices);
    }

    @Override
    public void dibujar(Graphics g) {
        if (vertices.size() < 2) return;

        int[] x = new int[vertices.size()];
        int[] y = new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            x[i] = vertices.get(i).x;
            y[i] = vertices.get(i).y;
        }

        if (relleno != null) {
            g.setColor(relleno);
            g.fillPolygon(x, y, vertices.size());
        }
        g.setColor(borde);
        g.drawPolygon(x, y, vertices.size());
    }

    @Override
    public String toSVG() {
        if (vertices.size() < 3) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("<polygon points='");
        for (Point p : vertices) {
            sb.append(p.x).append(",").append(p.y).append(" ");
        }
        String fill = relleno != null
                ? String.format("rgb(%d,%d,%d)", relleno.getRed(), relleno.getGreen(), relleno.getBlue())
                : "none";

        sb.append("' stroke='rgb(")
          .append(borde.getRed()).append(",")
          .append(borde.getGreen()).append(",")
          .append(borde.getBlue()).append(")' fill='")
          .append(fill).append("' stroke-width='2'/>");

        return sb.toString();
    }

    public Iterable<Point> getPuntos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
