public class ArbolAvl {
    private NodoAvl raiz;

    public ArbolAvl() {
        this.raiz = null;
    }
    public NodoAvl getRaiz() {
        return raiz;
    }

    /**
     *
     * @param valor
     */
    public void insertar(int valor) {
        this.raiz = insertar(this.raiz, valor);
    }

    /**
     *
     * @param nodo
     * @param valor
     * @return
     */
    private NodoAvl insertar(NodoAvl nodo, int valor) {
        if (nodo == null) {
            return new NodoAvl(valor);
        }

        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), valor));
        } else {
            nodo.setDerecho(insertar(nodo.getDerecho(), valor));
        }

        int factorBalanceo = factorBalanceo(nodo);

        if (factorBalanceo > 1) {
            if (valor < nodo.getIzquierdo().getValor()) {
                nodo = rotacionDerecha(nodo);
            } else {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                nodo = rotacionDerecha(nodo);
            }
        } else if (factorBalanceo < -1) {
            if (valor > nodo.getDerecho().getValor()) {
                nodo = rotacionIzquierda(nodo);
            } else {
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                nodo = rotacionIzquierda(nodo);
            }
        } else {
            nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));
        }

        return nodo;
    }

    /**
     *
     * @param valor
     */
    public void eliminar(int valor) {
        this.raiz = eliminar(this.raiz, valor);
    }

    /**
     *
     * @param nodo
     * @param valor
     * @return
     */
    private NodoAvl eliminar(NodoAvl nodo, int valor) {
        if (nodo == null) {
            return null;
        }

        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(eliminar(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecho(eliminar(nodo.getDerecho(), valor));
        } else {
            if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                NodoAvl temp = null;

                if (nodo.getIzquierdo() == null) {
                    temp = nodo.getDerecho();
                } else {
                    temp = nodo.getIzquierdo();
                }

                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                NodoAvl temp = minimoValor(nodo.getDerecho());
                nodo.setValor(temp.getValor());
                nodo.setDerecho(eliminar(nodo.getDerecho(), temp.getValor()));
            }
        }

        if (nodo == null) {
            return null;
        }

        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));

        int factorBalanceo = factorBalanceo(nodo);

        if (factorBalanceo > 1) {
            if (factorBalanceo(nodo.getIzquierdo()) >= 0) {
                nodo = rotacionDerecha(nodo);
            } else {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                nodo = rotacionDerecha(nodo);
            }
        } else if (factorBalanceo < -1) {
            if (factorBalanceo(nodo.getDerecho()) <= 0) {
                nodo = rotacionIzquierda(nodo);
            } else {
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                nodo = rotacionIzquierda(nodo);
            }
        }

        return nodo;
    }

    /**
     *
     * @param nodo
     * @return
     */

    private NodoAvl minimoValor(NodoAvl nodo) {
        NodoAvl actual = nodo;

        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }

        return actual;
    }

    /**
     * Altura del nodo
     * @param nodo
     * @return
     */
    private int altura(NodoAvl nodo) {
        if (nodo == null) {
            return 0;
        }

        return nodo.getAltura();
    }
    /**

     Calcula el factor de balanceo del nodo especificado.

     @param nodo el nodo para el cual se calculará el factor de balanceo.

     @return el factor de balanceo del nodo especificado.

     Si el nodo es null, el método devuelve 0.
     */
    private int factorBalanceo(NodoAvl nodo) {
        if (nodo == null) {
            return 0;
        }

        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }
    /**

     Realiza una rotación hacia la izquierda en un subárbol de un nodo AVL.
     @param nodo El nodo a partir del cual se realizará la rotación.
     @return El nuevo nodo que se convierte en la raíz del subárbol después de la rotación.
     */
    private NodoAvl rotacionIzquierda(NodoAvl nodo) {
        NodoAvl nuevoPadre = nodo.getDerecho();
        NodoAvl temp = nuevoPadre.getIzquierdo();
        nuevoPadre.setIzquierdo(nodo);
        nodo.setDerecho(temp);
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));
        nuevoPadre.setAltura(1 + Math.max(altura(nuevoPadre.getIzquierdo()), altura(nuevoPadre.getDerecho())));
        return nuevoPadre;
    }

    /**
     * Realiza una rotación hacia la derecha del subárbol que tiene como raíz el nodo especificado.
     *
     * @param nodo el nodo en torno al cual se realiza la rotación.
     * @return el nuevo nodo raíz del subárbol rotado.
     */
    private NodoAvl rotacionDerecha(NodoAvl nodo) {
        NodoAvl nuevoPadre = nodo.getIzquierdo();
        NodoAvl temp = nuevoPadre.getDerecho();
        nuevoPadre.setDerecho(nodo);
        nodo.setIzquierdo(temp);
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));
        nuevoPadre.setAltura(1 + Math.max(altura(nuevoPadre.getIzquierdo()), altura(nuevoPadre.getDerecho())));
        return nuevoPadre;
    }
}
