import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        boolean correcto = false;
        while (!correcto){

        String opciones = JOptionPane.showInputDialog(null, "Bienvenido a Counter Strike\n" +
                                                                                    "Elige una opción\n" +
                                                                                    "a) Registrar jugador\n" +
                                                                                    "b) Registrar equipo\n" +
                                                                                    "c) Ver equipos\n" +
                                                                                    "d) Salir");


        switch (opciones) {

            case "a":
                datosJugador();

                break;

            case "b":
                datosEquipo();

                break;

            case "c":
                verEquipos();


            case "d" :  JOptionPane.showMessageDialog(null, "Hasta la próxima");
                correcto = true;
                break;

            default:
                JOptionPane.showMessageDialog(null, "Elige una de las opciones");

            }
        }
    }
}
