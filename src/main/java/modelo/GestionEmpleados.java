package modelo;

import java.io.*;

public class GestionEmpleados {
    private NodoEmpleado cabeza;

    public GestionEmpleados() {
        cabeza = null;
    }

    // Agregar empleado al final de la lista
    public void agregarEmpleado(NodoEmpleado nuevoNodo) {
        if (cabeza == null) {
            cabeza = nuevoNodo; // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
        } else {
            NodoEmpleado actual = cabeza;
            // Recorrer la lista hasta el último nodo
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo); // Enlazar el nuevo nodo al final de la lista
        }
    }

    public void leerEmpleadosDesdeArchivo(String rutaCompleta) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 4) { // Asegúrate de que hay suficientes campos
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String apellido = datos[2];
                    String cargo = datos[3];
                    Empleado empleado = new Empleado();
                    empleado.setId(id);
                    empleado.setNombre(nombre);
                    empleado.setApellido(apellido);
                    empleado.setCargo(cargo);
                    NodoEmpleado nuevoNodo = new NodoEmpleado(empleado);
                    agregarEmpleado(nuevoNodo); // Agregar el nuevo nodo a la lista
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró. Iniciaremos con una lista de empleados vacía.");
        }
    }

    public void escribirEmpleadosEnArchivo(String rutaCompleta) throws IOException {
        try (PrintWriter pluma = new PrintWriter(new FileWriter(rutaCompleta))) {
            NodoEmpleado actual = cabeza;
            while (actual != null) {
                Empleado empleado = actual.getEmpleado();
                pluma.println(empleado.getId() + "," + empleado.getNombre() + "," + empleado.getApellido() + "," + empleado.getCargo());
                actual = actual.getSiguiente(); // Mover al siguiente nodo
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            throw e; // Vuelve a lanzar la excepción para manejarla en el servlet si es necesario
        }
    }

    public Empleado buscarEmpleadoPorId(int id) {
        NodoEmpleado actual = cabeza;
        while (actual != null) {
            if (actual.getEmpleado().getId() == id) {
                return actual.getEmpleado();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean eliminarEmpleadoPorId(int id) {
        NodoEmpleado actual = cabeza;
        NodoEmpleado anterior = null;

        while (actual != null) {
            if (actual.getEmpleado().getId() == id) {
                if (anterior == null) {
                    // Eliminar el nodo cabeza
                    cabeza = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                return true; // Empleado eliminado
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false; // Empleado no encontrado
    }

    public boolean editarEmpleado(Empleado empleadoActualizado) {
        NodoEmpleado actual = cabeza;
        while (actual != null) {
            if (actual.getEmpleado().getId() == empleadoActualizado.getId()) {
                actual.setEmpleado(empleadoActualizado); // Actualiza el nodo con el nuevo empleado
                return true; // Empleado editado
            }
            actual = actual.getSiguiente();
        }
        return false; // Empleado no encontrado
    }

    // Método para obtener la cabeza
    public NodoEmpleado getCabeza() {
        return cabeza;
    }

    // Método para establecer la cabeza (necesario para eliminar)
    public void setCabeza(NodoEmpleado nuevaCabeza) {
        this.cabeza = nuevaCabeza;
    }
}
