/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

/**
 *
 * @author gonza
 */
public class Usuario {

    private String nombreDeUsuario;
    private String contrasenaDelUsuario;

    public Usuario(String nombreDeUsuario, String contrasenaDelUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.contrasenaDelUsuario = contrasenaDelUsuario;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getContrasenaDelUsuario() {
        return contrasenaDelUsuario;
    }

    public void setContrasenaDelUsuario(String contrasenaDelUsuario) {
        this.contrasenaDelUsuario = contrasenaDelUsuario;
    }

}
