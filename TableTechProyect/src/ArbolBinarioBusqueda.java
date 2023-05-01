/**
 * Clase para crear arbol binario
 */
public class ArbolBinarioBusqueda {
    private Nodo raiz;

    public void insertar(Usuario usuario) {
        if (raiz == null) {
            raiz = new Nodo(usuario);
        } else {
            insertarEnSubarbol(raiz, usuario);
        }
    }

    /**
     * En este metodo se realiza la logica de insersion
     * @param nodo
     * @param usuario
     */

    private void insertarEnSubarbol(Nodo nodo, Usuario usuario) {
        if (usuario.getCorreo().compareTo(nodo.getUsuario().getCorreo()) < 0) {
            if (nodo.getHijoIzquierdo() == null) {
                nodo.setHijoIzquierdo(new Nodo(usuario));
            } else {
                insertarEnSubarbol(nodo.getHijoIzquierdo(), usuario);
            }
        } else {
            if (nodo.getHijoDerecho() == null) {
                nodo.setHijoDerecho(new Nodo(usuario));
            } else {
                insertarEnSubarbol(nodo.getHijoDerecho(), usuario);
            }
        }
    }

    /**
     *
     * @param correo
     * @param contrasena
     * @return
     */
    public boolean buscar(String correo, String contrasena) {
        return buscarEnSubarbol(raiz, correo, contrasena);
    }

    /**
     *
     * @param nodo
     * @param correo
     * @param contrasena
     * @return
     */
    private boolean buscarEnSubarbol(Nodo nodo, String correo, String contrasena) {
        if (nodo == null) {
            return false;
        }

        Usuario usuario = nodo.getUsuario();

        if (correo.equals(usuario.getCorreo()) && contrasena.equals(usuario.getContrasena())) {
            return true;
        } else if (correo.compareTo(usuario.getCorreo()) < 0) {
            return buscarEnSubarbol(nodo.getHijoIzquierdo(), correo, contrasena);
        } else {
            return buscarEnSubarbol(nodo.getHijoDerecho(), correo, contrasena);
        }
    }
}


