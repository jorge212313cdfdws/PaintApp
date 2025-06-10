package model;
import java.awt.*;
public class Linea extends Figura {
    private int x1, y1, x2, y2;

    public Linea(int x1, int y1, int x2, int y2, Color borde) {
        super(borde, null);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Linea(Color borde, Color relleno) {
        super(borde, relleno);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(borde);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public String toSVG() {
        return String.format(
                "<line x1='%d' y1='%d' x2='%d' y2='%d' stroke='rgb(%d,%d,%d)' stroke-width='2' />",
                x1, y1, x2, y2,
                borde.getRed(), borde.getGreen(), borde.getBlue()
        );
    }

    public String getX1() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getY1() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getX2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getY2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
