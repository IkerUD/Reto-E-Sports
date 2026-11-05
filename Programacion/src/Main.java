import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Equipo {
    String nombre;
    String abreviatura;
    String region;
    String ubicacion;
    LocalDate fechaCreacion;
}
class Jugador {
    String nombre;
    String apellido;
    String nacionalidad;
    String fecha;
    String nickname;
    String rol;
    String sueldo;

    public class Main {

    // Creamos los arrays de jugadores y equipos
    static List<Equipo> equipos = new ArrayList<>();
    //static List<Jugador> jugadores = new ArrayList<>();

    public static void main(String[] args) {
        boolean correcto = false;

        // Repetitiva para que siempre generemos el menu
        while (!correcto){
            Scanner sc = new Scanner(System.in);
            System.out.println("===== BIENVENIDO AL TORNEO DE CS2 =====\n" +
                    "Selecciona una opcion!\n" +
                    "a) Registrar jugador\n" +
                    "b) Registrar equipo\n" +
                    "c) Ver equipos\n" +
                    "d) Salir");
                    //TODO Poder gestionar jugadores y equipos
            String opciones = sc.nextLine();

                switch (opciones) {

                    case "a":
                        datosJugador();

                        break;

            case "b":
                // Registrar equipo y añadirlo al array
                Equipo e = datosEquipo(sc);
                if (e != null) {
                    equipos.add(e);
                }
                break;

            case "c":
                // Seleccionar equipo y ver su información
                System.out.println("¿Qué equipo quieres ver? (1-" + equipos.size() + ")");
                int num = sc.nextInt() - 1;  // ← restar 1, porque get() es 0-based
                sc.nextLine();

                if (num >= 0 && num < equipos.size()) {
                    Equipo eq = equipos.get(num);
                    System.out.println("Nombre: " + eq.nombre);
                    System.out.println("Abreviatura: " + eq.abreviatura);
                    System.out.println("Region: " + eq.region);
                    System.out.println("Ubicacion: " + eq.ubicacion);
                    System.out.println("Fecha: " + eq.fechaCreacion);
                } else {
                    System.out.println("Equipo no existe.");
                }
                break;


            case "d" :  JOptionPane.showMessageDialog(null, "Hasta la próxima");
                // Finalizar el programa
                correcto = true;
                break;

                    default:
                        JOptionPane.showMessageDialog(null, "Elige una de las opciones");

                }
            }
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

        public static Jugador datosJugador() {
            Jugador j = new Jugador();
            Scanner sc = new Scanner(System.in);

            System.out.println("=======Datos del jugador=======");
            System.out.println("Introduce los siguintes Datos");

            // 1. Nombre
            System.out.println("Introduce el nombre:");
            j.nombre = sc.nextLine();
            try {
                Pattern pat = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]{2,15}$");
                Matcher mat = pat.matcher(j.nombre);
                if (!mat.matches()) {
                    throw new IllegalArgumentException("No válido");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // 2. Apellido
            System.out.println("Introduce el apellido:");
            j.apellido = sc.nextLine();
            try {
                Pattern pet = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]{2,15}$");
                Matcher met = pet.matcher(j.apellido);
                if (!met.matches()) {
                    throw new IllegalArgumentException("No válido");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // 3. Nacionalidad
            System.out.println("Introduce la nacionalidad:");
            j.nacionalidad = sc.nextLine();
            try {
                Pattern pit = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]{3,25}$");
                Matcher mit = pit.matcher(j.nacionalidad);
                if (!mit.matches()) {
                    throw new IllegalArgumentException("No válido");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // 4. Fecha
            System.out.println("Introduce la fecha nacimiento (dd/MM/yyyy):");
            j.fecha = sc.nextLine();
            try {
                Pattern pot = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19[0-9]{2}|20[0-1][0-9]|202[0-6])$");
                Matcher mot = pot.matcher(j.fecha);
                if (!mot.matches()) {
                    throw new IllegalArgumentException("No válido");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // 5. Nickname
            System.out.println("Introduce el nickname:");
            j.nickname = sc.nextLine();
            try {
                Pattern put = Pattern.compile("^[a-zA-Z0-9_\\.]{4,20}$");
                Matcher mut = put.matcher(j.nickname);
                if (!mut.matches()) {
                    throw new IllegalArgumentException("No válido");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // 6. Rol
            System.out.println("Introduce el rol:");
            j.rol = sc.nextLine();
            try {
                Pattern pt = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{3,20}$");
                Matcher mt = pt.matcher(j.rol);
                if (!mt.matches()) {
                    throw new IllegalArgumentException("No válido");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // 7. Sueldo
            System.out.println("Introduce el sueldo:");
            j.sueldo = sc.nextLine();
            try {
                Pattern pa = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
                Matcher me = pa.matcher(j.sueldo);

                if (!me.matches()) {
                    throw new IllegalArgumentException("Formato de sueldo no válido");
                }

                // NumberFormatException ocurre si falla la conversión de texto a número
                double sueldo = Double.parseDouble(j.sueldo);

                if (sueldo <= 1220) {
                    throw new IllegalArgumentException("El sueldo debe ser superior al SMI");
                }

            } catch (NumberFormatException e) {
                System.out.println("Formato de sueldo no válido");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // Impresión final de datos
            System.out.println("=======Datos del jugador=======");
            System.out.println("Nombre: " + j.nombre);
            System.out.println("Apellido: " + j.apellido);
            System.out.println("Nacionalidad: " + j.nacionalidad);
            System.out.println("Fecha de nacimiento: " + j.fecha);
            System.out.println("Nickname: " + j.nickname);
            System.out.println("Rol: " + j.rol);
            System.out.println("Sueldo: " + j.sueldo);

            //menu

            return j;
        }
    }


}
