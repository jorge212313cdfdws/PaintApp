package view;

import javax.swing.*;
import controller.PaintController;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Paint App");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        PaintController controller = new PaintController();
        add(controller.getPanelPrincipal());

        setVisible(true);
    }
}
