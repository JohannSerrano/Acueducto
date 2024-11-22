<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HidroPura - Registro de Empleado</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="styles/registrar.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="background-image"></div>
        <div class="login-container">
            <h1>Registro de Empleado</h1>
            <form action="EmpleadoServlet" method="post">
                <input type="text" class="form-control" name="nombre" placeholder="Nombre" required>
                <input type="text" class="form-control" name="apellido" placeholder="Apellido" required>
                
                <select class="form-control" name="cargo" required>
                    <option value="" disabled selected>Cargo</option>
                    <option value="Instalador">Instalador</option>
                    <option value="Mantenimiento">Mantenimiento</option>
                    <option value="Fontanero">Fontanero</option>
                </select>
                
                <button type="submit" class="btn btn-primary">Registrar Empleado</button>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
