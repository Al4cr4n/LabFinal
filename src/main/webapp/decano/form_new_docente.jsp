<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear un nuevo trabajo</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="job"/>
    </jsp:include>
    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Crear un nuevo Docente</h1>
            <form method="POST" action="<%=request.getContextPath()%>/DecanoServlet?action=crear">

                <div class="mb-3">
                    <label class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="nombre_docente">
                </div>
                <div class="mb-3">
                    <label class="form-label">Correo</label>
                    <input type="text" class="form-control" name="correo_docente">
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="text" class="form-control" name="contrasena_docente">
                </div>

                <a href="<%= request.getContextPath()%>/DecanoServlet" class="btn btn-danger">Cancelar</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>