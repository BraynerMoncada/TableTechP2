import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Clase main que ejecuta la aplicacion
 */
public class Main extends Application {



    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        primaryStage.setTitle("TableTech");
        primaryStage.setScene(new Scene(root, 335, 258));
        primaryStage.show();

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
        serverThread.start();
    }




}