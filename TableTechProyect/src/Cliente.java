import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clase cliente: Esta clase es la encargada de interactuar con el servidor
 */

public class Cliente {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void enviarMensaje(String mensaje) throws IOException {
        out.println(mensaje);
    }

    public String recibirMensaje() throws IOException {
        return in.readLine();
    }

    public void cerrarConexion() throws IOException {
        out.close();
        in.close();
        socket.close();
    }
    public void conectar() throws IOException {
        // Conectarse al servidor
        socket = new Socket("localhost", 1234);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Cliente conectado");
    }
    public boolean iniciarSesion(String correo, String contrasena) throws IOException {
        // Enviar el correo y la contraseña al servidor
        out.println("INICIAR_SESION");
        out.println(correo);
        out.println(contrasena);

        // Esperar la respuesta del servidor
        String respuesta = in.readLine();

        if (respuesta.equals("OK_ADMIN")) {
            return true;
        } else if (respuesta.equals("OK_CLIENTE")) {
            return true;
        } else {
            return false;
        }
    }
}
