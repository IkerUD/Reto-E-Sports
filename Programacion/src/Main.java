import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// me he ayudado de la IA para entender las clases y los arrays
class Equipo {
    String nombre;
    String abreviatura;
    String region;
    String ubi;
    LocalDate fechafundacion;
    List<Jugador> jugadores = new ArrayList<>();
}
class Jugador {
    String nombre;
    String apellido;
    String nacionalidad;
    String fechadenacimiento;
    String nombreusuario;
    String rol;
    String sueldos;
}

public class Main {
    public static boolean error;
    // Creamos el array
    static List<Equipo> equipos = new ArrayList<>();

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
            String opciones = sc.nextLine();

            switch (opciones) {

                case "a":
                    // Para porder registrar al jugador jugador
                    Jugador j = datosdeljugador(sc);
                    break;

                case "b":
                    // Para poder registrar al equipo y añadirlo al array
                    Equipo e = datosdelequipo(sc);
                    if (e != null) {
                        equipos.add(e);
                    }
                    break;

                case "c":
                    // Para poder seleccionar  el equipo y ver su información
                    System.out.println("¿Qué equipo quieres ver? (1-" + equipos.size() + ")");
                    int num = sc.nextInt() - 1;  // ← restar 1, porque get() es 0-based
                    sc.nextLine();

                    if (num >= 0 && num < equipos.size()) {
                        Equipo eq = equipos.get(num);
                        System.out.println("Nombre: " + eq.nombre);
                        System.out.println("Abreviatura: " + eq.abreviatura);
                        System.out.println("Region: " + eq.region);
                        System.out.println("Ubicacion: " + eq.ubi);
                        System.out.println("Fecha: " + eq.fechafundacion);

                        System.out.println("¿Ver lista de jugadores? (S/N)");
                        String ans = sc.nextLine().trim().toLowerCase();

                        if (ans.equalsIgnoreCase("s") || ans.equalsIgnoreCase("si")) {
                            if (eq.jugadores.isEmpty()) {
                                System.out.println("Este equipo no tiene jugadores.");
                            } else {
                                System.out.println("--- Jugadores de " + eq.nombre + " ---");
                                for (Jugador jug : eq.jugadores) {
                                    System.out.println(jug.nombre + " " + jug.apellido + " - " + jug.rol);
                                }
                            }
                        }
                    } else {
                        System.out.println("Equipo no existe");
                    }
                    break;
                //  Pra poder finalizar el programa con la opcion d)
                case "d" :
                    System.out.println("Hasta la próxima");

                    correcto = true;
                    break;

                default:
                    System.out.println("Elige una de las opciones");

            }
        }
    }

    // Una función para solicitar los datos de un equipo y que se guarden
    public static Equipo datosdelequipo(Scanner sc) {

        Equipo e = new Equipo();

        System.out.println("===== Datos - del - Equipo =====");


        try {

            error = true;
            do {
                try {
                    System.out.println("Introduce el nombre del equipo:");
                    e.nombre = sc.nextLine();
                    Pattern pat = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{2,15}$");
                    Matcher mat = pat.matcher(e.nombre);
                    if (!mat.matches()) {
                        throw new IllegalArgumentException("Nombre no válido");
                    } else error = false;
                } catch (IllegalArgumentException a) {
                    System.out.println(a.getMessage());
                }
            } while (error);

            error = true;
            do {
                try {
                    System.out.println("Introduce su abreviatura:");
                    e.abreviatura = sc.nextLine();
                    Pattern pat = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{2,6}$");
                    Matcher mat = pat.matcher(e.abreviatura);
                    if (!mat.matches()) {
                        throw new IllegalArgumentException("Abreviatura no válida");
                    } else error = false;
                } catch (IllegalArgumentException a) {
                    System.out.println(a.getMessage());
                }
            } while (error);

            error = true;
            do {
                try {
                    System.out.println("Introduce la region:");
                    e.region = sc.nextLine();
                    Pattern pat = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{4,25}$");
                    Matcher mat = pat.matcher(e.region);
                    if (!mat.matches()) {
                        throw new IllegalArgumentException("Abreviatura no válida");
                    } else error = false;
                } catch (IllegalArgumentException a) {
                    System.out.println(a.getMessage());
                }
            } while (error);

            error = true;
            do {
                try {
                    System.out.println("Introduce la ubicacion:");
                    e.ubi = sc.nextLine();
                    Pattern pat = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{4,50}$");
                    Matcher mat = pat.matcher(e.ubi);
                    if (!mat.matches()) {
                        throw new IllegalArgumentException("Ubicacion no válida");
                    } else error = false;
                } catch (IllegalArgumentException a) {
                    System.out.println(a.getMessage());
                }
            } while (error);

            error = true;
            do {
                try {

                    System.out.println("Introduce la fecha de creación (dd/MM/yyyy):");
                    String fechaString = sc.nextLine();

                    // Para validar la fecha de creacion del equipo
                    if (!validacionfecha(fechaString)) {
                        throw new IllegalArgumentException("Fecha inválida");
                    }

                    // Transformar la fecha de String a LocalDate
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fecha = LocalDate.parse(fechaString, formatter);

                    // Reemplazar la fecha de la funcion por la de la clase del  Equipo
                    e.fechafundacion = fecha;

                    error = false;

                } catch (IllegalArgumentException a) {
                    System.out.println(a.getMessage());
                }
            } while (error);

            // Mostrar la información que se ha añadido
            System.out.println("---- NUEVO EQUIPO --> " + e.nombre + " ----");
            System.out.println("Nombre: " + e.nombre);
            System.out.println("Abreviatura: " + e.abreviatura);
            System.out.println("Region: " + e.region);
            System.out.println("Ubicacion: " + e.ubi);
            System.out.println("Fecha de creacion: " + e.fechafundacion);
            System.out.println("---------------------");

        } catch (Exception ex) {
            System.out.println("Problemas " + ex.getClass() + ex.getMessage());
        }

        return e;

    }

    public static Jugador datosdeljugador(Scanner sc) {

        // Comprobramos que hay equipos antes de crear el jugador
        if (equipos.isEmpty()) {
            System.out.println("Registra un equipo primero (opción b)");
            return null;
        }

        Jugador j = new Jugador();

        System.out.println("======= Datos - del - jugador=======");
        System.out.println("Introduce los siguintes Datos");

        // Nombre del jugador

        error = true;
        do {
            try {
                System.out.println("Introduce el nombre:");
                j.nombre = sc.nextLine();
                Pattern pat = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{2,15}$");
                Matcher mat = pat.matcher(j.nombre);
                if (!mat.matches()) {
                    throw new IllegalArgumentException("Nombre no válido");
                } else error = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        // Apellidos del jugador

        error = true;
        do {
            try {
                System.out.println("Introduce el apellido:");
                j.apellido = sc.nextLine();
                Pattern pet = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]{2,15}$");
                Matcher met = pet.matcher(j.apellido);
                if (!met.matches()) {
                    throw new IllegalArgumentException("Apellido no válido");
                } else error = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        // Nacionalidad del jugador

        error = true;
        do {
            try {
                System.out.println("Introduce la nacionalidad:");
                j.nacionalidad = sc.nextLine();
                Pattern pit = Pattern.compile("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]{3,25}$");
                Matcher mit = pit.matcher(j.nacionalidad);
                if (!mit.matches() ) {
                    throw new IllegalArgumentException("Nacionalidad no valida");
                } else error = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        // Fecha de nacimiento del jugador

        error = true;
        do {
            try {
                System.out.println("Introduce la fecha nacimiento (dd/MM/yyyy):");
                j.fechadenacimiento = sc.nextLine();
                Pattern pot = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19[0-9]{2}|20[0-1][0-9]|202[0-6])$");
                Matcher mot = pot.matcher(j.fechadenacimiento);
                if (!mot.matches()) {
                    throw new IllegalArgumentException("Fecha de nacimiento no válida");
                } else error = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        // Nombre de jugador/nickname

        error = true;
        do {
            try {
                System.out.println("Introduce el nombre de jugador/nickname :");
                j.nombreusuario = sc.nextLine();
                Pattern put = Pattern.compile("^[a-zA-Z0-9_\\.]{4,20}$");
                Matcher mut = put.matcher(j.nombreusuario);
                if (!mut.matches()) {
                    throw new IllegalArgumentException("Tu nombre de jugador/nickname no es válido");
                } else error = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        // Rol que desenpeña el jugador

        error = true;
        do {
            try {
                System.out.println("Introduce el rol:");
                j.rol = sc.nextLine();
                Pattern pt = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{3,20}$");
                Matcher mt = pt.matcher(j.rol);
                if (!mt.matches()) {
                    throw new IllegalArgumentException("El rol no es válido");
                } else error = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        // sueldo del jugador

        error = true;
        do {
            try {
                System.out.println("Introduce el sueldo:");
                j.sueldos = sc.nextLine();
                Pattern pa = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
                Matcher me = pa.matcher(j.sueldos);

                if (!me.matches()) {
                    throw new IllegalArgumentException("El Formato de sueldo no válido");
                }

                double sueldo = Double.parseDouble(j.sueldos);

                if (sueldo <= 1220) {
                    throw new IllegalArgumentException("El sueldo debe ser superior al SMI");
                } else error = false;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        // Introduccir el equipo al que pertenece el jugador

        int numEquipo = -1;
        error = true;
        do {
            try {
                System.out.println("Introduce numero del equipo:");
                for (int i = 0; i < equipos.size(); i++) {
                    System.out.println((i + 1) + ") " + equipos.get(i).nombre);
                    System.out.println("--------------------------");
                }

                numEquipo = sc.nextInt() - 1;
                sc.nextLine();

                if (numEquipo < 0 || numEquipo >= equipos.size()) {
                    throw new Exception("El equipo no existe. Asegurate de que los nombres coincidan.");
                } else {

                    error = false;
                }

                // por si ponen una letra en vez del numero de quipo
            } catch (InputMismatchException e) {
                System.out.println("Elige el quipo mediante el numero del equipo asignado (solo numeros)");
                sc.nextLine();
                numEquipo = -1;
                // por si ponen un numero que no existe
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }while (error);

        // Añadir el jugador a su equipo
        equipos.get(numEquipo).jugadores.add(j);
        System.out.println(j.nombre + " " + j.apellido + " asignado a " + equipos.get(numEquipo).nombre);

        // Impresión final de datos
        System.out.println("======= Datos - del - jugador=======");
        System.out.println("Equipo al que pertenece: " + equipos.get(numEquipo).nombre);
        System.out.println("Nickname: " + j.nombreusuario);
        System.out.println("Nombre: " + j.nombre);
        System.out.println("Apellido: " + j.apellido);
        System.out.println("Nacionalidad: " + j.nacionalidad);
        System.out.println("Fecha de nacimiento: " + j.fechadenacimiento);
        System.out.println("Rol: " + j.rol);
        System.out.println("Sueldo: " + j.sueldos);

        return j;
    }

    public static boolean validacionfecha(String fechaString) {
        String patron = "dd/MM/yyyy";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patron);

        try {
            LocalDate fecha = LocalDate.parse(fechaString, dtf);

            if (fecha.isAfter(LocalDate.now())) {
                return false;
            }
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
