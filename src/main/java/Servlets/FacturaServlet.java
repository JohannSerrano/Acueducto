package Servlets;

import modelo.GestionProductos;
import modelo.Productos;
import modelo.NodoProducto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FacturaServlet", urlPatterns = {"/FacturaServlet"})
public class FacturaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener la gestión de productos desde el contexto de la aplicación
        GestionProductos gestionProductos = (GestionProductos) getServletContext().getAttribute("gestionProductos");
        if (gestionProductos == null) {
            gestionProductos = new GestionProductos();
            String rutaCompleta = getServletContext().getRealPath("/WEB-INF/productos.txt");
            gestionProductos.leerProductosDesdeArchivo(rutaCompleta);
            getServletContext().setAttribute("gestionProductos", gestionProductos);
        }

        // Obtener el código de usuario desde la sesión
        String codigoUsuario = (String) request.getSession().getAttribute("codigoUsuario");

        // Verificar si hay un usuario válido
        if (codigoUsuario == null || codigoUsuario.isEmpty()) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener la lista de productos para este usuario
        NodoProducto cabeza = gestionProductos.getListaProductos();
        List<Productos> productosDelUsuario = new ArrayList<>();
        
        while (cabeza != null) {
            // Si el producto corresponde al usuario, lo agregamos a la lista
            if (cabeza.getProducto().getCodigoUsuario().equals(codigoUsuario)) {
                productosDelUsuario.add(cabeza.getProducto());
            }
            cabeza = cabeza.getSiguiente();
        }

        // Si el usuario tiene productos, proceder con la compra
        if (!productosDelUsuario.isEmpty()) {
            // Eliminar los productos del archivo
            for (Productos producto : productosDelUsuario) {
                NodoProducto nodo = gestionProductos.buscarNodo(producto);
                if (nodo != null) {
                    gestionProductos.eliminarProducto(nodo);
                }
            }

            // Guardar los cambios en el archivo
            String rutaCompleta = getServletContext().getRealPath("/WEB-INF/productos.txt");
            gestionProductos.escribirProductosEnArchivo(rutaCompleta);

            // Mostrar mensaje de compra finalizada
            request.setAttribute("mensajeCompra", "Compra finalizada con éxito. Sus productos han sido eliminados.");
        } else {
            // Si no tiene productos, mostrar mensaje de error
            request.setAttribute("mensajeCompra", "No hay productos en su carrito.");
        }

        // Redirigir al usuario a la página de productos
        request.getRequestDispatcher("productos.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si la acción es GET, redirigir al POST
        response.sendRedirect("productos.jsp");
    }
}
