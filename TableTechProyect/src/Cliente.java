import java.io.*;
import java.net.*;

/**
 * Clase cliente: Esta clase es la encargada de interactuar con el servidor
 */
public class Cliente {
    public static void main(String[] args) throws IOException {
        System.out.println("Conectado al servidor");

        Socket socket = new Socket("localhost", 1234);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("Cliente: " + in.readLine());
            if (userInput.equals("bye"))
                break;
        }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
