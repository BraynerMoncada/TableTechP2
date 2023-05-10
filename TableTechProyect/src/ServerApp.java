import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase servidor: Encargada de interactuar con el cliente, haciendo la funcion se servidor.
 */
public class ServerApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Cliente conectado");
        ServerSocket serverSocket = new ServerSocket(1234,100);
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
            Usuario usuario = new Usuario(email,password);

            arbolAdmin.insertar(usuario);
        }
        String correo = in.readLine();
        String contrasena = in.readLine();
        boolean puedeIniciarSesion = arbolAdmin.buscar(correo, contrasena);



        while ((inputLine = in.readLine()) != null) {
            outputLine = "Servidor: " + inputLine;
            out.println(outputLine);
            if (outputLine.equals("bye"))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

}
