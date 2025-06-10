package db;

import model.Figura;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SVGDAO {
    public static boolean exportarSVG(List<Figura> figuras, String rutaArchivo, int width, int height) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">\n", width, height));
        for (Figura figura : figuras) {
            sb.append("  ").append(figura.toSVG()).append("\n");
        }
        sb.append("</svg>");

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(sb.toString());
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar SVG: " + e.getMessage());
            return false;
        }
    }
}
