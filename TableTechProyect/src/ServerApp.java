import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase servidor: Encargada de interactuar con el cliente, haciendo la funcion se servidor.
 */
public class ServerApp {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Servidor conectado");
        ServerSocket serverSocket = new ServerSocket(1234, 100);
        Socket clientSocket = serverSocket.accept();

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine, outputLine;

        ArbolBinarioBusqueda arbolAdmin = new ArbolBinarioBusqueda();
        //Lee los datos del archivo XML y los agrega al árbol binario de búsqueda
        HashMap<String, String> xmlData = XMLReader.readXML("admin.xml");
        for (Map.Entry<String, String> entry : xmlData.entrySet()) {
            String email = entry.getKey();
            String password = entry.getValue();
            Usuario usuario = new Usuario(email, password);

            arbolAdmin.insertar(usuario);
        }

        ArbolBinarioBusqueda arbolUsuarios = new ArbolBinarioBusqueda();
        //Lee los datos del archivo XML y los agrega al árbol binario de búsqueda
        HashMap<String, String> xmlDataUsuarios = XMLReader.readXML("usuarios.xml");
        for (Map.Entry<String, String> entry : xmlDataUsuarios.entrySet()) {
            String email = entry.getKey();
            String password = entry.getValue();
            Usuario usuario = new Usuario(email, password);

            arbolUsuarios.insertar(usuario);
        }
        String mensaje = in.readLine(); // Lee el mensaje enviado por el cliente
        String[] partes = mensaje.split(";"); // Divide el mensaje en partes separadas por ";"

        if (partes.length == 3 && partes[0].equals("INICIAR_SESION")) { // Verifica que el mensaje tenga el formato correcto
            String correo = partes[1];
            String contrasena = partes[2];
            boolean puedeIniciarSesion = arbolUsuarios.buscar(correo, contrasena);
            boolean puedeIniciarSesionAdmin = arbolAdmin.buscar(correo, contrasena);
            System.out.println(partes[1] +" " + partes[2]);

            if (puedeIniciarSesion) {
                out.println("OK_CLIENTE");
            } else if (puedeIniciarSesionAdmin) {
                out.println("OK_ADMIN");

            } else {
                out.println("NO_VALIDO");
            }


        }
        // Recorrer la lista de platillos y agregarlos al árbol AVL

        ArbolAvl arbolPlatillos = new ArbolAvl();
        JSONObject datos = (JSONObject) JsonReader.leerArchivo("platillos.json");
        for (Object clave : datos.keySet()) {
            Object valorObj = datos.get(clave);
            if (valorObj instanceof String) {
                String valor = (String) valorObj;
                int numero = Integer.parseInt(valor);
                arbolPlatillos.insertar(numero);
            } else if (valorObj instanceof JSONArray) {
                JSONArray valorArray = (JSONArray) valorObj;
            }
        }

    }
}


