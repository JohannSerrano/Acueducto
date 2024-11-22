package Servlets;

import modelo.Empleado;
import modelo.GestionEmpleados;
import modelo.GestionProductos;
import modelo.NodoProducto;
import modelo.NodoEmpleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "LoginEmpleadoServlet", urlPatterns = {"/LoginEmpleadoServlet"})
public class LoginEmpleadoServlet extends HttpServlet {

    private GestionEmpleados gestionEmpleados;
    private GestionProductos gestionProductos;

    @Override
    public void init() throws ServletException {
        super.init();
        gestionEmpleados = new GestionEmpleados();
        gestionProductos = new GestionProductos();

        // Cargar empleados desde el archivo al iniciar el servlet
        String rutaCompletaEmpleados = getServletContext().getRealPath("/WEB-INF/empleados.txt");
        try {
            gestionEmpleados.leerEmpleadosDesdeArchivo(rutaCompletaEmpleados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Recargar empleados desde el archivo al iniciar el servlet
    String rutaCompletaEmpleados = getServletContext().getRealPath("/WEB-INF/empleados.txt");
    gestionEmpleados.leerEmpleadosDesdeArchivo(rutaCompletaEmpleados);

    // Comprobar si la lista de empleados está vacía
    NodoEmpleado cabeza = gestionEmpleados.getCabeza();
    if (cabeza == null) {
        request.setAttribute("error", "No hay empleados registrados");
        request.getRequestDispatcher("loginEmpleado.jsp").forward(request, response);
        return;
    }

    // Obtener los parámetros del formulario
    String nombreIngresado = request.getParameter("nombre");
    String codigoIngresado = request.getParameter("codigo");
    
    boolean empleadoValido = false;

    // Validar las credenciales del empleado
    NodoEmpleado actual = cabeza;
    while (actual != null) {
        if (actual.getEmpleado().getNombre().equals(nombreIngresado)
                && String.valueOf(actual.getEmpleado().getId()).equals(codigoIngresado)) {
            empleadoValido = true;
            break;
        }
        actual = actual.getSiguiente();
    }

    // Manejo de la respuesta según la validación
    if (empleadoValido) {
        HttpSession session = request.getSession();
        session.setAttribute("loggedInEmpleado", true);
        session.setAttribute("nombreEmpleado", nombreIngresado);
        session.setAttribute("idEmpleado", codigoIngresado);
    session.setAttribute("apellidoEmpleado", actual.getEmpleado().getApellido()); // Agrega esto
session.setAttribute("cargoEmpleado", actual.getEmpleado().getCargo());

        // Cargar la lista de productos y pasarlo a servicios.jsp
        String rutaCompletaProductos = getServletContext().getRealPath("/WEB-INF/productos.txt");
        gestionProductos.leerProductosDesdeArchivo(rutaCompletaProductos);
        NodoProducto cabezaProductos = gestionProductos.getListaProductos();

        request.setAttribute("cabezaProductos", cabezaProductos);
        request.getRequestDispatcher("servicios.jsp").forward(request, response);
    } else {
        request.setAttribute("error", "Empleado o código incorrecto");
        request.getRequestDispatcher("loginEmpleado.jsp").forward(request, response);
    }
}

}