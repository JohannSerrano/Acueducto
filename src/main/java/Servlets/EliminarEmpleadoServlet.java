package Servlets;

import modelo.GestionEmpleados;
import modelo.NodoEmpleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EliminarEmpleadoServlet", urlPatterns = {"/EliminarEmpleadoServlet"})
public class EliminarEmpleadoServlet extends HttpServlet {

    private GestionEmpleados gestionEmpleados;

    @Override
    public void init() throws ServletException {
        super.init();
        gestionEmpleados = new GestionEmpleados();  // Gestión de empleados
    }

  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Obtener la sesión actual
    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute("nombreEmpleado") == null) {
        // Si no hay sesión o no hay un empleado logueado, redirigir al login
        response.sendRedirect("loginEmpleado.jsp");
        return;
    }

    // Obtener el id del empleado a eliminar desde el formulario
    int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));

    // Leer el archivo de empleados para obtener la lista de empleados
    String rutaCompleta = getServletContext().getRealPath("/WEB-INF/empleados.txt");
    gestionEmpleados.leerEmpleadosDesdeArchivo(rutaCompleta); // Cargar la lista de empleados

    // Eliminar el empleado usando el método de la clase
    boolean eliminado = gestionEmpleados.eliminarEmpleadoPorId(idEmpleado);

    if (eliminado) {
        // Escribir la lista actualizada de empleados de vuelta en el archivo
        gestionEmpleados.escribirEmpleadosEnArchivo(rutaCompleta); // Escribir la lista

        // Establecer el mensaje que será mostrado
        request.setAttribute("mensaje", "Empleado eliminado exitosamente.");
    } else {
        // Si el empleado no fue encontrado
        request.setAttribute("mensaje", "No se encontró el empleado a eliminar.");
    }

    // Redirigir a la página de empleado con mensaje
    request.getRequestDispatcher("empleado.jsp").forward(request, response);
}
}
