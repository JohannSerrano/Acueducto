<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.NodoProducto"%>
<%@page import="modelo.Productos"%>
<%@page import="modelo.GestionProductos"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Servicios</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="styles/empleado.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <!-- Responsive navbar -->
        <nav class="navbar navbar-expand-lg " style="background-color: #333">
            <div class="container px-5">
                <a class="navbar-brand" href="#" style="color: white">HidroPura</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="index.jsp">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contactos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Servicios</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Empleado</a>
                        </li>

                        <!-- Ícono de usuario como botón -->
                        <li class="nav-item">
                            <!-- Botón que abre el modal -->
                            <button type="button" class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#userModal">
                                <i class="bi bi-person-circle"></i> Cuenta
                            </button>
                        </li>

                        <!-- Modal de usuario -->
                        <div class="modal fade" id="userModal" tabindex="-1" aria-labelledby="userModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="userModalLabel">Información del Empleado</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <!-- Aquí irá la información del usuario obtenida desde la sesión -->
                                        <p><strong>Empleado:</strong> <%= session.getAttribute("nombreEmpleado")%></p>
                                        <p><strong>ID:</strong> <%= session.getAttribute("idEmpleado")%></p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>

                                        <!-- Botón para eliminar usuario -->
                                        <form action="EliminarEmpleadoServlet" method="POST" onsubmit="return confirm('¿Estás seguro de que deseas eliminar tu cuenta?');">
                                            <input type="hidden" name="idEmpleado" value="<%= session.getAttribute("idEmpleado")%>">
                                            <button type="submit" class="btn btn-danger">Eliminar Empleado</button>
                                        </form>

                                        <!-- Botón para abrir el modal de edición -->
                                        <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editarModal">Editar Empleado</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal para editar empleado -->
                        <!-- Modal para editar empleado -->
                        <div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editarModalLabel">Editar Empleado</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="EditarEmpleadoServlet" method="POST">
                                            <input type="hidden" name="id" value="<%= session.getAttribute("idEmpleado")%>">
                                            <div class="mb-3">
                                                <label for="nuevoNombre" class="form-label">Nuevo Nombre</label>
                                                <input type="text" class="form-control" id="nuevoNombre" name="nuevoNombre" value="<%= session.getAttribute("nombreEmpleado")%>" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="nuevoApellido" class="form-label">Nuevo Apellido</label>
                                                <input type="text" class="form-control" id="nuevoApellido" name="nuevoApellido" value="<%= session.getAttribute("apellidoEmpleado")%>" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="nuevoCargo" class="form-label">Nuevo Cargo</label>
                                                <select class="form-control" name="nuevoCargo" id="nuevoCargo" required>
                                                    <option value="" disabled>Cargo</option>
                                                    <option value="Instalador" <%= "Instalador".equals(session.getAttribute("cargoEmpleado")) ? "selected" : ""%>>Instalador</option>
                                                    <option value="Mantenimiento" <%= "Mantenimiento".equals(session.getAttribute("cargoEmpleado")) ? "selected" : ""%>>Mantenimiento</option>
                                                    <option value="Fontanero" <%= "Fontanero".equals(session.getAttribute("cargoEmpleado")) ? "selected" : ""%>>Fontanero</option>
                                                </select>
                                            </div>

                                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <h1>Lista de Productos</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nombre del Servicio</th>
                        <th>Cantidad</th>
                        <th>Ubicación</th>
                        <th>Precio</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        GestionProductos gestionProductos = (GestionProductos) getServletContext().getAttribute("gestionProductos");
                        if (gestionProductos != null) {
                            NodoProducto cabeza = gestionProductos.getListaProductos();
                            if (cabeza != null) {
                                NodoProducto actual = cabeza;
                                while (actual != null) {
                                    Productos producto = actual.getProducto();
                    %>
                    <tr>
                        <td><%= producto.getCodigo()%></td>
                        <td><%= producto.getNombreServicios()%></td> <!-- Cambio aquí -->
                        <td><%= producto.getCantidad()%></td>
                        <td><%= producto.getUbicacion()%></td>
                        <td><%= producto.getPrecio()%></td>
                    </tr>
                    <%
                            actual = actual.getSiguiente();
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5">No hay productos disponibles.</td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5">No se pudo cargar la lista de productos.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <!-- Botón de Cerrar Sesión con mensaje de confirmación -->
            <div class="text-center">
                <a href="empleado.jsp" class="btn btn-primary" onclick="return confirm('¿Estás seguro de que deseas cerrar sesión?');">
                    Cerrar Sesión
                </a>
            </div>

            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        </div>
    </body>
</html>
