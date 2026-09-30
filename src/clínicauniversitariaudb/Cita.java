/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clínicauniversitariaudb;

/**
 *
 * @author diego
 */
public class Cita {

    private int codigoCita;
    private int codigoPaciente;
    private int codigoDoctor;
    private String fechaCita;
    private String horaCita;
    private String motivoConsulta;
    private String estadoCita;

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

    public int getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(int codigoCita) {
        this.codigoCita = codigoCita;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getCodigoDoctor() {
        return codigoDoctor;
    }

    public void setCodigoDoctor(int codigoDoctor) {
        this.codigoDoctor = codigoDoctor;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public void registrarCita() {
        System.out.println("Cita #" + codigoCita + " registrada exitosamente para la fecha: " + fechaCita);
    }

    public void cancelarCita() {
        this.estadoCita = "Cancelada";
        System.out.println("La cita #" + codigoCita + " ha sido cancelada.");
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
