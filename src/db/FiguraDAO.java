package db;

import model.*;
import java.sql.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FiguraDAO {

    public static boolean guardarFigura(Figura figura) {
        String tipo = figura.getClass().getSimpleName(); // Ej: Punto, Linea, etc.
        String datos = serializar(figura);
        String colorBorde = colorToRGB(figura.getBorde());
        String colorRelleno = colorToRGB(figura.getRelleno());

        String sql = "INSERT INTO figura(tipo, datos, colorBorde, colorRelleno) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);
            stmt.setString(2, datos);
            stmt.setString(3, colorBorde);
            stmt.setString(4, colorRelleno);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar figura: " + e.getMessage());
            return false;
        }
    }

    public static List<Figura> leerFiguras() {
        List<Figura> figuras = new ArrayList<>();
        String sql = "SELECT * FROM figura";

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String datos = rs.getString("datos");
                Color borde = rgbToColor(rs.getString("colorBorde"));
                Color relleno = rgbToColor(rs.getString("colorRelleno"));
                Figura f = deserializar(tipo, datos, borde, relleno);
                if (f != null) figuras.add(f);
            }

        } catch (SQLException e) {
            System.err.println("Error al leer figuras: " + e.getMessage());
        }

        return figuras;
    }

    // -------------------- Serializar --------------------

    private static String serializar(Figura figura) {
        if (figura instanceof Punto p)
            return p.getX() + "," + p.getY();

        if (figura instanceof Linea l)
            return l.getX1() + "," + l.getY1() + "," + l.getX2() + "," + l.getY2();

        if (figura instanceof Circulo c)
            return c.getCx() + "," + c.getCy() + "," + c.getRadio();

        if (figura instanceof PoligonoRegular pr)
            return pr.getCx() + "," + pr.getCy() + "," + pr.getRadio() + "," + pr.getLados();

        if (figura instanceof PoligonoIrregular pi) {
            StringBuilder sb = new StringBuilder();
            for (Point pt : pi.getPuntos()) {
                sb.append(pt.x).append("-").append(pt.y).append(";");
            }
            return sb.toString();
        }

        return "";
    }

    // -------------------- Deserializar --------------------

    private static Figura deserializar(String tipo, String datos, Color borde, Color relleno) {
        try {
            switch (tipo) {
                case "Punto":
                    String[] d1 = datos.split(",");
                    return new Punto(Integer.parseInt(d1[0]), Integer.parseInt(d1[1]), borde);

                case "Linea":
                    String[] d2 = datos.split(",");
                    return new Linea(Integer.parseInt(d2[0]), Integer.parseInt(d2[1]),
                                     Integer.parseInt(d2[2]), Integer.parseInt(d2[3]), borde);

                case "Circulo":
                    String[] d3 = datos.split(",");
                    return new Circulo(Integer.parseInt(d3[0]), Integer.parseInt(d3[1]),
                                       Integer.parseInt(d3[2]), borde, relleno);

                case "PoligonoRegular":
                    String[] d4 = datos.split(",");
                    return new PoligonoRegular(Integer.parseInt(d4[0]), Integer.parseInt(d4[1]),
                                               Integer.parseInt(d4[2]), Integer.parseInt(d4[3]), borde, relleno);

                case "PoligonoIrregular":
                    List<Point> puntos = new ArrayList<>();
                    String[] d5 = datos.split(";");
                    for (String p : d5) {
                        if (p.isEmpty()) continue;
                        String[] xy = p.split("-");
                        puntos.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
                    }
                    return new PoligonoIrregular(puntos, borde, relleno);
            }
        } catch (Exception e) {
            System.err.println("Error deserializando figura: " + e.getMessage());
        }

        return null;
    }

    // -------------------- Color Helpers --------------------

    private static String colorToRGB(Color color) {
        if (color == null) return "none";
        return String.format("rgb(%d,%d,%d)", color.getRed(), color.getGreen(), color.getBlue());
    }

    private static Color rgbToColor(String rgb) {
        if (rgb == null || rgb.equals("none")) return null;
        rgb = rgb.replace("rgb(", "").replace(")", "");
        String[] parts = rgb.split(",");
        return new Color(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}
