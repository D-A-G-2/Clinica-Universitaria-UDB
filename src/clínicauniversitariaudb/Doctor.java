/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clínicauniversitariaudb;

/**
 *
 * @author diego
 */
public class Doctor extends Persona {

    private int codigoDoctor;
    private String especialidad;
    private String estadoDoctor;

    public Doctor(String nombreCompleto, int edad, String telefono, String correoElectronico,
                  int codigoDoctor, String especialidad, String estadoDoctor) {
        
        super(nombreCompleto, edad, telefono, correoElectronico);
        
        this.codigoDoctor = codigoDoctor;
        this.especialidad = especialidad;
        this.estadoDoctor = estadoDoctor;
    }

    public int getCodigoDoctor() {
        return codigoDoctor;
    }

    public void setCodigoDoctor(int codigoDoctor) {
        this.codigoDoctor = codigoDoctor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEstadoDoctor() {
        return estadoDoctor;
    }

    public void setEstadoDoctor(String estadoDoctor) {
        this.estadoDoctor = estadoDoctor;
    }

    public void registrarDoctor() {
        System.out.println("Doctor registrado con éxito: Dr(a). " + getNombreCompleto());
    }

    public void modificarDoctor() {
        System.out.println("Datos del doctor actualizados.");
    }

    public void consultarDoctor() {
        System.out.println("--- DATOS DEL DOCTOR ---");
        System.out.println("Código Doctor: " + codigoDoctor);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Teléfono: " + getTelefono());
        System.out.println("Correo: " + getCorreoElectronico());
        System.out.println("Estado: " + estadoDoctor);
    }
}
