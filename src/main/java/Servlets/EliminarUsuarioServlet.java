package Servlets;

import modelo.GestionUsuarios;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "EliminarUsuarioServlet", urlPatterns = {"/EliminarUsuarioServlet"})
public class EliminarUsuarioServlet extends HttpServlet {

    private GestionUsuarios gestionUsuarios;

    @Override
    public void init() throws ServletException {
        super.init();
        gestionUsuarios = new GestionUsuarios();
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Obtener la sesión actual
    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute("codigoUsuario") == null) {
        // Si no hay sesión o no hay un usuario logueado, redirigir al login
        response.sendRedirect("login.jsp");
        return;
    }

    // Obtener el código del usuario desde la sesión y convertirlo a int
    int codigoUsuario = 0;
    try {
        // Convertir a int usando Integer.parseInt()
        codigoUsuario = Integer.parseInt(session.getAttribute("codigoUsuario").toString());
    } catch (NumberFormatException e) {
        // Si no se puede convertir a int, manejar el error
        response.sendRedirect("login.jsp");
        return;
    }

    // Leer el archivo de usuarios para obtener la lista de usuarios
    String rutaCompleta = getServletContext().getRealPath("/WEB-INF/usuarios.txt");
    LinkedList<Usuario> usuarios = gestionUsuarios.leerUsuariosDesdeArchivo(rutaCompleta);

    // Buscar al usuario en la lista por su código
    Usuario usuarioAEliminar = null;
    for (Usuario usuario : usuarios) {
        if (usuario.getCodigo() == codigoUsuario) {
            usuarioAEliminar = usuario;
            break;
        }
    }

    if (usuarioAEliminar != null) {
        // Eliminar el usuario de la lista
        usuarios.remove(usuarioAEliminar);

        // Actualizar el archivo con la lista de usuarios
        gestionUsuarios.escribirUsuariosEnArchivo(usuarios, rutaCompleta);

        // Actualizar la lista en el contexto de la aplicación
        getServletContext().setAttribute("usuarios", usuarios);

        // Invalidar la sesión después de eliminar el usuario
        session.invalidate();

        // Redirigir con mensaje de éxito
        request.setAttribute("mensaje", "Usuario eliminado exitosamente.");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } else {
        // Si el usuario no fue encontrado, redirigir con mensaje de error
        request.setAttribute("mensaje", "No se encontró el usuario a eliminar.");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
}