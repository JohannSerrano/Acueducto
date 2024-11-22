<%-- 
    Document   : index
    Created on : 23/08/2024, 1:56:53?p.?m.
    Author     : Karen Erazo y Johan Serrano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HidroPura - Login </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="styles/registrar.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="background-image"></div>
        <div class="login-container">
            <h1>HidroPura</h1>
            <form action="UsuarioServlet" method="post">
                <input type="text" class="form-control" name="nombre" placeholder="Nombres" required>
                <input type="text" class="form-control" name="apellidos" placeholder="Apellidos" required>
                <input type="text" class="form-control" name="cedula" placeholder="Cédula" required>
                <input type="text" class="form-control" name="direccion" placeholder="Dirección" required>
                <select class="form-control" name="estrato">
                    <option value="" disabled selected>Estrato</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
                <input type="text" class="form-control" name="ciudad" placeholder="Ciudad" required>
                <input type="text" class="form-control" name="codigo_postal" placeholder="Código Postal" required>
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
