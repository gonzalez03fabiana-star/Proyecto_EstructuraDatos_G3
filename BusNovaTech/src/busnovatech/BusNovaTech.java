/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package busnovatech;

import javax.swing.JOptionPane;

/**
 *
 * @author gonza
 */
public class BusNovaTech {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ConfigSistema configSistema = ConfigSistema.iniciarSistema();
            configSistema.ejecutarInicioDeSesion();

            boolean salir = false;

            while (!salir) {

                String opcion = JOptionPane.showInputDialog(
                        "MENU\n1. Crear Tiquete\n2. Salir");

                if (opcion == null) {
                    break;
                }

                switch (opcion) {
                    case "1":
                        configSistema.creacionDeTiquete();
                        break;

                    case "2":
                        salir = true;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opcion invalida");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
