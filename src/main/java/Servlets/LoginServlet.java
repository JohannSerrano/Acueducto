package Servlets;

import modelo.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener la lista de usuarios desde el contexto de la aplicación
        LinkedList<Usuario> usuarios = (LinkedList<Usuario>) getServletContext().getAttribute("usuarios");

        if (usuarios == null) {
            usuarios = leerUsuariosDesdeArchivo();
            getServletContext().setAttribute("usuarios", usuarios);
        }

        // Comprobar si la lista de usuarios está vacía
        if (usuarios == null || usuarios.isEmpty()) {
            request.setAttribute("error", "No hay usuarios registrados");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Obtener los parámetros del formulario
        String nombreIngresado = request.getParameter("nombre");
        String codigoIngresado = request.getParameter("codigo");
        boolean usuarioValido = false;

        // Validar las credenciales del usuario
        for (Usuario usuario : usuarios) {
            System.out.println("Usuario: " + usuario.getNombre() + ", Código: " + usuario.getCodigo()); // Depuración
            if (usuario.getNombre().equals(nombreIngresado)
                    && String.valueOf(usuario.getCodigo()).equals(codigoIngresado)) {
                usuarioValido = true;
                break;
            }
        }

        // Si el usuario no es válido, se muestra un error
        if (!usuarioValido) {
            request.setAttribute("error", "Usuario o código incorrecto.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Manejo de la respuesta si el usuario es válido
        HttpSession session = request.getSession();
        session.setAttribute("loggedIn", true);
        session.setAttribute("idUsuario", codigoIngresado);
        session.setAttribute("nombreUsuario", nombreIngresado);

        // Obtener el código del usuario validado y guardarlo en la sesión como String
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreIngresado)
                    && String.valueOf(usuario.getCodigo()).equals(codigoIngresado)) {
                session.setAttribute("codigoUsuario", String.valueOf(usuario.getCodigo()));  // Guardar como String
                break;
            }
        }

        response.sendRedirect("index.jsp");
    }

    private LinkedList<Usuario> leerUsuariosDesdeArchivo() throws IOException {
        LinkedList<Usuario> usuarios = new LinkedList<>();
        String rutaRelativa = "/WEB-INF/usuarios.txt";
        String rutaCompleta = getServletContext().getRealPath(rutaRelativa);

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {
            String linea;
            Usuario usuario = null;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Código:")) {
                    if (usuario != null) {
                        usuarios.add(usuario);
                    }
                    usuario = new Usuario();
                    usuario.setCodigo(Integer.parseInt(linea.split(": ")[1].trim()));
                } else if (linea.startsWith("Nombre:")) {
                    usuario.setNombre(linea.split(": ")[1].trim());
                }
            }
            if (usuario != null) {
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}