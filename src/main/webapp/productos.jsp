<%@ page import="java.util.List"%>
<%@ page import="modelo.Productos"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Correcta inclusión del archivo CSS -->
<link rel="stylesheet" type="text/css" href="styles/productos.css?v=1.0">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">

<html>
<head>
    <title>Mis Productos</title>
</head>
<body>
    <div class="factura">
        <!-- Título de la factura -->
        <h1 class="titulo">Productos Agregados</h1>
        
        <!-- Mostrar mensaje de compra -->
        <div>
            <%= request.getAttribute("mensajeCompra") != null ? request.getAttribute("mensajeCompra") : "" %>
        </div>

        <!-- Tabla de productos -->
        <table class="tabla-productos">
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
                    List<Productos> productos = (List<Productos>) request.getAttribute("productos");
                    double totalFactura = 0.0;

                    if (productos != null) {
                        for (Productos producto : productos) {
                            double totalProducto = producto.getPrecio() * producto.getCantidad();
                            totalFactura += totalProducto;
                %>
                    <tr>
                        <td><%= producto.getCodigo() %></td>
                        <td><%= producto.getNombreServicios() %></td>
                        <td><%= producto.getCantidad() %></td>
                        <td><%= producto.getUbicacion() %></td>
                        <td>$<%= producto.getPrecio() %></td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr><td colspan="5">No hay productos disponibles.</td></tr>
                <% } %>
            </tbody>
        </table>

        <!-- Total a pagar -->
        <hr>
        <h5><strong>Total a Pagar: $<span id="totalFactura"><%= totalFactura %></span></strong></h5>

        <!-- Botón para Generar Factura -->
        <form action="FacturaServlet" method="POST">
            <input type="hidden" name="codigoUsuario" value="<%= request.getSession().getAttribute("codigoUsuario") %>">
            <input type="hidden" name="accion" value="confirmarCompra">
            <button type="submit" class="boton-factura">Generar Factura y Confirmar Compra</button>
        </form>

        <!-- Botón para regresar al inicio -->
        <form action="index.jsp" method="GET">
            <button type="submit" class="boton-atras">Regresar al Inicio</button>
        </form>

    </div>
</body>
</html>