package clínicauniversitariaudb;

public class Paciente extends Persona {

    private int codigoPaciente;
    private String tipoPaciente;
    private String estadoPaciente;

    public void modificarPaciente() {
    System.out.println("Datos del paciente actualizados: " + getNombreCompleto());
    }
    
    public Paciente(String nombreCompleto, int edad, String telefono, String correoElectronico,
                     String tipoPaciente, String estadoPaciente) {
        super(nombreCompleto, edad, telefono, correoElectronico);
        this.tipoPaciente = tipoPaciente;
        this.estadoPaciente = estadoPaciente;
    }

   
    public Paciente(int codigoPaciente, String nombreCompleto, int edad, String telefono, String correoElectronico,
                     String tipoPaciente, String estadoPaciente) {
        this(nombreCompleto, edad, telefono, correoElectronico, tipoPaciente, estadoPaciente);
        this.codigoPaciente = codigoPaciente;
    }

    public int getCodigoPaciente() { return codigoPaciente; }
    public void setCodigoPaciente(int codigoPaciente) { this.codigoPaciente = codigoPaciente; }

    public String getTipoPaciente() { return tipoPaciente; }
    public void setTipoPaciente(String tipoPaciente) { this.tipoPaciente = tipoPaciente; }

    public String getEstadoPaciente() { return estadoPaciente; }
    public void setEstadoPaciente(String estadoPaciente) { this.estadoPaciente = estadoPaciente; }

    public void registrarPaciente() {
        System.out.println("Paciente registrado con éxito. Código: " + codigoPaciente + " — " + getNombreCompleto());
    }

    public void consultarPaciente() {
        System.out.println("--- DATOS DEL PACIENTE ---");
        System.out.println("Código: " + codigoPaciente);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Edad: " + getEdad());
        System.out.println("Teléfono: " + getTelefono());
        System.out.println("Correo: " + getCorreoElectronico());
        System.out.println("Tipo: " + tipoPaciente);
        System.out.println("Estado: " + estadoPaciente);
    }
}