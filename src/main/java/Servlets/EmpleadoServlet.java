package Servlets;

import modelo.GestionEmpleados;
import modelo.Empleado;
import modelo.NodoEmpleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/EmpleadoServlet"})
public class EmpleadoServlet extends HttpServlet {

    private GestionEmpleados gestionEmpleados;

    @Override
    public void init() throws ServletException {
        super.init();
        gestionEmpleados = new GestionEmpleados();
        try {
            String rutaCompleta = getServletContext().getRealPath("/WEB-INF/empleados.txt");
            gestionEmpleados.leerEmpleadosDesdeArchivo(rutaCompleta);
            getServletContext().setAttribute("gestionEmpleados", gestionEmpleados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener la gestión de empleados desde el contexto de la aplicación
        gestionEmpleados = (GestionEmpleados) getServletContext().getAttribute("gestionEmpleados");

        // Si no hay gestión de empleados, se vuelve a inicializar (aunque esto no debería pasar)
        if (gestionEmpleados == null) {
            gestionEmpleados = new GestionEmpleados();
            String rutaCompleta = getServletContext().getRealPath("/WEB-INF/empleados.txt");
            gestionEmpleados.leerEmpleadosDesdeArchivo(rutaCompleta);
            getServletContext().setAttribute("gestionEmpleados", gestionEmpleados);
        }

        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cargo = request.getParameter("cargo");

        // Generar ID automáticamente, sumando uno al ID más alto de la lista
        int id = 0; // Default ID en caso de que no haya empleados

        // Buscar el ID máximo en la lista
        NodoEmpleado actual = gestionEmpleados.getCabeza();
        while (actual != null) {
            id = Math.max(id, actual.getEmpleado().getId());
            actual = actual.getSiguiente();
        }
        id++; // Incrementar para el nuevo empleado

        // Crear nuevo empleado
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setId(id);
        nuevoEmpleado.setNombre(nombre);
        nuevoEmpleado.setApellido(apellido);
        nuevoEmpleado.setCargo(cargo);

        // Crear un nuevo nodo y agregarlo a la lista
        NodoEmpleado nuevoNodo = new NodoEmpleado(nuevoEmpleado);
        gestionEmpleados.agregarEmpleado(nuevoNodo);

        // Guardar lista actualizada en el archivo
        String rutaCompleta = getServletContext().getRealPath("/WEB-INF/empleados.txt");
        gestionEmpleados.escribirEmpleadosEnArchivo(rutaCompleta);

        // Redireccionar o mostrar los resultados
        request.setAttribute("nombreEmpleado", nombre);
        request.setAttribute("idEmpleado", id);
        request.getRequestDispatcher("empleado.jsp").forward(request, response);
    }
}
