<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HidroPura</title>
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="styles/empleado.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <!-- Responsive navbar -->
    <nav class="navbar navbar-expand-lg "style="background-color: #333">
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
                    
                   
                    <li class="nav-item">
                        <a class="nav-link" href="loginEmpleado.jsp">Iniciar sesión</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Header -->
    <header class="py-5 position-relative" style="background-image: url('images/empleado.jpg'); background-size: cover; background-position: center;">
        <!-- Capa superpuesta -->
        <div style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5);"></div>
        <div class="container px-5 position-relative" style="z-index: 1;">
            <div class="row gx-5 justify-content-center">
                <div class="col-lg-6">
                    <div class="text-center my-5">
                        <h1 class="display-5 fw-bolder text-white mb-2">La Esencia De La Pureza En Cada Gota</h1>
                        <p class="lead text-white mb-4">Ofrecer agua pura y fresca, garantizando calidad y sostenibilidad para el bienestar de nuestras comunidades!</p>
                        
                        <div class="d-grid gap-3 d-sm-flex justify-content-sm-center">
                            <a class="btn btn-secondary btn-lg px-4 me-sm-3" href="registrarEmpleado.jsp">Registrar Empleado</a>
                            <a class="btn btn-secondary btn-lg px-4" href="loginEmpleado.jsp">Iniciar</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Modal para mostrar el nombre y código del empleado -->
    <div class="modal fade" id="empleadoAgregadoModal" tabindex="-1" aria-labelledby="empleadoAgregadoLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="empleadoAgregadoLabel">Empleado Agregado</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>¡Empleado agregado exitosamente!</p>
                    <p><strong>Nombre:</strong> <span id="nombreEmpleado"></span></p>
                    <p><strong>ID:</strong> <span id="idEmpleado"></span></p> <!-- Cambiado a ID -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Sección bloqueada/desbloqueada dependiendo del estado de inicio de sesión -->
    <section id="features" class="py-5 border-bottom">
        <div class="container px-5 my-5">
            <div class="row gx-5">
                <!-- Aquí puede ir el contenido adicional que necesites -->
            </div>
        </div>
    </section>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Script para mostrar el modal si el empleado fue agregado -->
    <script>
        window.onload = function() {
            var nombreEmpleado = "<%= request.getAttribute("nombreEmpleado") != null ? request.getAttribute("nombreEmpleado") : "" %>";
            var idEmpleado = "<%= request.getAttribute("idEmpleado") != null ? request.getAttribute("idEmpleado") : "" %>";

            if (nombreEmpleado !== "" && idEmpleado !== "") {
                // Asignar valores al modal de empleado agregado
                document.getElementById("nombreEmpleado").textContent = nombreEmpleado;
                document.getElementById("idEmpleado").textContent = idEmpleado;

                // Mostrar el modal
                var empleadoAgregadoModal = new bootstrap.Modal(document.getElementById('empleadoAgregadoModal'));
                empleadoAgregadoModal.show();
            }
        };
    </script>

</body>
</html>
