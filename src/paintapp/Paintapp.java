
package paintapp;

import javax.swing.SwingUtilities;
import view.VentanaPrincipal;

public class Paintapp {

    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new VentanaPrincipal()); 
    }
    
}
