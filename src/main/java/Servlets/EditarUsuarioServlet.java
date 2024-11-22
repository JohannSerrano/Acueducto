package Servlets;

import modelo.Usuario;
import modelo.GestionUsuarios;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para editar la información de un usuario.
 */
@WebServlet(name = "EditarUsuarioServlet", urlPatterns = {"/EditarUsuarioServlet"})
public class EditarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
        String nuevoNombre = request.getParameter("nombre");
        String nuevosApellidos = request.getParameter("apellidos");
        String nuevaCedula = request.getParameter("cedula");
        String nuevaDireccion = request.getParameter("direccion");
        String nuevoEstrato = request.getParameter("estrato");
        String nuevaCiudad = request.getParameter("ciudad");
        String nuevoCodigoPostal = request.getParameter("codigoPostal");

        LinkedList<Usuario> usuarios = (LinkedList<Usuario>) getServletContext().getAttribute("usuarios");
        GestionUsuarios gestionUsuarios = new GestionUsuarios();

        if (usuarios != null) {
            Usuario usuario = gestionUsuarios.buscarUsuarioPorCodigo(usuarios, codigoUsuario);
            
            if (usuario != null) {
                // Modificar los atributos del usuario
                if (nuevoNombre != null) usuario.setNombre(nuevoNombre);
                if (nuevosApellidos != null) usuario.setApellidos(nuevosApellidos);
                if (nuevaCedula != null) usuario.setCedula(nuevaCedula);
                if (nuevaDireccion != null) usuario.setDireccion(nuevaDireccion);
                if (nuevoEstrato != null) usuario.setEstrato(nuevoEstrato);
                if (nuevaCiudad != null) usuario.setCiudad(nuevaCiudad);
                if (nuevoCodigoPostal != null) usuario.setCodigoPostal(nuevoCodigoPostal);

                // Escribir cambios en el archivo
                String rutaCompletaTxt = getServletContext().getRealPath("/WEB-INF/usuarios.txt");
                gestionUsuarios.escribirUsuariosEnArchivo(usuarios, rutaCompletaTxt);

                // Actualizar la lista de usuarios en el ServletContext
                getServletContext().setAttribute("usuarios", usuarios);

                // Actualizar mensaje de éxito en la sesión
                request.getSession().setAttribute("mensaje", "Usuario editado exitosamente.");

                // Redirigir para cargar la página con los cambios
                response.sendRedirect("index.jsp");
            } else {
                request.getSession().setAttribute("mensaje", "Usuario no encontrado.");
                response.sendRedirect("index.jsp");
            }
        } else {
            request.getSession().setAttribute("mensaje", "Error interno: no se pudo acceder a la lista de usuarios");
            response.sendRedirect("index.jsp");
        }
    }
}
