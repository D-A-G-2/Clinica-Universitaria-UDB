/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clínicauniversitariaudb;

import java.util.Scanner;
/**
 *
 * @author diego
 */
public class ClinicaUniversitariaUDB {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        Paciente pacienteActual = null;
        Doctor doctorActual = null;
        Cita citaActual = null;

        int opcion = 0;

        do {
            System.out.println("\n==================================================");
            System.out.println("   SISTEMA DE GESTION DE CITAS - CLINICA UDB");
            System.out.println("==================================================");
            System.out.println("1. Registrar Paciente");
            System.out.println("2. Registrar Doctor");
            System.out.println("3. Agendar / Registrar Cita");
            System.out.println("4. Consultar Datos Registrados");
            System.out.println("5. Cancelar Cita");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción (1-6): ");

            if (entrada.hasNextInt()) {
                opcion = entrada.nextInt();
                entrada.nextLine(); // Limpiar el salto de línea
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                entrada.nextLine();
                continue;
            }

            System.out.println("--------------------------------------------------");

            switch (opcion) {
                case 1:
                    System.out.println("--- REGISTRO DE PACIENTE ---");
                    System.out.print("Nombre completo: ");
                    String nombreP = entrada.nextLine();
                    System.out.print("Edad: ");
                    int edadP = entrada.nextInt();
                    entrada.nextLine();
                    System.out.print("Teléfono: ");
                    String telP = entrada.nextLine();
                    System.out.print("Correo electrónico: ");
                    String correoP = entrada.nextLine();
                    System.out.print("Código de paciente (número): ");
                    int codP = entrada.nextInt();
                    entrada.nextLine();
                    System.out.print("Tipo de paciente (Estudiante/Docente/Administrativo/Visitante): ");
                    String tipoP = entrada.nextLine();

                    pacienteActual = new Paciente(nombreP, edadP, telP, correoP, codP, tipoP, "Activo");
                    pacienteActual.registrarPaciente();
                    break;

                case 2:
                    System.out.println("--- REGISTRO DE DOCTOR ---");
                    System.out.print("Nombre completo: ");
                    String nombreD = entrada.nextLine();
                    System.out.print("Edad: ");
                    int edadD = entrada.nextInt();
                    entrada.nextLine();
                    System.out.print("Teléfono: ");
                    String telD = entrada.nextLine();
                    System.out.print("Correo electrónico: ");
                    String correoD = entrada.nextLine();
                    System.out.print("Código de doctor (número): ");
                    int codD = entrada.nextInt();
                    entrada.nextLine();
                    System.out.print("Especialidad (Medicina General/Psicologia/Nutricion/Fisioterapia): ");
                    String espD = entrada.nextLine();

                    doctorActual = new Doctor(nombreD, edadD, telD, correoD, codD, espD, "Disponible");
                    doctorActual.registrarDoctor();
                    break;

                case 3:
                    if (pacienteActual == null || doctorActual == null) {
                        System.out.println("Error: Primero debe registrar al menos un paciente y un doctor antes de agendar una cita.");
                    } else {
                        System.out.println("--- AGENDAR CITA MÉDICA ---");
                        System.out.print("Código de la cita (número): ");
                        int codC = entrada.nextInt();
                        entrada.nextLine();
                        System.out.print("Fecha de la cita (YYYY-MM-DD): ");
                        String fecha = entrada.nextLine();
                        System.out.print("Hora de la cita (ej. 09:00 AM): ");
                        String hora = entrada.nextLine();
                        System.out.print("Motivo de consulta: ");
                        String motivo = entrada.nextLine();

                        citaActual = new Cita(
                            codC, 
                            pacienteActual.getCodigoPaciente(), 
                            doctorActual.getCodigoDoctor(), 
                            fecha, 
                            hora, 
                            motivo, 
                            "Programada"
                        );
                        citaActual.registrarCita();
                    }
                    break;

                case 4:
                    System.out.println("--- CONSULTA GENERAL DE REGISTROS ---");
                    if (pacienteActual != null) pacienteActual.consultarPaciente();
                    else System.out.println("No hay ningún paciente registrado.");
                    
                    System.out.println();
                    if (doctorActual != null) doctorActual.consultarDoctor();
                    else System.out.println("No hay ningún doctor registrado.");

                    System.out.println();
                    if (citaActual != null) citaActual.consultarCita();
                    else System.out.println("No hay ninguna cita registrada.");
                    break;

                case 5:
                    if (citaActual != null) {
                        citaActual.cancelarCita();
                    } else {
                        System.out.println("No hay ninguna cita activa para cancelar.");
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }

        } while (opcion != 6);

        entrada.close();
    }
}
