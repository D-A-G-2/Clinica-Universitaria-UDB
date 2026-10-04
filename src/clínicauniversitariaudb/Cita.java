package clínicauniversitariaudb;

public class Cita {

    private int codigoCita;
    private int codigoPaciente;
    private int codigoDoctor;
    private String fechaCita;
    private String horaCita;
    private String motivoConsulta;
    private String estadoCita;

    public Cita(int codigoPaciente, int codigoDoctor, String fechaCita, String horaCita,
                String motivoConsulta, String estadoCita) {
        this(0, codigoPaciente, codigoDoctor, fechaCita, horaCita, motivoConsulta, estadoCita);
    }

    public Cita(int codigoCita, int codigoPaciente, int codigoDoctor,
                String fechaCita, String horaCita, String motivoConsulta, String estadoCita) {
        this.codigoCita = codigoCita;
        this.codigoPaciente = codigoPaciente;
        this.codigoDoctor = codigoDoctor;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.motivoConsulta = motivoConsulta;
        this.estadoCita = estadoCita;
    }

    public int getCodigoCita() { return codigoCita; }
    public void setCodigoCita(int codigoCita) { this.codigoCita = codigoCita; }
    public int getCodigoPaciente() { return codigoPaciente; }
    public int getCodigoDoctor() { return codigoDoctor; }
    public String getFechaCita() { return fechaCita; }
    public String getHoraCita() { return horaCita; }
    public String getMotivoConsulta() { return motivoConsulta; }
    public String getEstadoCita() { return estadoCita; }
    public void setEstadoCita(String estadoCita) { this.estadoCita = estadoCita; }

    public void registrarCita() {
        System.out.println("Cita #" + codigoCita + " registrada para la fecha: " + fechaCita);
    }

    public void consultarCita() {
        System.out.println("--- DETALLE DE LA CITA ---");
        System.out.println("Código Cita: " + codigoCita);
        System.out.println("Código Paciente: " + codigoPaciente);
        System.out.println("Código Doctor: " + codigoDoctor);
        System.out.println("Fecha: " + fechaCita + " | Hora: " + horaCita);
        System.out.println("Motivo: " + motivoConsulta);
        System.out.println("Estado: " + estadoCita);
    }
}