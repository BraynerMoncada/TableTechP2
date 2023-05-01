import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que controla las aplicacion inicial
 */
public class appController {

    @FXML
    private TextField usuarioTextField;
    @FXML
    private PasswordField contrasenaPasswordField;
    private Button myButton;

    ArbolBinarioBusqueda arbolCliente = new ArbolBinarioBusqueda();
    @FXML

    /**
     * Se la da funcionalidad al boton iniciar sesion
     * En este boton se hace la conexion del cliente con el servidor
     * Y la validacion de correo
     */
    private void handleButtonAction(ActionEvent event) {
        //Lee los datos del archivo XML y los agrega al árbol binario de búsqueda
        HashMap<String, String> xmlData = XMLReader.readXML("usuarios.xml");
        for (Map.Entry<String, String> entry : xmlData.entrySet()) {
            String email = entry.getKey();
            String password = entry.getValue();
            Usuario usuario = new Usuario(email,password);


            arbolCliente.insertar(usuario);
        }
        String correo = usuarioTextField.getText();
        String contrasena = contrasenaPasswordField.getText();
        boolean puedeIniciarSesion = arbolCliente.buscar(correo, contrasena);

        if (puedeIniciarSesion) {
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

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientApp.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Contraseña o correo incorrecto\n \nPor favor intenta de nuevo");
            alert.getDialogPane().setPrefSize(400, 200); // Establecer el tamaño de la ventana en píxeles
            alert.getDialogPane().setStyle("-fx-font-size: 20; -fx-font-family: 'Arial';"); // Cambiar el tamaño y la fuente de la ventana

            alert.showAndWait();
        }

    }
}
