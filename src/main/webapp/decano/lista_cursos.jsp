<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.labfinal.Beans.Usuario" %>
<%@ page import="com.example.labfinal.Beans.Curso" %>
<%@ page import="com.example.labfinal.Beans.Evaluaciones" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaCursosOficial" type="java.util.ArrayList<com.example.labfinal.Beans.Curso>" scope="request"/>
<jsp:useBean id="listaEvaluaciones" type="java.util.ArrayList<com.example.labfinal.Beans.Evaluaciones>" scope="request"/>

<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="usuariologueado" class="com.example.labfinal.Beans.Usuario" type="Usuario" scope="session" />
<!DOCTYPE html>
<html>
<head>
    <title>Lista empleados</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="cursos"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Lista de Cursos</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/DecanoServlet?action=agregar" class="btn btn-primary">Agregar
                nuevo Curso </a>
        </div>
    </div>
    <% if (request.getParameter("msg") != null) {%>
    <div class="alert alert-success" role="alert"><%=request.getParameter("msg")%>
    </div>
    <% } %>
    <% if (request.getParameter("err") != null) {%>
    <div class="alert alert-danger" role="alert"><%=request.getParameter("err")%>
    </div>
    <% } %>
    <form method="post" action="<%=request.getContextPath()%>/EmployeeServlet?action=buscar">
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Buscar por nombre" name="textoBuscar"
                   value="<%=textoBusqueda%>"/>
            <button class="input-group-text" type="submit">
                <i class="bi bi-search"></i>
            </button>
            <a class="input-group-text" href="<%=request.getContextPath()%>/EmployeeServlet">
                <i class="bi bi-x-circle"></i>
            </a>
        </div>
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Curso</th>
            <th>Codigo</th>
            <th></th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Curso curso : listaCursosOficial) {

        %>
        <tr>
            <td><%= i%>
            </td>
            <td><%=curso.getNombre()%>
            </td>
            <td><%=curso.getCodigo()%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/DecanoServlet?action=editar&id=<%=curso.getIdcurso()%>"
                   type="button" class="btn btn-primary">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>%

            <td>
                <a onclick="return confirm('¿Estas seguro de borrar?');"
                   href="<%=request.getContextPath()%>/DecanoServlet?action=borrar&id=<%=curso.getIdcurso()%>"
                   type="button" class="btn btn-danger">
                    <i class="bi bi-trash"></i>
                </a>
            </td>

        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
