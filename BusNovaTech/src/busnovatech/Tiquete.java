/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

/**
 *
 * @author gonza
 */
public class Tiquete {
    
    private String nombreCliente;
    private String idCliente;
    private int edadCliente;
    private String monedaSeleccionada;
    private String servicioSeleccionado;
    private char tipoDeBusSeleccionado;
    private String horaDeCreacionDelTiquete;

    public Tiquete(String nombreCliente, String idCliente, int edadCliente, String monedaSeleccionada, String servicioSeleccionado, char tipoDeBusSeleccionado, String horaDeCreacionDelTiquete) {
        this.nombreCliente = nombreCliente;
        this.idCliente = idCliente;
        this.edadCliente = edadCliente;
        this.monedaSeleccionada = monedaSeleccionada;
        this.servicioSeleccionado = servicioSeleccionado;
        this.tipoDeBusSeleccionado = tipoDeBusSeleccionado;
        this.horaDeCreacionDelTiquete = horaDeCreacionDelTiquete;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getEdadCliente() {
        return edadCliente;
    }

    public void setEdadCliente(int edadCliente) {
        this.edadCliente = edadCliente;
    }

    public String getMonedaSeleccionada() {
        return monedaSeleccionada;
    }

    public void setMonedaSeleccionada(String monedaSeleccionada) {
        this.monedaSeleccionada = monedaSeleccionada;
    }

    public String getServicioSeleccionado() {
        return servicioSeleccionado;
    }

    public void setServicioSeleccionado(String servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }

    public char getTipoDeBusSeleccionado() {
        return tipoDeBusSeleccionado;
    }

    public void setTipoDeBusSeleccionado(char tipoDeBusSeleccionado) {
        this.tipoDeBusSeleccionado = tipoDeBusSeleccionado;
    }

    public String getHoraDeCreacionDelTiquete() {
        return horaDeCreacionDelTiquete;
    }

    public void setHoraDeCreacionDelTiquete(String horaDeCreacionDelTiquete) {
        this.horaDeCreacionDelTiquete = horaDeCreacionDelTiquete;
    }
    
    
    
}
