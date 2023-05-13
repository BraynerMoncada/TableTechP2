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

/**

 Clase que controla las aplicaciones iniciales
 */
public class appController {

    @FXML
    private TextField usuarioTextField;
    @FXML
    private PasswordField contrasenaPasswordField;

    Cliente clienteActual = new Cliente();

    @FXML
    public void handlebuttonActionConecction(ActionEvent event) throws IOException {
        // Establecer una conexión con el servidor
        clienteActual.conectar();
    }

    /**

     Se le da funcionalidad al botón iniciar sesión

     En este botón se hace la conexión del cliente con el servidor

     Y la validación de correo
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

// Enviar los datos de correo y contraseña al servidor
        String correo = usuarioTextField.getText();
        String contrasena = contrasenaPasswordField.getText();
        String mensaje = "INICIAR_SESION;" + correo + ";" + contrasena;
        clienteActual.enviarMensaje(mensaje);

// Recibir la respuesta del servidor y realizar la validación de inicio de sesión
        String respuesta = clienteActual.recibirMensaje();
        if (respuesta.equals("OK_ADMIN")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminApp.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (respuesta.equals("OK_CLIENTE")) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Contraseña o correo incorrecto\n \nPor favor intenta de nuevo");
            alert.getDialogPane().setPrefSize(400, 200); // Establecer el tamaño de la ventana en píxeles
            alert.getDialogPane().setStyle("-fx-font-size: 20; -fx-font-family: 'Arial';"); // Cambiar el tamaño y la fuente de la ventana
            alert.showAndWait();
        }

        // Cerrar la conexión con el servidor
        clienteActual.cerrarConexion();
    }
}