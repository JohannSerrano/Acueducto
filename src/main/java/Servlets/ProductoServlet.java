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

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"}, loadOnStartup = 1)
public class ProductoServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        GestionProductos gestionProductos = new GestionProductos();
        try {
            String rutaCompleta = getServletContext().getRealPath("/WEB-INF/productos.txt");
            gestionProductos.leerProductosDesdeArchivo(rutaCompleta); // Leer productos desde el archivo

            // Almacenar los productos en el contexto de la aplicación
            getServletContext().setAttribute("gestionProductos", gestionProductos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

        // Obtener datos del formulario
        String nombreServicios = request.getParameter("nombreServicios");
        int cantidad;
        try {
            cantidad = Integer.parseInt(request.getParameter("cantidad"));
        } catch (NumberFormatException e) {
            // Si la cantidad no es válida, redirigir con un mensaje de error
            request.setAttribute("error", "Cantidad inválida.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        String ubicacion = request.getParameter("ubicacion");

        // Obtener el código de usuario desde la sesión
        String codigoUsuario = (String) request.getSession().getAttribute("codigoUsuario");

        // Generar código automáticamente para el producto
        int codigoNumerico = 1;
        NodoProducto cabeza = gestionProductos.getListaProductos();
        if (cabeza != null) {
            while (cabeza.getSiguiente() != null) {
                cabeza = cabeza.getSiguiente();
                codigoNumerico++;
            }
            codigoNumerico++;
        }

        // Crear nuevo producto, incluyendo el codigoUsuario
        String codigo = String.valueOf(codigoNumerico);
        Productos nuevoProducto = new Productos(codigo, nombreServicios, cantidad, ubicacion, codigoUsuario);

        // Calcular el precio total y establecerlo en el producto
        double precioTotal = nuevoProducto.getPrecio() * cantidad;
        nuevoProducto.setPrecio(precioTotal);

        // Crear un nuevo nodo de NodoProducto y agregarlo a la lista
        NodoProducto nuevoNodo = new NodoProducto(nuevoProducto);
        gestionProductos.agregarProducto(nuevoNodo);

        // Guardar lista actualizada en el archivo
        String rutaCompleta = getServletContext().getRealPath("/WEB-INF/productos.txt");
        gestionProductos.escribirProductosEnArchivo(rutaCompleta);

        // Redireccionar o mostrar los resultados
        request.setAttribute("nombreServicios", nombreServicios);
        request.setAttribute("codigo", codigo);
        request.setAttribute("precioTotal", precioTotal);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("verProductos".equals(accion)) {
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

            // Filtrar productos por el código de usuario
            if (codigoUsuario != null) {
                NodoProducto cabeza = gestionProductos.getListaProductos();
                List<Productos> productosDelUsuario = new ArrayList<>();
                while (cabeza != null) {
                    // Compara el código de usuario con el código del producto
                    if (cabeza.getProducto().getCodigoUsuario().equals(codigoUsuario)) {
                        productosDelUsuario.add(cabeza.getProducto());
                    }
                    cabeza = cabeza.getSiguiente();
                }

                // Pasar la lista de productos del usuario al JSP
                request.setAttribute("productos", productosDelUsuario);
                request.getRequestDispatcher("productos.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            // En caso de que la acción no sea "verProductos", redirigir a una página por defecto o a otra acción
            response.sendRedirect("index.jsp");
        }
    }
}
