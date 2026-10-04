package clínicauniversitariaudb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/clinicavidaudb";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = ""; // pon aquí tu contraseña real de MySQL

    private ConexionBD() { }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
