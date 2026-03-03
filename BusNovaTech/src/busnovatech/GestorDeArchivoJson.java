/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 *
 * @author gonza
 */
public class GestorDeArchivoJson {
    
    private String rutaDelArchivoDeConfiguracion;

    public GestorDeArchivoJson(String rutaDelArchivoDeConfiguracion) {
        this.rutaDelArchivoDeConfiguracion = rutaDelArchivoDeConfiguracion;
    }

    public boolean existeArchivoDeConfiguracion() {
        Path ruta = Paths.get(rutaDelArchivoDeConfiguracion);
        return Files.exists(ruta);
    }

    public void guardarConfiguracionEnArchivo(ConfigSistema configuracionDelSistema) throws Exception {
        String textoJson = UtilidadesJson.convertirConfiguracionAJson(configuracionDelSistema);
        Path ruta = Paths.get(rutaDelArchivoDeConfiguracion);
        Files.writeString(ruta, textoJson);
    }

    public ConfigSistema cargarConfiguracionDesdeArchivo() throws Exception {
        Path ruta = Paths.get(rutaDelArchivoDeConfiguracion);

        if (!Files.exists(ruta)) {
            throw new Exception("No existe el archivo config.json");
        }

        String textoJson = Files.readString(ruta);
        if (textoJson == null || textoJson.trim().equals("")) {
            throw new Exception("El archivo config.json está vacío.");
        }

        return UtilidadesJson.convertirJsonAConfiguracion(textoJson);
    }
    
}
