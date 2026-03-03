/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author gonza
 */
public class TiqueteJson {
    
    public static void guardarTiquetes(String rutaArchivo, NodoTiquete cabeza) throws Exception {
        String json = convertirTiquetesAJson(cabeza);
        Path ruta = Paths.get(rutaArchivo);
        Files.writeString(ruta, json);
    }

    public static NodoTiquete cargarTiquetes(String rutaArchivo) throws Exception {
        Path ruta = Paths.get(rutaArchivo);

        if (!Files.exists(ruta)) {
            return null; 
        }

        String json = Files.readString(ruta);
        if (json == null || json.trim().equals("")) {
            return null;
        }

        return convertirJsonATiquetes(json);
    }

    
    private static String convertirTiquetesAJson(NodoTiquete cabeza) {

        String json = "{\n";
        json = json + "  \"tiquetes\": [\n";

        NodoTiquete nodoActual = cabeza;
        while (nodoActual != null) {

            Tiquete ticket = nodoActual.getDatoTiquete();

            json = json + "    {\n";
            json = json + "      \"nombreDelCliente\": \"" + escaparTexto(ticket.getNombreCliente()) + "\",\n";
            json = json + "      \"identificacionDelCliente\": \"" + escaparTexto(ticket.getIdCliente()) + "\",\n";
            json = json + "      \"edadDelCliente\": " + ticket.getEdadCliente() + ",\n";
            json = json + "      \"monedaSeleccionada\": \"" + escaparTexto(ticket.getMonedaSeleccionada()) + "\",\n";
            json = json + "      \"servicioSeleccionado\": \"" + escaparTexto(ticket.getServicioSeleccionado()) + "\",\n";
            json = json + "      \"tipoDeBusSeleccionado\": \"" + ticket.getTipoDeBusSeleccionado() + "\",\n";
            json = json + "      \"horaDeCreacionDelTiquete\": \"" + escaparTexto(ticket.getHoraDeCreacionDelTiquete()) + "\"\n";
            json = json + "    }";

            if (nodoActual.getSiguienteNodoTiquete() != null) {
                json = json + ",";
            }
            json = json + "\n";

            nodoActual = nodoActual.getSiguienteNodoTiquete();
        }

        json = json + "  ]\n";
        json = json + "}\n";

        return json;
    }

    
    private static NodoTiquete convertirJsonATiquetes(String textoJson) throws Exception {

        String json = textoJson.replace("\r", "").trim();
        String bloque = extraerBloqueArreglo(json, "tiquetes");

        NodoTiquete cabeza = null;
        NodoTiquete fin = null;

        int posicionActual = bloque.indexOf("{");
        while (posicionActual != -1) {

            int posicionCierre = bloque.indexOf("}", posicionActual);
            if (posicionCierre == -1) throw new Exception("Objeto de tiquete mal formado");

            String objeto = bloque.substring(posicionActual + 1, posicionCierre);

            String nombre = extraerValorStringDeObjeto(objeto, "nombreDelCliente");
            String id = extraerValorStringDeObjeto(objeto, "identificacionDelCliente");
            int edad = extraerValorEntero(objeto, "edadDelCliente");
            String moneda = extraerValorStringDeObjeto(objeto, "monedaSeleccionada");
            String servicio = extraerValorStringDeObjeto(objeto, "servicioSeleccionado");
            String tipoTexto = extraerValorStringDeObjeto(objeto, "tipoDeBusSeleccionado");
            String hora = extraerValorStringDeObjeto(objeto, "horaDeCreacionDelTiquete");

            char tipo = tipoTexto.charAt(0);

            Tiquete nuevo = new Tiquete(nombre, id, edad, moneda, servicio, tipo, hora);
            NodoTiquete nodoNuevo = new NodoTiquete(nuevo, null);

            if (cabeza == null) {
                cabeza = nodoNuevo;
                fin = nodoNuevo;
            } else {
                fin.setSiguienteNodoTiquete(nodoNuevo);
                fin = nodoNuevo;
            }

            posicionActual = bloque.indexOf("{", posicionCierre);
        }

        return cabeza;
    }

    
    private static String extraerBloqueArreglo(String json, String llave) throws Exception {
        String patron = "\"" + llave + "\"";
        int indiceLlave = json.indexOf(patron);
        if (indiceLlave == -1) throw new Exception("No existe el arreglo: " + llave);

        int indiceAbre = json.indexOf("[", indiceLlave);
        int indiceCierra = encontrarCierreDeCorchetes(json, indiceAbre);

        if (indiceAbre == -1 || indiceCierra == -1) throw new Exception("Arreglo mal formado: " + llave);

        return json.substring(indiceAbre + 1, indiceCierra).trim();
    }

    private static int encontrarCierreDeCorchetes(String texto, int indiceAbre) {
        int nivel = 0;
        for (int i = indiceAbre; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (caracter == '[') nivel++;
            if (caracter == ']') {
                nivel--;
                if (nivel == 0) return i;
            }
        }
        return -1;
    }

    private static String extraerValorStringDeObjeto(String objeto, String llave) throws Exception {
        String patron = "\"" + llave + "\"";
        int indiceLlave = objeto.indexOf(patron);
        if (indiceLlave == -1) throw new Exception("Falta llave: " + llave);

        int indiceDosPuntos = objeto.indexOf(":", indiceLlave);
        int primeraComilla = objeto.indexOf("\"", indiceDosPuntos + 1);
        int segundaComilla = objeto.indexOf("\"", primeraComilla + 1);

        if (primeraComilla == -1 || segundaComilla == -1) throw new Exception("String mal formado: " + llave);

        return desescaparTexto(objeto.substring(primeraComilla + 1, segundaComilla));
    }
    
    private static int buscarFinNumero(String texto, int inicio) {
        int indiceComa = texto.indexOf(",", inicio);
        int indiceSalto = texto.indexOf("\n", inicio);
        int indiceLlaveCierra = texto.indexOf("}", inicio);

        int fin = texto.length();
        if (indiceComa != -1 && indiceComa < fin) fin = indiceComa;
        if (indiceSalto != -1 && indiceSalto < fin) fin = indiceSalto;
        if (indiceLlaveCierra != -1 && indiceLlaveCierra < fin) fin = indiceLlaveCierra;

        return fin;
    }

    private static int extraerValorEntero(String objeto, String llave) throws Exception {
        String patron = "\"" + llave + "\"";
        int indiceLlave = objeto.indexOf(patron);
        if (indiceLlave == -1) throw new Exception("Falta llave: " + llave);

        int indiceDosPuntos = objeto.indexOf(":", indiceLlave);
        int inicio = indiceDosPuntos + 1;

        int fin = buscarFinNumero(objeto, inicio);
        String valor = objeto.substring(inicio, fin).trim();

        return Integer.parseInt(valor);
    }

    

    private static String escaparTexto(String texto) {
        if (texto == null) return "";
        texto = texto.replace("\\", "\\\\");
        texto = texto.replace("\"", "\\\"");
        return texto;
    }

    private static String desescaparTexto(String texto) {
        if (texto == null) return "";
        texto = texto.replace("\\\"", "\"");
        texto = texto.replace("\\\\", "\\");
        return texto;
    }
}
