/**
 * Clase nodo de el arbol
 */
class Nodo {
    private Usuario usuario;
    private Nodo hijoIzquierdo;
    private Nodo hijoDerecho;

    public Nodo(Usuario usuario) {
        this.usuario = usuario;
    }

    public Nodo(String email, String password) {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Nodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Nodo getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
}