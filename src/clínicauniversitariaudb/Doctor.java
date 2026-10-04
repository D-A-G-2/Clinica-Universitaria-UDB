package clínicauniversitariaudb;

public class Doctor extends Persona {

    private int codigoDoctor;
    private String especialidad;
    private String estadoDoctor;

    
    
    public void modificarDoctor() {
    System.out.println("Datos del doctor actualizados: " + getNombreCompleto());
    }
    
    public Doctor(String nombreCompleto, int edad, String telefono, String correoElectronico,
                  String especialidad, String estadoDoctor) {
        super(nombreCompleto, edad, telefono, correoElectronico);
        this.especialidad = especialidad;
        this.estadoDoctor = estadoDoctor;
    }

    public Doctor(int codigoDoctor, String nombreCompleto, int edad, String telefono, String correoElectronico,
                  String especialidad, String estadoDoctor) {
        this(nombreCompleto, edad, telefono, correoElectronico, especialidad, estadoDoctor);
        this.codigoDoctor = codigoDoctor;
    }

    public int getCodigoDoctor() { return codigoDoctor; }
    public void setCodigoDoctor(int codigoDoctor) { this.codigoDoctor = codigoDoctor; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getEstadoDoctor() { return estadoDoctor; }
    public void setEstadoDoctor(String estadoDoctor) { this.estadoDoctor = estadoDoctor; }

    public void registrarDoctor() {
        System.out.println("Doctor registrado con éxito. Código: " + codigoDoctor + " — Dr(a). " + getNombreCompleto());
    }

    public void consultarDoctor() {
        System.out.println("--- DATOS DEL DOCTOR ---");
        System.out.println("Código: " + codigoDoctor);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Teléfono: " + getTelefono());
        System.out.println("Correo: " + getCorreoElectronico());
        System.out.println("Estado: " + estadoDoctor);
    }
}