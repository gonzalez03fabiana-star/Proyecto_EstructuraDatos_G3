/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

/**
 *
 * @author gonza
 */
public class NodoUsuario {

    private Usuario datoDelUsuario;
    private NodoUsuario siguienteNodoDeUsuario;

    public NodoUsuario(Usuario datoDelUsuario) {
        this.datoDelUsuario = datoDelUsuario;
        this.siguienteNodoDeUsuario = null;
    }

    public Usuario getDatoDelUsuario() {
        return datoDelUsuario;
    }

    public NodoUsuario getSiguienteNodoDeUsuario() {
        return siguienteNodoDeUsuario;
    }

    public void setSiguienteNodoDeUsuario(NodoUsuario siguienteNodoDeUsuario) {
        this.siguienteNodoDeUsuario = siguienteNodoDeUsuario;
    }

}
