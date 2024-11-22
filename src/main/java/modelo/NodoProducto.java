package modelo;

public class NodoProducto {

    private Productos producto;
    private NodoProducto siguiente;
    private NodoProducto previo;

    // Constructor que inicializa el nodo con un producto
    public NodoProducto(Productos producto) {
        this.producto = producto;
        this.siguiente = null;
        this.previo = null;
    }

    // Constructor vacío (opcional, si realmente se necesita)
    public NodoProducto() {
    }

    // Getter para el producto
    public Productos getProducto() {
        return producto;
    }

    // Setter para el producto
    public void setProducto(Productos producto) {
        this.producto = producto; // Asigna el nuevo producto al nodo
    }

    // Getter para el siguiente nodo
    public NodoProducto getSiguiente() {
        return siguiente;
    }

    // Setter para el siguiente nodo
    public void setSiguiente(NodoProducto siguiente) {
        this.siguiente = siguiente;
    }

    // Getter para el nodo previo
    public NodoProducto getPrevio() {
        return previo;
    }

    // Setter para el nodo previo
    public void setPrevio(NodoProducto previo) {
        this.previo = previo;
    }
}