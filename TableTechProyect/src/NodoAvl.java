public class NodoAvl {
    private int valor;
    private int altura;
    private NodoAvl izquierdo;
    private NodoAvl derecho;

    public NodoAvl(int valor) {
        this.valor = valor;
        this.altura = 1;
        this.izquierdo = null;
        this.derecho = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAvl getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAvl izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAvl getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAvl derecho) {
        this.derecho = derecho;
    }
}
