/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busnovatech;

import javax.swing.JOptionPane;

/**
 *
 * @author gonza
 */
public class ConfigSistema {

    private String nombreDeLaTerminal;
    private int cantidadTotalDeBuses;
    private ListaEnlazadaBus listaEnlazadaDeBuses;
    private ListaEnlazadaUsuario listaEnlazadaDeUsuarios;
    private NodoTiquete nodoTiquetes;

    private static GestorDeArchivoJson gestorDeArchivoJson
            = new GestorDeArchivoJson("config.json");

    public ConfigSistema(String nombreDeLaTerminal, int cantidadTotalDeBuses, ListaEnlazadaBus listaEnlazadaDeBuses, ListaEnlazadaUsuario listaEnlazadaDeUsuarios, NodoTiquete nodoTiquetes) {
        this.nombreDeLaTerminal = nombreDeLaTerminal;
        this.cantidadTotalDeBuses = cantidadTotalDeBuses;
        this.listaEnlazadaDeBuses = listaEnlazadaDeBuses;
        this.listaEnlazadaDeUsuarios = listaEnlazadaDeUsuarios;
        this.nodoTiquetes = nodoTiquetes;
    }

    public String getNombreDeLaTerminal() {
        return nombreDeLaTerminal;
    }

    public void setNombreDeLaTerminal(String nombreDeLaTerminal) {
        this.nombreDeLaTerminal = nombreDeLaTerminal;
    }

    public int getCantidadTotalDeBuses() {
        return cantidadTotalDeBuses;
    }

    public void setCantidadTotalDeBuses(int cantidadTotalDeBuses) {
        this.cantidadTotalDeBuses = cantidadTotalDeBuses;
    }

    public ListaEnlazadaBus getListaEnlazadaDeBuses() {
        return listaEnlazadaDeBuses;
    }

    public void setListaEnlazadaDeBuses(ListaEnlazadaBus listaEnlazadaDeBuses) {
        this.listaEnlazadaDeBuses = listaEnlazadaDeBuses;
    }

    public ListaEnlazadaUsuario getListaEnlazadaDeUsuarios() {
        return listaEnlazadaDeUsuarios;
    }

    public void setListaEnlazadaDeUsuarios(ListaEnlazadaUsuario listaEnlazadaDeUsuarios) {
        this.listaEnlazadaDeUsuarios = listaEnlazadaDeUsuarios;
    }

    public NodoTiquete getNodoTiquetes() {
        return nodoTiquetes;
    }

    public void setNodoTiquetes(NodoTiquete nodoTiquetes) {
        this.nodoTiquetes = nodoTiquetes;
    }

    public static GestorDeArchivoJson getGestorDeArchivoJson() {
        return gestorDeArchivoJson;
    }

    public static void setGestorDeArchivoJson(GestorDeArchivoJson gestorDeArchivoJson) {
        ConfigSistema.gestorDeArchivoJson = gestorDeArchivoJson;
    }

    public static ConfigSistema iniciarSistema() throws Exception {

    ConfigSistema configuracion;

    if (!gestorDeArchivoJson.existeArchivoDeConfiguracion()) {
        JOptionPane.showMessageDialog(null, "Primera ejecución.\nSe creara el archivo config.json");
        configuracion = construirConfiguracionInicial();
        gestorDeArchivoJson.guardarConfiguracionEnArchivo(configuracion);
    } else {
        configuracion = gestorDeArchivoJson.cargarConfiguracionDesdeArchivo();
    }
    configuracion.setNodoTiquetes(TiqueteJson.cargarTiquetes("tiquetes.json"));

    return configuracion;
}

    private static ConfigSistema construirConfiguracionInicial() throws Exception {

        String nombreDeLaTerminal = solicitarTextoNoVacio("Ingrese el nombre de la terminal:");
        int cantidadTotalDeBuses = solicitarEnteroMinimo("Ingrese la cantidad total de buses (mínimo 2):", 2);

        ListaEnlazadaBus listaEnlazadaDeBuses = new ListaEnlazadaBus();

        int numeroActualDelBus = 1;
        while (numeroActualDelBus <= cantidadTotalDeBuses) {

            char tipoDeBus;

            if (numeroActualDelBus == 1) {
                tipoDeBus = 'P';
            } else if (numeroActualDelBus == 2) {
                tipoDeBus = 'D';
            } else {
                tipoDeBus = 'N';
            }

            listaEnlazadaDeBuses.agregarBusAlFinal(
                    new Bus(numeroActualDelBus, tipoDeBus)
            );

            numeroActualDelBus++;
        }

        ListaEnlazadaUsuario listaEnlazadaDeUsuarios
                = construirListaDeUsuarios();

        return new ConfigSistema(nombreDeLaTerminal, cantidadTotalDeBuses, listaEnlazadaDeBuses, listaEnlazadaDeUsuarios, null);
    }

    private static ListaEnlazadaUsuario construirListaDeUsuarios() throws Exception {

        ListaEnlazadaUsuario listaEnlazadaDeUsuarios
                = new ListaEnlazadaUsuario();

        int cantidadTotalDeUsuarios
                = solicitarEnteroMinimo("Cuantos usuarios desea crear? (mínimo 2):", 2);

        int contadorDeUsuarios = 1;

        while (contadorDeUsuarios <= cantidadTotalDeUsuarios) {

            String nombreDeUsuario
                    = solicitarTextoNoVacio("Usuario #" + contadorDeUsuarios + " - nombre:");

            String contrasenaDelUsuario
                    = solicitarTextoNoVacio("Usuario #" + contadorDeUsuarios + " - contraseña:");

            listaEnlazadaDeUsuarios.agregarUsuarioAlFinal(
                    new Usuario(nombreDeUsuario, contrasenaDelUsuario)
            );

            contadorDeUsuarios++;
        }

        return listaEnlazadaDeUsuarios;
    }

    public void ejecutarInicioDeSesion() throws Exception {

        boolean inicioCorrecto = false;
        int intentos = 0;

        while (!inicioCorrecto && intentos < 3) {

            String nombreIngresado
                    = JOptionPane.showInputDialog("LOGIN\nUsuario:");

            String contrasenaIngresada
                    = JOptionPane.showInputDialog("LOGIN\nContraseña:");

            inicioCorrecto = listaEnlazadaDeUsuarios
                    .validarCredenciales(nombreIngresado, contrasenaIngresada);

            if (!inicioCorrecto) {
                intentos++;
                JOptionPane.showMessageDialog(null,
                        "Credenciales incorrectas.\nIntento " + intentos + " de 3");
            }
        }

        if (!inicioCorrecto) {
            throw new Exception("Demasiados intentos fallidos.");
        }
    }

    public void mostrarResumen() {

        String texto = "CONFIGURACIÓN CARGADA\n\n";
        texto = texto + "Terminal: " + nombreDeLaTerminal + "\n";
        texto = texto + "Cantidad total de buses: " + cantidadTotalDeBuses + "\n";
        texto = texto + "Usuarios registrados: "
                + listaEnlazadaDeUsuarios.getCantidadTotalDeUsuarios() + "\n\n";

        NodoBus nodoActual = listaEnlazadaDeBuses.getNodoCabezaDeBuses();

        while (nodoActual != null) {
            Bus busActual = nodoActual.getDatoDelBus();

            texto = texto + "Bus #"
                    + busActual.getNumeroIdentificadorDelBus()
                    + " | Tipo: "
                    + busActual.getTipoDeBus() + "\n";

            nodoActual = nodoActual.getSiguienteNodoDeBus();
        }

        JOptionPane.showMessageDialog(null, texto);
    }

    private static String solicitarTextoNoVacio(String mensaje) throws Exception {
        while (true) {
            String texto = JOptionPane.showInputDialog(mensaje);
            if (texto == null) {
                throw new Exception("Operacion cancelada");
            }
            texto = texto.trim();
            if (!texto.equals("")) {
                return texto;
            }
        }
    }

    private static int solicitarEnteroMinimo(String mensaje, int minimo) throws Exception {
        while (true) {
            String texto = JOptionPane.showInputDialog(mensaje);
            if (texto == null) {
                throw new Exception("Operacion cancelada");
            }
            try {
                int numero = Integer.parseInt(texto.trim());
                if (numero >= minimo) {
                    return numero;
                }
            } catch (Exception e) {
            }
        }
    }

    public void agregarTiquete(Tiquete tiqueteNuevo) {

    NodoTiquete nuevoNodo = new NodoTiquete(tiqueteNuevo, null);

    if (nodoTiquetes == null) {
        nodoTiquetes = nuevoNodo;
        return;
    }

    NodoTiquete actual = nodoTiquetes;
    while (actual.getSiguienteNodoTiquete() != null) {
        actual = actual.getSiguienteNodoTiquete();
    }
    actual.setSiguienteNodoTiquete(nuevoNodo);
    }
    
    public void creacionDeTiquete() throws Exception {

    String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
    if (nombre == null) return;

    String id = JOptionPane.showInputDialog("Ingrese el ID:");
    if (id == null) return;

    String edadTexto = JOptionPane.showInputDialog("Ingrese la edad:");
    if (edadTexto == null) return;

    int edad = Integer.parseInt(edadTexto);

    String moneda = JOptionPane.showInputDialog("Moneda (CRC/USD):");
    if (moneda == null) return;

    String servicio = JOptionPane.showInputDialog("Servicio:");
    if (servicio == null) return;

    String tipoTexto = JOptionPane.showInputDialog("Tipo de bus (P/D/N):");
    if (tipoTexto == null) return;

    char tipo = tipoTexto.toUpperCase().charAt(0);

    String hora = java.time.LocalDateTime.now().toString();

    Tiquete nuevo = new Tiquete(nombre, id, edad, moneda, servicio, tipo, hora);

    agregarTiquete(nuevo);
    
    TiqueteJson.guardarTiquetes("tiquetes.json", nodoTiquetes);

    JOptionPane.showMessageDialog(null, "Tiquete creado correctamente.");
}

}
