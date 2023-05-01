/**
 * Esta clase es usada para crear las cuentas que se van a almacenar en el arbol binario de busqueda
 * @author BraynerMoncada
 */
public class Usuario {
    private String correo;
    private String contrasena;

    public Usuario(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}
