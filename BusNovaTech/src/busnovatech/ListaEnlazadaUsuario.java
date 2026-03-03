/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

/**
 *
 * @author gonza
 */
public class ListaEnlazadaUsuario {

    private NodoUsuario nodoCabezaDeUsuarios;
    private NodoUsuario nodoFinalDeUsuarios;
    private int cantidadTotalDeUsuarios;

    public ListaEnlazadaUsuario() {
        this.nodoCabezaDeUsuarios = null;
        this.nodoFinalDeUsuarios = null;
        this.cantidadTotalDeUsuarios = 0;
    }

    public void agregarUsuarioAlFinal(Usuario usuarioParaAgregar) {
        NodoUsuario nuevoNodoDeUsuario = new NodoUsuario(usuarioParaAgregar);

        if (nodoCabezaDeUsuarios == null) {
            nodoCabezaDeUsuarios = nuevoNodoDeUsuario;
            nodoFinalDeUsuarios = nuevoNodoDeUsuario;
        } else {
            nodoFinalDeUsuarios.setSiguienteNodoDeUsuario(nuevoNodoDeUsuario);
            nodoFinalDeUsuarios = nuevoNodoDeUsuario;
        }

        cantidadTotalDeUsuarios++;
    }

    public boolean validarCredenciales(String nombreDeUsuarioIngresado, String contrasenaIngresada) {
        NodoUsuario nodoActual = nodoCabezaDeUsuarios;

        while (nodoActual != null) {
            Usuario usuarioActual = nodoActual.getDatoDelUsuario();
            if (usuarioActual.getNombreDeUsuario().equals(nombreDeUsuarioIngresado)
                    && usuarioActual.getContrasenaDelUsuario().equals(contrasenaIngresada)) {
                return true;
            }
            nodoActual = nodoActual.getSiguienteNodoDeUsuario();
        }

        return false;
    }

    public int getCantidadTotalDeUsuarios() {
        return cantidadTotalDeUsuarios;
    }

    public NodoUsuario getNodoCabezaDeUsuarios() {
        return nodoCabezaDeUsuarios;
    }

}
