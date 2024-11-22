package Servlets;

import modelo.GestionUsuarios;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private GestionUsuarios gestionUsuarios;

    @Override
    public void init() throws ServletException {
        super.init();
        gestionUsuarios = new GestionUsuarios();
        try {
            String rutaCompleta = getServletContext().getRealPath("/WEB-INF/usuarios.txt");
            LinkedList<Usuario> usuarios = gestionUsuarios.leerUsuariosDesdeArchivo(rutaCompleta);
            getServletContext().setAttribute("usuarios", usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LinkedList<Usuario> usuarios = (LinkedList<Usuario>) getServletContext().getAttribute("usuarios");

        if (usuarios == null) {
            usuarios = new LinkedList<>();
            getServletContext().setAttribute("usuarios", usuarios);
        }

        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String cedula = request.getParameter("cedula");
        String direccion = request.getParameter("direccion");
        String estrato = request.getParameter("estrato");
        String ciudad = request.getParameter("ciudad");
        String codigoPostal = request.getParameter("codigo_postal");

        // Obtener el código más alto actual en la lista de usuarios
        int codigoUsuario = 1;  // Valor por defecto en caso de que la lista esté vacía.
        if (!usuarios.isEmpty()) {
            // Encontrar el código más alto
            for (Usuario usuario : usuarios) {
                if (usuario.getCodigo() > codigoUsuario) {
                    codigoUsuario = usuario.getCodigo();
                }
            }
            // Sumar 1 al código más alto
            codigoUsuario++;
        }

        // Crear un nuevo usuario con el ID generado
        Usuario nuevoUsuario = new Usuario(nombre, apellidos, cedula, direccion, estrato, ciudad, codigoPostal, codigoUsuario);
        usuarios.add(nuevoUsuario);

        // Guardar la lista actualizada en el archivo de texto
        String rutaCompletaTxt = getServletContext().getRealPath("/WEB-INF/usuarios.txt");
        gestionUsuarios.escribirUsuariosEnArchivo(usuarios, rutaCompletaTxt);

        // Serializar la lista de usuarios en el archivo .data
        String rutaCompletaData = getServletContext().getRealPath("/WEB-INF/usuarios.data");
        gestionUsuarios.serializarUsuarios(usuarios, rutaCompletaData);

        // Establecer atributos para mostrar en la vista
        request.setAttribute("nombre", nombre);
        request.setAttribute("codigoUsuario", codigoUsuario);

        // Redirigir al usuario a la página de inicio
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
