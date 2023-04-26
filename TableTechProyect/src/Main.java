import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    }

}