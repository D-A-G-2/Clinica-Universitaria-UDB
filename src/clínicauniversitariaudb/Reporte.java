package clínicauniversitariaudb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Reporte {

    private int idReporte;
    private String tipo;
    private LocalDate fechaGeneracion;

    public Reporte(int idReporte, String tipo) {
        this.idReporte = idReporte;
        this.tipo = tipo;
        this.fechaGeneracion = LocalDate.now();
    }

    public int getIdReporte() { return idReporte; }
    public String getTipo() { return tipo; }
    public LocalDate getFechaGeneracion() { return fechaGeneracion; }

    // Usa información real de la tabla citasmedicas (relación "utiliza información de" del diagrama)
    public void generarReporte() throws SQLException {
        System.out.println("\n=== REPORTE #" + idReporte + " - " + tipo + " (" + fechaGeneracion + ") ===");
        String sql = "SELECT estadoCita, COUNT(*) AS total FROM citasmedicas GROUP BY estadoCita";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            boolean hayDatos = false;
            while (rs.next()) {
                hayDatos = true;
                System.out.println(rs.getString("estadoCita") + ": " + rs.getInt("total") + " cita(s)");
            }
            if (!hayDatos) System.out.println("No hay citas registradas todavía.");
        }
    }
}