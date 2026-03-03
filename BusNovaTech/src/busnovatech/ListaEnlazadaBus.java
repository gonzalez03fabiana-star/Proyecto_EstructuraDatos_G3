/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

/**
 *
 * @author gonza
 */
public class ListaEnlazadaBus {

    private NodoBus nodoCabezaDeBuses;
    private NodoBus nodoFinalDeBuses;
    private int cantidadTotalDeBuses;

    public ListaEnlazadaBus() {
        this.nodoCabezaDeBuses = null;
        this.nodoFinalDeBuses = null;
        this.cantidadTotalDeBuses = 0;
    }

    public void agregarBusAlFinal(Bus busParaAgregar) {
        NodoBus nuevoNodoDeBus = new NodoBus(busParaAgregar);

        if (nodoCabezaDeBuses == null) {
            nodoCabezaDeBuses = nuevoNodoDeBus;
            nodoFinalDeBuses = nuevoNodoDeBus;
        } else {
            nodoFinalDeBuses.setSiguienteNodoDeBus(nuevoNodoDeBus);
            nodoFinalDeBuses = nuevoNodoDeBus;
        }

        cantidadTotalDeBuses++;
    }

    public int getCantidadTotalDeBuses() {
        return cantidadTotalDeBuses;
    }

    public NodoBus getNodoCabezaDeBuses() {
        return nodoCabezaDeBuses;
    }

}
