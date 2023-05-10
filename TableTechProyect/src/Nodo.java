/**
 * Clase nodo de el arbol binario AVL
 */
class Nodo {
    private Usuario usuario;
    private Nodo hijoIzquierdo;
    private Nodo hijoDerecho;
    private int altura;

    public Nodo(Usuario usuario) {
        this.usuario = usuario;
        this.altura = 1;
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

    public int getAltura() {
        return altura;
    }

    public int getBalance() {
        int balance = 0;
        if (hijoIzquierdo != null) {
            balance -= hijoIzquierdo.altura();
        }
        if (hijoDerecho != null) {
            balance += hijoDerecho.altura();
        }
        return balance;
    }

    public int altura() {
        int alturaIzquierdo = 0;
        int alturaDerecho = 0;
        if (hijoIzquierdo != null) {
            alturaIzquierdo = hijoIzquierdo.altura();
        }
        if (hijoDerecho != null) {
            alturaDerecho = hijoDerecho.altura();
        }
        return Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }

    public void actualizarAltura() {
        altura = altura();
    }
    /**
     * Realiza una rotación a la derecha del subárbol con raíz en este nodo.
     *
     * @return La nueva raíz del subárbol.
     */
    public Nodo rotacionDerecha() {
        Nodo nuevoPadre = this.hijoIzquierdo;
        this.hijoIzquierdo = nuevoPadre.hijoDerecho;
        nuevoPadre.hijoDerecho = this;
        this.actualizarAltura();
        nuevoPadre.actualizarAltura();
        return nuevoPadre;
    }

    /**
     * Realiza una rotación a la izquierda del subárbol con raíz en este nodo.
     *
     * @return La nueva raíz del subárbol.
     */
    public Nodo rotacionIzquierda() {
        Nodo nuevoPadre = this.hijoDerecho;
        this.hijoDerecho = nuevoPadre.hijoIzquierdo;
        nuevoPadre.hijoIzquierdo = this;
        this.actualizarAltura();
        nuevoPadre.actualizarAltura();
        return nuevoPadre;
    }

    /**
     * Realiza una doble rotación a la derecha del subárbol con raíz en este nodo.
     *
     * @return La nueva raíz del subárbol.
     */
    public Nodo rotacionDobleDerecha() {
        this.hijoIzquierdo = hijoIzquierdo.rotacionIzquierda();
        return this.rotacionDerecha();
    }

    /**
     * Realiza una doble rotación a la izquierda del subárbol con raíz en este nodo.
     *
     * @return La nueva raíz del subárbol.
     */
    public Nodo rotacionDobleIzquierda() {
        this.hijoDerecho = hijoDerecho.rotacionDerecha();
        return this.rotacionIzquierda();
    }
}

