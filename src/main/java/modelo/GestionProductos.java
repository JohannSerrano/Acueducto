package modelo;

import java.io.*;

public class GestionProductos {

    private NodoProducto cabeza;

    public GestionProductos() {
        cabeza = null;
    }

    // Agregar producto al inicio de la lista (Last In, First Out)
    public void agregarProducto(NodoProducto nuevoNodo) {
        nuevoNodo.setSiguiente(cabeza);
        if (cabeza != null) {
            cabeza.setPrevio(nuevoNodo);
        }
        cabeza = nuevoNodo;
    }

    // Leer productos desde un archivo TXT
    public void leerProductosDesdeArchivo(String rutaCompleta) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6) {  // Asegúrate de que el archivo tenga el codigoUsuario
                    String codigo = datos[0];
                    String nombreServicios = datos[1];
                    int cantidad = Integer.parseInt(datos[2]);
                    String ubicacion = datos[3];
                    double precio = Double.parseDouble(datos[4]);
                    String codigoUsuario = datos[5]; // Leer codigoUsuario

                    Productos producto = new Productos(codigo, nombreServicios, cantidad, ubicacion, codigoUsuario);
                    NodoProducto nuevoNodo = new NodoProducto(producto);
                    agregarProducto(nuevoNodo);

                    System.out.println("Producto leído: " + producto.getNombreServicios()); // Cambié el método
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró.");
        }
    }

    // Guardar productos en un archivo TXT
    public void escribirProductosEnArchivo(String rutaCompleta) throws IOException {
        try (PrintWriter pluma = new PrintWriter(new FileWriter(rutaCompleta))) {
            NodoProducto actual = cabeza;
            while (actual != null) {  // Recorre toda la lista
                Productos producto = actual.getProducto();
                pluma.println(producto.getCodigo() + "," + producto.getNombreServicios() + "," + producto.getCantidad() + "," + producto.getUbicacion() + "," + producto.getPrecio() + "," + producto.getCodigoUsuario());
                actual = actual.getSiguiente();
            }
        }
    }

    // Obtener la lista de productos
    public NodoProducto getListaProductos() {
        return cabeza;
    }

    // Método para buscar un nodo dado un producto
    public NodoProducto buscarNodo(Productos producto) {
        NodoProducto nodo = cabeza;
        while (nodo != null) {
            if (nodo.getProducto().getCodigoUsuario().equals(producto.getCodigoUsuario())) {
                return nodo;
            }
            nodo = nodo.getSiguiente();
        }
        return null; // No se encontró el nodo
    }

    // Método para eliminar un producto dado un nodo
    public void eliminarProducto(NodoProducto nodo) {
        if (nodo == null) {
            return; // Si el nodo es nulo, no hay nada que eliminar
        }

        // Si el nodo a eliminar es el primero de la lista
        if (nodo == cabeza) {
            cabeza = nodo.getSiguiente();
            if (cabeza != null) {
                cabeza.setPrevio(null); // Si hay un siguiente, se ajusta el previo
            }
        } else {
            // Si el nodo no es el primero, ajustamos los enlaces
            NodoProducto anterior = nodo.getPrevio();
            NodoProducto siguiente = nodo.getSiguiente();
            if (anterior != null) {
                anterior.setSiguiente(siguiente);
            }
            if (siguiente != null) {
                siguiente.setPrevio(anterior);
            }
        }

        nodo.setSiguiente(null); // Desvinculamos el nodo
        nodo.setPrevio(null); // Desvinculamos el nodo
    }


}
