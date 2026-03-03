/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

/**
 *
 * @author gonza
 */
public class NodoTiquete {
    
    private Tiquete datoTiquete;
    private NodoTiquete siguienteNodoTiquete;

    public NodoTiquete(Tiquete datoTiquete, NodoTiquete siguienteNodoTiquete) {
        this.datoTiquete = datoTiquete;
        this.siguienteNodoTiquete = siguienteNodoTiquete;
    }

    public Tiquete getDatoTiquete() {
        return datoTiquete;
    }

    public void setDatoTiquete(Tiquete datoTiquete) {
        this.datoTiquete = datoTiquete;
    }

    public NodoTiquete getSiguienteNodoTiquete() {
        return siguienteNodoTiquete;
    }

    public void setSiguienteNodoTiquete(NodoTiquete siguienteNodoTiquete) {
        this.siguienteNodoTiquete = siguienteNodoTiquete;
    }
    
    
}
