package clínicauniversitariaudb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void insertar(Doctor d) throws SQLException {
        String sql = "INSERT INTO doctores (nombreCompleto, especialidad, telefono, correoElectronico, estadoDoctor) VALUES (?,?,?,?,?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getNombreCompleto());
            ps.setString(2, d.getEspecialidad());
            ps.setString(3, d.getTelefono());
            ps.setString(4, d.getCorreoElectronico());
            ps.setString(5, d.getEstadoDoctor());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) d.setCodigoDoctor(rs.getInt(1));
            }
        }
    }

    public void actualizar(Doctor d) throws SQLException {
        String sql = "UPDATE doctores SET nombreCompleto=?, especialidad=?, telefono=?, correoElectronico=?, estadoDoctor=? WHERE codigoDoctor=?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getNombreCompleto());
            ps.setString(2, d.getEspecialidad());
            ps.setString(3, d.getTelefono());
            ps.setString(4, d.getCorreoElectronico());
            ps.setString(5, d.getEstadoDoctor());
            ps.setInt(6, d.getCodigoDoctor());
            ps.executeUpdate();
        }
    }

    public Doctor buscarPorCodigo(int codigo) throws SQLException, DoctorNoEncontradoException {
        String sql = "SELECT * FROM doctores WHERE codigoDoctor = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        throw new DoctorNoEncontradoException("Doctor no encontrado, no se puede Mostrar");
    }

    public List<Doctor> listarTodos() throws SQLException {
        List<Doctor> lista = new ArrayList<>();
        String sql = "SELECT * FROM doctores";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Doctor mapear(ResultSet rs) throws SQLException {
        return new Doctor(
            rs.getInt("codigoDoctor"), rs.getString("nombreCompleto"), 0,
            rs.getString("telefono"), rs.getString("correoElectronico"),
            rs.getString("especialidad"), rs.getString("estadoDoctor")
        );
    }
}