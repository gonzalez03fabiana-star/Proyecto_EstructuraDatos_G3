/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

/**
 *
 * @author gonza
 */
public class NodoBus {

    private Bus datoDelBus;
    private NodoBus siguienteNodoDeBus;

    public NodoBus(Bus datoDelBus) {
        this.datoDelBus = datoDelBus;
        this.siguienteNodoDeBus = null;
    }

    public Bus getDatoDelBus() {
        return datoDelBus;
    }

    public NodoBus getSiguienteNodoDeBus() {
        return siguienteNodoDeBus;
    }

    public void setSiguienteNodoDeBus(NodoBus siguienteNodoDeBus) {
        this.siguienteNodoDeBus = siguienteNodoDeBus;
    }

}
