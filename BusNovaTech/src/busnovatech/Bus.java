/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

/**
 *
 * @author gonza
 */
public class Bus {

    private int numeroIdentificadorDelBus;
    private char tipoDeBus;
    // 'P' = Preferencial
    // 'D' = Directo
    // 'N' = Normal

    public Bus(int numeroIdentificadorDelBus, char tipoDeBus) {
        this.numeroIdentificadorDelBus = numeroIdentificadorDelBus;
        this.tipoDeBus = tipoDeBus;
    }

    public int getNumeroIdentificadorDelBus() {
        return numeroIdentificadorDelBus;
    }

    public void setNumeroIdentificadorDelBus(int numeroIdentificadorDelBus) {
        this.numeroIdentificadorDelBus = numeroIdentificadorDelBus;
    }

    public char getTipoDeBus() {
        return tipoDeBus;
    }

    public void setTipoDeBus(char tipoDeBus) {
        this.tipoDeBus = tipoDeBus;
    }

}
