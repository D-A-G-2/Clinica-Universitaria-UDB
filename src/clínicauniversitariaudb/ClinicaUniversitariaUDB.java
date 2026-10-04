package clínicauniversitariaudb;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Dimension;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class ClinicaUniversitariaUDB {

    static PacienteDAO pacienteDAO = new PacienteDAO();
    static DoctorDAO doctorDAO = new DoctorDAO();
    static CitaDAO citaDAO = new CitaDAO();

    static String[] opcionesMenu = {
        "Registrar Paciente", "Registrar Doctor", "Agendar Cita",
        "Consultar Datos Registrados", "Cancelar Cita",
        "Modificar Paciente", "Modificar Doctor", "Generar Reporte", "Salir"
    };

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Sistema de Gestión de Citas - Clínica UDB",
                "Menú Principal",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesMenu,
                opcionesMenu[0]
            );

            if (seleccion == null) break; 

            try {
                switch (seleccion) {
                    case "Registrar Paciente": registrarPaciente(); break;
                    case "Registrar Doctor": registrarDoctor(); break;
                    case "Agendar Cita": agendarCita(); break;
                    case "Consultar Datos Registrados": consultarTodo(); break;
                    case "Cancelar Cita": cancelarCita(); break;
                    case "Modificar Paciente": modificarPaciente(); break;
                    case "Modificar Doctor": modificarDoctor(); break;
                    case "Generar Reporte": generarReporte(); break;
                    case "Salir": salir = true; break;
                }
            } catch (CancelacionException e) {
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (PacienteNoEncontradoException | DoctorNoEncontradoException
                     | CitaNoEncontradaException | HorarioNoDisponibleException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar solo números donde corresponda.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha/hora inválido. Use yyyy-MM-dd y HH:mm:ss.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
    }


    private static String pedir(String mensaje) {
        String valor = JOptionPane.showInputDialog(mensaje);
        if (valor == null) throw new CancelacionException();
        return valor.trim();
    }

    private static String elegirOpcion(String titulo, String[] opciones) {
        String seleccion = (String) JOptionPane.showInputDialog(
            null, titulo, titulo, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]
        );
        if (seleccion == null) throw new CancelacionException();
        return seleccion;
    }

    private static void mostrarPanelTexto(String titulo, String contenido) {
        JTextArea area = new JTextArea(contenido);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(520, 400));
        JOptionPane.showMessageDialog(null, scroll, titulo, JOptionPane.PLAIN_MESSAGE);
    }


    private static void registrarPaciente() throws SQLException {
        String nombre = pedir("Nombre completo:");
        int edad = Integer.parseInt(pedir("Edad:"));
        String tel = pedir("Teléfono:");
        String correo = pedir("Correo electrónico:");
        String tipo = elegirOpcion("Tipo de paciente", new String[]{"Estudiante", "Docente", "Administrativo", "Visitante"});

        Paciente p = new Paciente(nombre, edad, tel, correo, tipo, "Activo");
        pacienteDAO.insertar(p);
        p.registrarPaciente();
        JOptionPane.showMessageDialog(null, "Paciente registrado con éxito. Código: " + p.getCodigoPaciente());
    }

    private static void registrarDoctor() throws SQLException {
        String nombre = pedir("Nombre completo:");
        int edad = Integer.parseInt(pedir("Edad:"));
        String tel = pedir("Teléfono:");
        String correo = pedir("Correo electrónico:");
        String especialidad = elegirOpcion("Especialidad", new String[]{"Medicina General", "Psicología", "Nutrición", "Fisioterapia"});

        Doctor d = new Doctor(nombre, edad, tel, correo, especialidad, "Disponible");
        doctorDAO.insertar(d);
        d.registrarDoctor();
        JOptionPane.showMessageDialog(null, "Doctor registrado con éxito. Código: " + d.getCodigoDoctor());
    }

    private static void agendarCita() throws SQLException, PacienteNoEncontradoException,
            DoctorNoEncontradoException, HorarioNoDisponibleException {
        int codP = Integer.parseInt(pedir("Código del paciente:"));
        Paciente paciente = pacienteDAO.buscarPorCodigo(codP);

        int codD = Integer.parseInt(pedir("Código del doctor:"));
        Doctor doctor = doctorDAO.buscarPorCodigo(codD);

        String fecha = pedir("Fecha de la cita (yyyy-MM-dd):");
        String hora = pedir("Hora de la cita (HH:mm:ss):");
        String motivo = pedir("Motivo de consulta:");

        Cita cita = new Cita(paciente.getCodigoPaciente(), doctor.getCodigoDoctor(), fecha, hora, motivo, "Programada");
        citaDAO.insertar(cita); 
        cita.registrarCita();
        JOptionPane.showMessageDialog(null, "Cita #" + cita.getCodigoCita() + " agendada exitosamente.");
    }

    private static void consultarTodo() throws SQLException {
        StringBuilder sb = new StringBuilder();

        sb.append("--- PACIENTES ---\n");
        List<Paciente> pacientes = pacienteDAO.listarTodos();
        if (pacientes.isEmpty()) sb.append("No hay pacientes registrados.\n");
        else for (Paciente p : pacientes) sb.append(String.format(
            "Código: %d | %s | Edad: %d | Tel: %s | %s | Tipo: %s | Estado: %s%n",
            p.getCodigoPaciente(), p.getNombreCompleto(), p.getEdad(), p.getTelefono(),
            p.getCorreoElectronico(), p.getTipoPaciente(), p.getEstadoPaciente()));

        sb.append("\n--- DOCTORES ---\n");
        List<Doctor> doctores = doctorDAO.listarTodos();
        if (doctores.isEmpty()) sb.append("No hay doctores registrados.\n");
        else for (Doctor d : doctores) sb.append(String.format(
            "Código: %d | %s | Esp: %s | Tel: %s | %s | Estado: %s%n",
            d.getCodigoDoctor(), d.getNombreCompleto(), d.getEspecialidad(), d.getTelefono(),
            d.getCorreoElectronico(), d.getEstadoDoctor()));

        sb.append("\n--- CITAS ---\n");
        List<Cita> citas = citaDAO.listarTodas();
        if (citas.isEmpty()) sb.append("No hay citas registradas.\n");
        else for (Cita c : citas) sb.append(String.format(
            "Código: %d | Paciente: %d | Doctor: %d | %s %s | %s | Estado: %s%n",
            c.getCodigoCita(), c.getCodigoPaciente(), c.getCodigoDoctor(),
            c.getFechaCita(), c.getHoraCita(), c.getMotivoConsulta(), c.getEstadoCita()));

        mostrarPanelTexto("Datos Registrados", sb.toString());
    }

    private static void cancelarCita() throws SQLException, CitaNoEncontradaException {
        int codC = Integer.parseInt(pedir("Código de la cita a cancelar:"));
        citaDAO.cancelar(codC);
        JOptionPane.showMessageDialog(null, "La cita #" + codC + " ha sido cancelada.");
    }

    private static void modificarPaciente() throws SQLException, PacienteNoEncontradoException {
        int cod = Integer.parseInt(pedir("Código del paciente a modificar:"));
        Paciente p = pacienteDAO.buscarPorCodigo(cod);

        String tel = JOptionPane.showInputDialog("Nuevo teléfono (" + p.getTelefono() + "), deje vacío para no cambiar:");
        String correo = JOptionPane.showInputDialog("Nuevo correo (" + p.getCorreoElectronico() + "), deje vacío para no cambiar:");
        String estado = elegirOpcion("Nuevo estado", new String[]{"Activo", "Inactivo"});

        if (tel != null && !tel.isBlank()) p.setTelefono(tel);
        if (correo != null && !correo.isBlank()) p.setCorreoElectronico(correo);
        p.setEstadoPaciente(estado);

        pacienteDAO.actualizar(p);
        p.modificarPaciente();
        JOptionPane.showMessageDialog(null, "Datos del paciente actualizados.");
    }

    private static void modificarDoctor() throws SQLException, DoctorNoEncontradoException {
        int cod = Integer.parseInt(pedir("Código del doctor a modificar:"));
        Doctor d = doctorDAO.buscarPorCodigo(cod);

        String tel = JOptionPane.showInputDialog("Nuevo teléfono (" + d.getTelefono() + "), deje vacío para no cambiar:");
        String correo = JOptionPane.showInputDialog("Nuevo correo (" + d.getCorreoElectronico() + "), deje vacío para no cambiar:");
        String estado = elegirOpcion("Nueva disponibilidad", new String[]{"Disponible", "No Disponible"});

        if (tel != null && !tel.isBlank()) d.setTelefono(tel);
        if (correo != null && !correo.isBlank()) d.setCorreoElectronico(correo);
        d.setEstadoDoctor(estado);

        doctorDAO.actualizar(d);
        d.modificarDoctor();
        JOptionPane.showMessageDialog(null, "Datos del doctor actualizados.");
    }

    private static void generarReporte() throws SQLException {
        String tipo = elegirOpcion("Tipo de reporte", new String[]{"Citas por estado"});
        Reporte reporte = new Reporte((int) (Math.random() * 9000) + 1000, tipo);

        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            reporte.generarReporte();
        } finally {
            System.setOut(original);
        }
        mostrarPanelTexto("Reporte", baos.toString());
    }
}