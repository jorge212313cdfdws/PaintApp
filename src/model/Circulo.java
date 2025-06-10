package model;
import java.awt.Color;
import java.awt.Graphics;

public class Circulo extends Figura{
    private int cx, cy, radio; 

    public Circulo(int cx, int cy, int radio, Color borde, Color relleno) {
        super(borde, relleno);
        this.cx = cx;
        this.cy = cy;
        this.radio = radio;
    }

    @Override
    public void dibujar(Graphics g) {
        if (relleno != null) {
            g.setColor(relleno);
            g.fillOval(cx - radio, cy - radio, 2 * radio, 2 * radio);
        }
        g.setColor(borde);
        g.drawOval(cx - radio, cy - radio, 2 * radio, 2 * radio);
    }

    @Override
    public String toSVG() {
        String fill = relleno != null
                ? String.format("rgb(%d,%d,%d)", relleno.getRed(), relleno.getGreen(), relleno.getBlue())
                : "none";

        return String.format(
                "<circle cx='%d' cy='%d' r='%d' stroke='rgb(%d,%d,%d)' fill='%s' stroke-width='2' />",
                cx, cy, radio,
                borde.getRed(), borde.getGreen(), borde.getBlue(),
                fill
        );
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
}
