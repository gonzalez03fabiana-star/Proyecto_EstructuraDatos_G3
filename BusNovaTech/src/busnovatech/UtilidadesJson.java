/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

/**
 *
 * @author gonza
 */
public class UtilidadesJson {

    public static String convertirConfiguracionAJson(ConfigSistema configuracionDelSistema) throws Exception {

        String json = "{\n";
        json = json + "  \"nombreDeLaTerminal\": \"" + escaparTexto(configuracionDelSistema.getNombreDeLaTerminal()) + "\",\n";
        json = json + "  \"cantidadTotalDeBuses\": " + configuracionDelSistema.getCantidadTotalDeBuses() + ",\n";

        // Buses
        json = json + "  \"buses\": [\n";
        json = json + convertirListaDeBusesAJson(configuracionDelSistema.getListaEnlazadaDeBuses());
        json = json + "  ],\n";

        // Usuarios
        json = json + "  \"usuarios\": [\n";
        json = json + convertirListaDeUsuariosAJson(configuracionDelSistema.getListaEnlazadaDeUsuarios());
        json = json + "  ]\n";

        json = json + "}\n";
        return json;
    }

    private static String convertirListaDeBusesAJson(ListaEnlazadaBus listaEnlazadaDeBuses) {
        String texto = "";

        NodoBus nodoActual = listaEnlazadaDeBuses.getNodoCabezaDeBuses();
        while (nodoActual != null) {
            Bus busActual = nodoActual.getDatoDelBus();

            texto = texto + "    { \"numeroIdentificadorDelBus\": " + busActual.getNumeroIdentificadorDelBus()
                    + ", \"tipoDeBus\": \"" + busActual.getTipoDeBus() + "\" }";

            if (nodoActual.getSiguienteNodoDeBus() != null) {
                texto = texto + ",";
            }
            texto = texto + "\n";

            nodoActual = nodoActual.getSiguienteNodoDeBus();
        }

        return texto;
    }

    private static String convertirListaDeUsuariosAJson(ListaEnlazadaUsuario listaEnlazadaDeUsuarios) {
        String texto = "";

        NodoUsuario nodoActual = listaEnlazadaDeUsuarios.getNodoCabezaDeUsuarios();
        while (nodoActual != null) {
            Usuario usuarioActual = nodoActual.getDatoDelUsuario();

            texto = texto + "    { \"nombreDeUsuario\": \"" + escaparTexto(usuarioActual.getNombreDeUsuario())
                    + "\", \"contrasenaDelUsuario\": \"" + escaparTexto(usuarioActual.getContrasenaDelUsuario()) + "\" }";

            if (nodoActual.getSiguienteNodoDeUsuario() != null) {
                texto = texto + ",";
            }
            texto = texto + "\n";

            nodoActual = nodoActual.getSiguienteNodoDeUsuario();
        }

        return texto;
    }

    public static ConfigSistema convertirJsonAConfiguracion(String textoJson) throws Exception {
        String json = textoJson.replace("\r", "").trim();

        String nombreDeLaTerminal = extraerValorString(json, "nombreDeLaTerminal");
        int cantidadTotalDeBuses = extraerValorEntero(json, "cantidadTotalDeBuses");

        String bloqueDeBuses = extraerBloqueArreglo(json, "buses");
        String bloqueDeUsuarios = extraerBloqueArreglo(json, "usuarios");

        ListaEnlazadaBus listaEnlazadaDeBuses = construirListaDeBusesDesdeBloque(bloqueDeBuses);
        ListaEnlazadaUsuario listaEnlazadaDeUsuarios = construirListaDeUsuariosDesdeBloque(bloqueDeUsuarios);

        if (listaEnlazadaDeBuses.getCantidadTotalDeBuses() != cantidadTotalDeBuses) {
            throw new Exception("La cantidadTotalDeBuses no coincide con la lista de buses en el JSON.");
        }

        validarTiposDeBuses(listaEnlazadaDeBuses);

        return new ConfigSistema(nombreDeLaTerminal, cantidadTotalDeBuses, listaEnlazadaDeBuses, listaEnlazadaDeUsuarios, null);
    }

    private static ListaEnlazadaBus construirListaDeBusesDesdeBloque(String bloqueDeBuses) throws Exception {
        ListaEnlazadaBus lista = new ListaEnlazadaBus();

        int posicionActual = bloqueDeBuses.indexOf("{");
        while (posicionActual != -1) {

            int posicionCierre = bloqueDeBuses.indexOf("}", posicionActual);
            if (posicionCierre == -1) {
                throw new Exception("Objeto de bus mal formado en JSON.");
            }

            String objeto = bloqueDeBuses.substring(posicionActual + 1, posicionCierre);

            int numeroIdentificadorDelBus = extraerValorEnteroDeObjeto(objeto, "numeroIdentificadorDelBus");
            String tipoTexto = extraerValorStringDeObjeto(objeto, "tipoDeBus");
            char tipoDeBus = tipoTexto.charAt(0);

            lista.agregarBusAlFinal(new Bus(numeroIdentificadorDelBus, tipoDeBus));

            posicionActual = bloqueDeBuses.indexOf("{", posicionCierre);
        }

        return lista;
    }

    private static ListaEnlazadaUsuario construirListaDeUsuariosDesdeBloque(String bloqueDeUsuarios) throws Exception {
        ListaEnlazadaUsuario lista = new ListaEnlazadaUsuario();

        int posicionActual = bloqueDeUsuarios.indexOf("{");
        while (posicionActual != -1) {

            int posicionCierre = bloqueDeUsuarios.indexOf("}", posicionActual);
            if (posicionCierre == -1) {
                throw new Exception("Objeto de usuario mal formado en JSON.");
            }

            String objeto = bloqueDeUsuarios.substring(posicionActual + 1, posicionCierre);

            String nombreDeUsuario = extraerValorStringDeObjeto(objeto, "nombreDeUsuario");
            String contrasenaDelUsuario = extraerValorStringDeObjeto(objeto, "contrasenaDelUsuario");

            lista.agregarUsuarioAlFinal(new Usuario(nombreDeUsuario, contrasenaDelUsuario));

            posicionActual = bloqueDeUsuarios.indexOf("{", posicionCierre);
        }

        return lista;
    }

    private static void validarTiposDeBuses(ListaEnlazadaBus listaEnlazadaDeBuses) throws Exception {
        int contadorPreferencial = 0;
        int contadorDirecto = 0;

        NodoBus nodoActual = listaEnlazadaDeBuses.getNodoCabezaDeBuses();
        while (nodoActual != null) {
            char tipo = nodoActual.getDatoDelBus().getTipoDeBus();
            if (tipo == 'P') {
                contadorPreferencial++;
            }
            if (tipo == 'D') {
                contadorDirecto++;
            }
            nodoActual = nodoActual.getSiguienteNodoDeBus();
        }

        if (contadorPreferencial != 1 || contadorDirecto != 1) {
            throw new Exception("Debe existir EXACTAMENTE 1 bus Preferencial (P) y 1 bus Directo (D).");
        }
    }

    private static String extraerValorString(String json, String llave) throws Exception {
        String patron = "\"" + llave + "\"";
        int indiceLlave = json.indexOf(patron);
        if (indiceLlave == -1) {
            throw new Exception("No existe la llave: " + llave);
        }

        int indiceDosPuntos = json.indexOf(":", indiceLlave);
        int primeraComilla = json.indexOf("\"", indiceDosPuntos + 1);
        int segundaComilla = json.indexOf("\"", primeraComilla + 1);

        if (primeraComilla == -1 || segundaComilla == -1) {
            throw new Exception("Formato inválido para: " + llave);
        }

        return desescaparTexto(json.substring(primeraComilla + 1, segundaComilla));
    }

    private static int extraerValorEntero(String json, String llave) throws Exception {
        String patron = "\"" + llave + "\"";
        int indiceLlave = json.indexOf(patron);
        if (indiceLlave == -1) {
            throw new Exception("No existe la llave: " + llave);
        }

        int indiceDosPuntos = json.indexOf(":", indiceLlave);
        int indiceComa = json.indexOf(",", indiceDosPuntos + 1);

        String valor;
        if (indiceComa == -1) {
            int indiceFin = json.indexOf("\n", indiceDosPuntos + 1);
            if (indiceFin == -1) {
                indiceFin = json.length();
            }
            valor = json.substring(indiceDosPuntos + 1, indiceFin).trim();
        } else {
            valor = json.substring(indiceDosPuntos + 1, indiceComa).trim();
        }

        return Integer.parseInt(valor);
    }

    private static String extraerBloqueArreglo(String json, String llave) throws Exception {
        String patron = "\"" + llave + "\"";
        int indiceLlave = json.indexOf(patron);
        if (indiceLlave == -1) {
            throw new Exception("No existe el arreglo: " + llave);
        }

        int indiceAbre = json.indexOf("[", indiceLlave);
        int indiceCierra = encontrarCierreDeCorchetes(json, indiceAbre);

        if (indiceAbre == -1 || indiceCierra == -1) {
            throw new Exception("Arreglo mal formado: " + llave);
        }

        return json.substring(indiceAbre + 1, indiceCierra).trim();
    }

    private static int encontrarCierreDeCorchetes(String texto, int indiceAbre) {
        int nivel = 0;
        for (int i = indiceAbre; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (caracter == '[') {
                nivel++;
            }
            if (caracter == ']') {
                nivel--;
                if (nivel == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static String extraerValorStringDeObjeto(String objeto, String llave) throws Exception {
        String patron = "\"" + llave + "\"";
        int indiceLlave = objeto.indexOf(patron);
        if (indiceLlave == -1) {
            throw new Exception("Falta llave en objeto: " + llave);
        }

        int indiceDosPuntos = objeto.indexOf(":", indiceLlave);
        int primeraComilla = objeto.indexOf("\"", indiceDosPuntos + 1);
        int segundaComilla = objeto.indexOf("\"", primeraComilla + 1);

        if (primeraComilla == -1 || segundaComilla == -1) {
            throw new Exception("String mal formado en: " + llave);
        }

        return desescaparTexto(objeto.substring(primeraComilla + 1, segundaComilla));
    }

    private static int extraerValorEnteroDeObjeto(String objeto, String llave) throws Exception {
        String patron = "\"" + llave + "\"";
        int indiceLlave = objeto.indexOf(patron);
        if (indiceLlave == -1) {
            throw new Exception("Falta llave en objeto: " + llave);
        }

        int indiceDosPuntos = objeto.indexOf(":", indiceLlave);
        int indiceComa = objeto.indexOf(",", indiceDosPuntos + 1);

        String valor;
        if (indiceComa == -1) {
            valor = objeto.substring(indiceDosPuntos + 1).trim();
        } else {
            valor = objeto.substring(indiceDosPuntos + 1, indiceComa).trim();
        }

        return Integer.parseInt(valor);
    }

    private static String escaparTexto(String texto) {
        if (texto == null) {
            return "";
        }
        texto = texto.replace("\\", "\\\\");
        texto = texto.replace("\"", "\\\"");
        return texto;
    }

    private static String desescaparTexto(String texto) {
        if (texto == null) {
            return "";
        }
        texto = texto.replace("\\\"", "\"");
        texto = texto.replace("\\\\", "\\");
        return texto;
    }

}
