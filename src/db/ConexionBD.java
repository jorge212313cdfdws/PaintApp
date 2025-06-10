package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:sqlite:paint.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("No se pudo conectar a la BD: " + e.getMessage());
            return null;
        }
    }
}
