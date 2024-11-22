package Servlets;

import modelo.GestionEmpleados;
import modelo.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;

@WebServlet("/EditarEmpleadoServlet")
public class EditarEmpleadoServlet extends HttpServlet {
    private GestionEmpleados gestionEmpleados;

    public EditarEmpleadoServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        gestionEmpleados = new GestionEmpleados();
        // Cargar empleados desde un archivo al iniciar el servlet
        String rutaArchivo = getServletContext().getRealPath("/WEB-INF/empleados.txt");
        try {
            gestionEmpleados.leerEmpleadosDesdeArchivo(rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al cargar empleados: " + e.getMessage());
        }
    }

   // Método doPost para manejar la edición de un empleado
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idStr = request.getParameter("id");
    String nuevoNombre = request.getParameter("nuevoNombre");
    String nuevoApellido = request.getParameter("nuevoApellido");
    String nuevoCargo = request.getParameter("nuevoCargo");

    if (idStr != null && !idStr.isEmpty()) {
        int id = Integer.parseInt(idStr);
        Empleado empleado = gestionEmpleados.buscarEmpleadoPorId(id); // Buscar empleado por ID

        if (empleado != null) {
            // Crear un nuevo objeto Empleado con los datos actualizados
            Empleado empleadoActualizado = new Empleado();
            empleadoActualizado.setId(id);
            empleadoActualizado.setNombre(nuevoNombre);
            empleadoActualizado.setApellido(nuevoApellido);
            empleadoActualizado.setCargo(nuevoCargo);

            // Utiliza el método editarEmpleado para actualizar la lista
            if (gestionEmpleados.editarEmpleado(empleadoActualizado)) {
                // Guardar los cambios en el archivo
                String rutaArchivo = getServletContext().getRealPath("/WEB-INF/empleados.txt");
                gestionEmpleados.escribirEmpleadosEnArchivo(rutaArchivo); // Escribe la lista actualizada en el archivo

                // Actualizar la sesión con los nuevos valores
                HttpSession session = request.getSession();
                session.setAttribute("nombreEmpleado", nuevoNombre);
                session.setAttribute("apellidoEmpleado", nuevoApellido);
                session.setAttribute("cargoEmpleado", nuevoCargo);

                // Redirigir con mensaje de éxito
                request.setAttribute("mensaje", "Empleado editado exitosamente.");
                response.sendRedirect("empleado.jsp");
            } else {
                request.setAttribute("mensaje", "Error al editar el empleado.");
                request.getRequestDispatcher("servicios.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mensaje", "Empleado no encontrado.");
            request.getRequestDispatcher("servicios.jsp").forward(request, response);
        }
    } else {
        request.setAttribute("mensaje", "ID no proporcionado.");
        request.getRequestDispatcher("servicios.jsp").forward(request, response);
    }
}
}