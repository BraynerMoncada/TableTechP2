import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que controla las aplicacion inicial
 */
public class appController {


    @FXML
    private Button myButton;

    @FXML
    /**
     * Se la da funcionalidad al boton iniciar sesion
     * En este boton se hace la conexion del cliente con el servidor
     */
    private void handleButtonAction(ActionEvent event) {
        // Creamos un nuevo hilo para el servidor
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                try {
                    ServerApp.main(new String[0]);
                } catch (IOException e) {
                    // Manejar la excepción aquí
                    e.printStackTrace();
                }
            }
        });

        // Creamos un nuevo hilo para el cliente
        Thread clientThread = new Thread(new Runnable() {
            public void run() {
                try {
                    Cliente.main(new String[0]);
                } catch (IOException e) {
                    // Manejar la excepción aquí
                    e.printStackTrace();
                }
            }
        });

        // Iniciamos ambos hilos
        serverThread.start();
        clientThread.start();
    }

}
