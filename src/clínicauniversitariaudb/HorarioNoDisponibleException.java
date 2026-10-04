package clínicauniversitariaudb;

public class HorarioNoDisponibleException extends Exception {
    public HorarioNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}