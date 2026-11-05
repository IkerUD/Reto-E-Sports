import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Equipo {
    String nombre;
    String abreviatura;
    String region;
    String ubicacion;
    LocalDate fechaCreacion;
}

public class Main {

    static List<Equipo> equipos = new ArrayList<>();

    public static void main(String[] args) {

    }

    public static Equipo datosEquipo(Scanner sc) {
        // Función para solicitar los datos de un equipo
        Equipo e = new Equipo();

        try {

            System.out.println("===== Datos Equipo =====");
            System.out.println("Introduce el nombre del equipo:");
            e.nombre = sc.nextLine();

            System.out.println("Introduce su abreviatura:");
            e.abreviatura = sc.nextLine();

            System.out.println("Introduce la region:");
            e.region = sc.nextLine();

            System.out.println("Introduce la ubicacion:");
            e.ubicacion = sc.nextLine();

            System.out.println("Introduce la fecha de creación (dd/MM/yyyy):");
            String fechaString = sc.nextLine();

            // Validar la fecha
            if (!validarFecha(fechaString)) {
                System.out.println("Fecha inválida.");
                return null;
            }

            // Transformar la fecha de String a LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            LocalDate fecha = LocalDate.parse(fechaString, formatter);

            // Reemplazar la fecha de la funcion por la de la class Equipo
            e.fechaCreacion = fecha;

            // Mostrar la información proporcionada
            System.out.println("===== NUEVO EQUIPO --> " + e.nombre + " =====");
            System.out.println("Nombre: " + e.nombre);
            System.out.println("Abreviatura: " + e.abreviatura);
            System.out.println("Region: " + e.region);
            System.out.println("Ubicacion: " + e.ubicacion);
            System.out.println("Fecha de creacion: " + e.fechaCreacion);
            System.out.println("===== ===== ===== =====");

        } catch (Exception ex) {
            System.out.println("Problemas " + ex.getClass() + ex.getMessage());
        }

        return e;

    }

    public static boolean validarFecha(String fechaString) {
        String patron = "dd/MM/uuuu";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patron).withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(fechaString, dtf);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
