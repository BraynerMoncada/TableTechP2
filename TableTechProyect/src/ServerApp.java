import java.io.*;
import java.net.*;

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
