
package model;

import java.awt.Color;
import java.awt.Graphics;


public abstract class Figura {
    protected Color borde;
    protected Color relleno; 
    
    public Figura(Color borde, Color relleno){
        this.borde = borde; 
        this.relleno = relleno; 
    }
    
    public abstract void dibujar(Graphics g); 
    public abstract String toSVG(); 

    public Color getBorde() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Color getRelleno() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
