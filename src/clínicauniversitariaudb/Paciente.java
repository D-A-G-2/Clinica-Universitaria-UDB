/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clínicauniversitariaudb;

/**
 *
 * @author diego
 */
public class Paciente extends Persona {
    
    private int codigoPaciente;
    private String tipoPaciente;
    private String estadoPaciente;

    public Paciente(String nombreCompleto, int edad, String telefono, String correoElectronico,
                    int codigoPaciente, String tipoPaciente, String estadoPaciente) {
        
        super(nombreCompleto, edad, telefono, correoElectronico);
        
        this.codigoPaciente = codigoPaciente;
        this.tipoPaciente = tipoPaciente;
        this.estadoPaciente = estadoPaciente;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(String tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

    public String getEstadoPaciente() {
        return estadoPaciente;
    }

    public void setEstadoPaciente(String estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }

    public void registrarPaciente() {
        System.out.println("Paciente registrado con éxito: " + getNombreCompleto());
    }

    public void modificarPaciente() {
        System.out.println("Datos del paciente actualizados.");
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