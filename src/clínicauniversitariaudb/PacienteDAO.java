package clínicauniversitariaudb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void insertar(Paciente p) throws SQLException {
        String sql = "INSERT INTO pacientes (nombreCompleto, edad, telefono, correoElectronico, tipoPaciente, estadoPaciente) VALUES (?,?,?,?,?,?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombreCompleto());
            ps.setInt(2, p.getEdad());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getCorreoElectronico());
            ps.setString(5, p.getTipoPaciente());
            ps.setString(6, p.getEstadoPaciente());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setCodigoPaciente(rs.getInt(1));
            }
        }
    }

    public void actualizar(Paciente p) throws SQLException {
        String sql = "UPDATE pacientes SET nombreCompleto=?, edad=?, telefono=?, correoElectronico=?, tipoPaciente=?, estadoPaciente=? WHERE codigoPaciente=?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombreCompleto());
            ps.setInt(2, p.getEdad());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getCorreoElectronico());
            ps.setString(5, p.getTipoPaciente());
            ps.setString(6, p.getEstadoPaciente());
            ps.setInt(7, p.getCodigoPaciente());
            ps.executeUpdate();
        }
    }

    public Paciente buscarPorCodigo(int codigo) throws SQLException, PacienteNoEncontradoException {
        String sql = "SELECT * FROM pacientes WHERE codigoPaciente = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        throw new PacienteNoEncontradoException("Paciente no encontrado, no se puede Mostrar");
    }

    public List<Paciente> listarTodos() throws SQLException {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Paciente mapear(ResultSet rs) throws SQLException {
        return new Paciente(
            rs.getInt("codigoPaciente"), rs.getString("nombreCompleto"), rs.getInt("edad"),
            rs.getString("telefono"), rs.getString("correoElectronico"),
            rs.getString("tipoPaciente"), rs.getString("estadoPaciente")
        );
    }
}