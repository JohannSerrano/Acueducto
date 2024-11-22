<%-- 
    Document   : index
    Created on : 23/08/2024, 1:56:53?p.?m.
    Author     : Karen Erazo y Johan Serrano
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%
    // Inicializar loggedIn en false si no hay sesión o no se ha establecido
    Boolean loggedIn = false;
    if (session != null && session.getAttribute("loggedIn") != null) {
        loggedIn = (Boolean) session.getAttribute("loggedIn");
    }
%>

<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HidroPura</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="styles/index.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>


        <!-- Responsive navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-5">
                <a class="navbar-brand" href="#">HidroPura</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contactos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Servicios</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="empleado.jsp">Empleado</a>
                        </li>
                        <% if (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn")) { %>

                        <!-- Ícono de usuario como botón -->
                        <li class="nav-item">
                            <!-- Botón que abre el modal -->
                            <button type="button" class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#userModal">
                                <i class="bi bi-person-circle"></i> Usuario
                            </button>
                        </li>
                        <li class="nav-item">

                            <a class="nav-link" href="ProductoServlet?accion=verProductos">Ver Productos</a>

                        </li>
                        <% } else { %>
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">Iniciar sesión</a>
                        </li>

                        <% }%>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Modal de usuario -->
        <div class="modal fade" id="userModal" tabindex="-1" aria-labelledby="userModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="userModalLabel">Información del Usuario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Aquí irá la información del usuario obtenida desde la sesión -->
                        <p><strong>Nombre de Usuario:</strong> <%= session.getAttribute("nombreUsuario")%></p>
                        <p><strong>Código:</strong> <%= session.getAttribute("codigoUsuario")%></p>
                    </div>
                    <div class="modal-footer">
                        <!-- Botón para cerrar el modal -->
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>

                        <!-- Botón para eliminar usuario -->
                        <form action="EliminarUsuarioServlet" method="POST" onsubmit="return confirm('¿Estás seguro de que deseas eliminar tu cuenta?');">
                            <input type="hidden" name="codigoUsuario" value="<%= session.getAttribute("codigoUsuario")%>">
                            <button type="submit" class="btn btn-danger">Eliminar Usuario</button>
                        </form>

                        <!-- Botón para modificar usuario -->
                        <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#modificarUsuarioModal">Modificar Usuario</button>

                        <!-- Botón de cerrar sesión -->
                        <div class="text-center">
                            <a href="login.jsp" class="btn btn-primary" onclick="return confirm('¿Estás seguro de que deseas cerrar sesión?');">
                                Cambiar de usuario
                            </a>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>




    <!-- Header -->
    <header class="py-5 position-relative" style="background-image: url('images/agua.jpg'); background-size: cover; background-position: center;">
        <!-- Capa superpuesta -->
        <div style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5);"></div>
        <div class="container px-5 position-relative" style="z-index: 1;">
            <div class="row gx-5 justify-content-center">
                <div class="col-lg-6">
                    <div class="text-center my-5">
                        <h1 class="display-5 fw-bolder text-white mb-2">La Esencia De La Pureza En Cada Gota</h1>
                        <p class="lead text-white mb-4">Ofrecer agua pura y fresca, garantizando calidad y sostenibilidad para el bienestar de nuestras comunidades!</p>
                        <% if (!loggedIn) { %>
                        <div class="d-grid gap-3 d-sm-flex justify-content-sm-center">
                            <a class="btn btn-primary btn-lg px-4 me-sm-3" href="registrar.jsp">Registrar Usuario</a>
                            <a class="btn btn-outline-light btn-lg px-4" href="login.jsp">Iniciar sesión</a>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Sección bloqueada/desbloqueada dependiendo del estado de inicio de sesión -->
    <section id="features" class="py-5 border-bottom">
        <div class="container px-5 my-5">
            <div class="row gx-5">
                <% if (loggedIn) { %>
                <!-- Mostrar la información completa si está logueado -->
                <!-- Features section -->

                <div class="col-lg-4 mb-5 mb-lg-0">
                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
                        <i class="bi bi-collection"></i>
                    </div>
                    <h2 class="h4 fw-bolder">Gestión Inteligente del Agua</h2>
                    <p>Optimiza el uso de recursos hídricos con nuestras soluciones avanzadas de monitoreo y control. Desde el hogar hasta grandes industrias, garantizamos eficiencia y sostenibilidad en cada gota.  </p>
                    <a class="text-decoration-none" href="#">
                        Ver más
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
                <div class="col-lg-4 mb-5 mb-lg-0">
                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
                        <i class="bi bi-building"></i>
                    </div>
                    <h2 class="h4 fw-bolder">Acueductos Sostenibles</h2>
                    <p>Nuestra tecnología permite a los acueductos gestionar la distribución del agua de forma eficiente, reduciendo el desperdicio y protegiendo el medio ambiente. </p>
                    <a class="text-decoration-none" href="#">
                        Ver más
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
                <div class="col-lg-4">
                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
                        <i class="bi bi-toggles2"></i>
                    </div>
                    <h2 class="h4 fw-bolder">Innovación en Riego Agrícola</h2>
                    <p>Maximiza la productividad de tus cultivos con nuestros sistemas de riego automatizados que ajustan el consumo de agua según las condiciones del suelo y el clima.   </p>
                    <a class="text-decoration-none" href="#">
                        Ver más 
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
            </div>
        </div>
    </section>
    <% } else { %>
    <!-- Bloquear acceso a la información si no está logueado -->



    <% } %>
</div>
</div>
</section>

<!-- Servicios disponibles -->
<section class="bg-light py-5 border-bottom">
    <div class="container px-5 my-5">
        <% if (loggedIn) { %>
        <div class="text-center mb-5">
            <h2 class="fw-bolder">SERVICIOS DISPONIBLES PARA TI</h2>
            <p class="lead mb-0">Elige el servicio que mejor se ajuste a tus necesidades y lleva el control de tu agua al siguiente nivel, porque cada gota cuenta.</p>
        </div>
        <div class="row gx-5 justify-content-center">
            <!-- Servicio 1 -->
            <div class="col-lg-6 col-xl-4">
                <div class="card mb-5 mb-xl-0">
                    <div class="card-body p-5">
                        <div class="small text-uppercase fw-bold text-muted">SERVICIO 1</div>
                        <div class="mb-3">
                            <span class="display-4 fw-bold">Hogar Cristalino</span>
                        </div>
                        <ul class="list-unstyled mb-4">
                            <li class="mb-2"><i class="bi bi-check text-primary"></i> Monitoreo en tiempo real</li>
                            <li class="mb-2"><i class="bi bi-check text-primary"></i> Alertas automáticas de fugas</li>
                            <li class="mb-2"><i class="bi bi-check text-primary"></i> Reporte mensual de consumo</li>
                            <li class="mb-2"><i class="bi bi-check text-primary"></i> Descuentos en ahorradores de agua</li>
                        </ul>
                        <div class="d-grid">
                            <a class="btn btn-outline-primary" href="#" data-bs-toggle="modal" data-bs-target="#seleccionServicioModal" onclick="seleccionarServicio('Hogar Cristalino')">Elegir Servicio</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Servicio 2 -->
            <div class="col-lg-6 col-xl-4">
                <div class="card mb-5 mb-xl-0">
                    <div class="card-body p-5">
                        <div class="small text-uppercase fw-bold">SERVICIO 2</div>
                        <div class="mb-3">
                            <span class="display-4 fw-bold">Industria Verde</span>
                        </div>
                        <ul class="list-unstyled mb-4">
                            <li class="mb-2"><i class="bi bi-check text-primary"></i> Control de consumo industrial</li>
                            <li class="mb-2"><i class="bi bi-check text-primary"></i> Proyectos de sostenibilidad</li>
                            <li class="mb-2"><i class="bi bi-check text-primary"></i> Informes de ahorro de agua</li>
                            <li class="mb-2"><i class="bi bi-check text-primary"></i> Acceso a nuestra plataforma avanzada</li>
                        </ul>
                        <div class="d-grid">
                            <a class="btn btn-outline-primary" href="#" data-bs-toggle="modal" data-bs-target="#seleccionServicioModal" onclick="seleccionarServicio('Industria Verde')">Elegir Servicio</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Pricing card enterprise -->
            <div class="col-lg-6 col-xl-4">
                <div class="card">
                    <div class="card-body p-5">
                        <div class="small text-uppercase fw-bold text-muted">Servicio 3</div>
                        <div class="mb-3">
                            <span class="display-4 fw-bold">Campos Verdes</span>

                        </div>
                        <ul class="list-unstyled mb-4">
                            <li class="mb-2">
                                <i class="bi bi-check text-primary"></i>
                                <strong>Monitoreo de lluvias y clima</strong>
                            </li>
                            <li class="mb-2">
                                <i class="bi bi-check text-primary"></i>
                                Riego inteligente con sensores 
                            </li>
                            <li class="mb-2">
                                <i class="bi bi-check text-primary"></i>
                                Informes mensuales del uso de agua
                            </li>
                            <li class="mb-2">
                                <i class="bi bi-check text-primary"></i>
                                Alertas de sequías o excesos de agua
                            </li>
                            </li>

                            ¡Maximiza tus cultivos mientras proteges este recurso tan importante!
                            </li>
                        </ul>
                        <div class="d-grid">
                            <div class="d-grid">
                                <a class="btn btn-outline-primary" href="#" data-bs-toggle="modal" data-bs-target="#seleccionServicioModal" onclick="seleccionarServicio('Campos Verdes')">Elegir Servicio</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% }%>
    </div>
</section>

<div class="modal fade" id="seleccionServicioModal" tabindex="-1" aria-labelledby="seleccionServicioLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="seleccionServicioLabel">Seleccionar Servicio</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="formSeleccionServicio" action="ProductoServlet" method="POST">
                    <div class="mb-3">
                        <label for="cantidad" class="form-label">Cantidad</label>
                        <input type="text" class="form-control" id="cantidad" name="cantidad" placeholder="Cuantos solicita" required>
                    </div>
                    <div class="mb-3">
                        <label for="ubicacion" class="form-label">Ubicación</label>
                        <input type="text" class="form-control" id="ubicacion" name="ubicacion" placeholder="Ingresa tu ubicación" required>
                    </div>
                    <div class="mb-3">
                        <label for="nombreServicios" class="form-label">Servicio</label>
                        <input type="text" class="form-control" id="nombreServicios" name="nombreServicios" readonly>
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modificarUsuarioModal" tabindex="-1" aria-labelledby="modificarUsuarioModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="EditarUsuarioServlet" method="POST">
                <div class="modal-header">
                    <h5 class="modal-title" id="modificarUsuarioModalLabel">Modificar Usuario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="codigoUsuario" value="<%= session.getAttribute("codigoUsuario")%>">

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="mb-3">
                        <label for="apellidos" class="form-label">Apellidos</label>
                        <input type="text" class="form-control" id="apellidos" name="apellidos" required>
                    </div>
                    <div class="mb-3">
                        <label for="cedula" class="form-label">Cédula</label>
                        <input type="text" class="form-control" id="cedula" name="cedula" required>
                    </div>
                    <div class="mb-3">
                        <label for="direccion" class="form-label">Dirección</label>
                        <input type="text" class="form-control" id="direccion" name="direccion" required>
                    </div>
                    <div class="mb-3">
                        <label for="estrato" class="form-label">Estrato</label>
                        <input type="text" class="form-control" id="estrato" name="estrato" required>
                    </div>
                    <div class="mb-3">
                        <label for="ciudad" class="form-label">Ciudad</label>
                        <input type="text" class="form-control" id="ciudad" name="ciudad" required>
                    </div>
                    <div class="mb-3">
                        <label for="codigoPostal" class="form-label">Código Postal</label>
                        <input type="text" class="form-control" id="codigoPostal" name="codigoPostal" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-success">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

</body>

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container px-5">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p>
    </div>
</footer>
<div class="modal fade" id="registroExitosoModal" tabindex="-1" aria-labelledby="registroExitosoLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registroExitosoLabel">Registro Exitoso</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>¡Usuario registrado exitosamente!</p>
                <p><strong>Nombre:</strong> <span id="nombreUsuario"></span></p>
                <p><strong>Código:</strong> <span id="codigoUsuario"></span></p>
                <p>Ten en cuenta que para iniciar sesion tienes que utilizar el codigo que te proporcionamos</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
<script>
                                    window.onload = function () {
                                        var nombre = "<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : ""%>";
                                        var codigo = "<%= request.getAttribute("codigoUsuario") != null ? request.getAttribute("codigoUsuario") : ""%>";

                                        if (nombre !== "" && codigo !== "") {
                                            // Asignar valores al modal
                                            document.getElementById("nombreUsuario").textContent = nombre;
                                            document.getElementById("codigoUsuario").textContent = codigo;

                                            // Mostrar el modal
                                            var registroExitosoModal = new bootstrap.Modal(document.getElementById('registroExitosoModal'));
                                            registroExitosoModal.show();
                                        }
                                    };
</script>
<!-- Bootstrap core JS -->
<!-- Core theme JS -->
<script src="<%=request.getContextPath()%>/js/scripts.js"></script>
<script>
                                    function seleccionarServicio(nombreServicio) {
                                        document.getElementById('nombreServicios').value = nombreServicio;
                                    }
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>