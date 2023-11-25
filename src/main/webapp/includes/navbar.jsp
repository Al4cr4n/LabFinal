<%@ page import="com.example.labfinal.Beans.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<% String currentPage = request.getParameter("currentPage"); %>
<jsp:useBean id="usuarioLogueado" scope="session" type="com.example.labfinal.Beans.Usuario" class="com.example.labfinal.Beans.Usuario" />

<nav class="navbar navbar-expand-md navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>">Gestión HR</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav">

                <% if(usuarioLogueado.getRol().getIdrol() == 3){ %>
                <li class="nav-item">

                    <a class="nav-link <%=currentPage.equals("cursos") ? "active" : ""%>"
                       href="<%=request.getContextPath()%>/DecanoServlet?action=listar_cursos">
                        Cursos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=currentPage.equals("docentes") ? "active" : ""%>"
                       href="<%=request.getContextPath()%>/DecanoServlet?action=listar_docentes">
                        Docentes
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link disabled"><%=usuarioLogueado.getNombre()%></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" style="text-decoration: underline;color: #0d6efd;" href="<%=request.getContextPath()%>/SesionServlet?a=lo">(Cerrar sesión)</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>