<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HidroPura - Login Empleado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/registrar.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="background-image"></div>
    <div class="login-container">
        <h1>HidroPura - Empleado</h1>
        <form action="LoginEmpleadoServlet" method="post">
            <input type="text" class="form-control" name="nombre" placeholder="Nombre" required>
            <input type="text" class="form-control" name="codigo" placeholder="ID" required>
            <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
             <a class="btn btn-primary" href="empleado.jsp">Regresar</a>

        </form>

        <!-- Mostrar mensaje de error si las credenciales son incorrectas -->
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger mt-3">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
