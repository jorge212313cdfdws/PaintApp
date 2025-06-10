
package model;

import java.awt.Color;
import java.awt.Graphics;


public class Punto extends Figura{
    private int x, y; 
    
    public Punto(int x, int y, Color borde){
        super(borde, null); 
        this.x = x; 
        this.y = y; 
    }

    public int getX() {
    return x;
}

public int getY() {
    return y;
}

    @Override
    public void dibujar(Graphics g) {
        g.setColor(borde); 
        g.fillOval(x - 2, y - 2, 4, 4);
    }

    @Override
    public String toSVG() {
        return String.format("<circle cx='%d' cy='%d' r='2' fill='rgb(%d,%d,%d)' />",
            x, y,
            borde.getRed(), borde.getGreen(), borde.getBlue()); 
    }
}
