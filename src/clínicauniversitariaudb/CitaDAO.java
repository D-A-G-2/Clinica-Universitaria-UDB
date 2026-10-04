package clínicauniversitariaudb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public boolean verificarDisponibilidad(int codigoDoctor, String fechaCita, String horaCita) throws SQLException {
        String sql = "SELECT COUNT(*) FROM citasmedicas WHERE codigoDoctor = ? AND fechaCita = ? AND horaCita = ? AND estadoCita <> 'Cancelada'";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoDoctor);
            ps.setDate(2, java.sql.Date.valueOf(fechaCita));
            ps.setTime(3, java.sql.Time.valueOf(horaCita));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) == 0;
            }
        }
        return true;
    }

    public void insertar(Cita c) throws SQLException, HorarioNoDisponibleException {
        if (!verificarDisponibilidad(c.getCodigoDoctor(), c.getFechaCita(), c.getHoraCita())) {
            throw new HorarioNoDisponibleException("El doctor ya tiene una cita programada en esa fecha y hora.");
        }
        String sql = "INSERT INTO citasmedicas (codigoPaciente, codigoDoctor, fechaCita, horaCita, motivoConsulta, estadoCita) VALUES (?,?,?,?,?,?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getCodigoPaciente());
            ps.setInt(2, c.getCodigoDoctor());
            ps.setDate(3, java.sql.Date.valueOf(c.getFechaCita()));
            ps.setTime(4, java.sql.Time.valueOf(c.getHoraCita()));
            ps.setString(5, c.getMotivoConsulta());
            ps.setString(6, c.getEstadoCita());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setCodigoCita(rs.getInt(1));
            }
        }
    }

    public void cancelar(int codigoCita) throws SQLException, CitaNoEncontradaException {
        String sql = "UPDATE citasmedicas SET estadoCita = 'Cancelada' WHERE codigoCita = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoCita);
            if (ps.executeUpdate() == 0) {
                throw new CitaNoEncontradaException("Cita no encontrada, no se puede cancelar.");
            }
        }
    }

    public void reprogramar(int codigoCita, String nuevaFecha, String nuevaHora) throws SQLException, CitaNoEncontradaException {
        String sql = "UPDATE citasmedicas SET fechaCita = ?, horaCita = ? WHERE codigoCita = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(nuevaFecha));
            ps.setTime(2, java.sql.Time.valueOf(nuevaHora));
            ps.setInt(3, codigoCita);
            if (ps.executeUpdate() == 0) {
                throw new CitaNoEncontradaException("Cita no encontrada, no se puede reprogramar.");
            }
        }
    }

    public List<Cita> listarTodas() throws SQLException {
        return consultar("SELECT * FROM citasmedicas", null);
    }

    public List<Cita> listarPorPaciente(int codigoPaciente) throws SQLException {
        return consultar("SELECT * FROM citasmedicas WHERE codigoPaciente = ?", codigoPaciente);
    }

    public List<Cita> listarPorDoctor(int codigoDoctor) throws SQLException {
        return consultar("SELECT * FROM citasmedicas WHERE codigoDoctor = ?", codigoDoctor);
    }

    private List<Cita> consultar(String sql, Integer parametro) throws SQLException {
        List<Cita> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (parametro != null) ps.setInt(1, parametro);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Cita(
                        rs.getInt("codigoCita"), rs.getInt("codigoPaciente"), rs.getInt("codigoDoctor"),
                        rs.getDate("fechaCita").toString(), rs.getTime("horaCita").toString(),
                        rs.getString("motivoConsulta"), rs.getString("estadoCita")
                    ));
                }
            }
        }
        return lista;
    }
}