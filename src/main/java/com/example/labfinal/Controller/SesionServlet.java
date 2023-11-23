package com.example.labfinal.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SesionServlet", urlPatterns = {"/SesionServlet", ""})
public class SesionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("loginForm.jsp").forward(request, response);
        /*HttpSession httpSession = request.getSession();
        Employee employeeLogged = (Employee) httpSession.getAttribute("usuarioLogueado");

        if(employeeLogged != null && employeeLogged.getEmployeeId() > 0){

            if(request.getParameter("a") != null){//logout
                httpSession.invalidate();
            }
            response.sendRedirect(request.getContextPath());
        }else{
            request.getRequestDispatcher("loginForm.jsp").forward(request, response);
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String correo = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username: " + correo + "| password: " + password );

        //Herramientas:
        CredentialsDao credentialsDao = new CredentialsDao();
        DelegadoActividadDao delegadoActividadDao = new DelegadoActividadDao();
        DelegadoGeneralDao delegadoGeneralDao = new DelegadoGeneralDao();
        EstadoAlumnoDao estadoAlumnoDao = new EstadoAlumnoDao();
        AlumnoDao alumnoDao = new AlumnoDao();



        String action = request.getParameter("action");


        switch (action){
            case "inicio_sesion":
                HttpSession httpSession = request.getSession();

                int tipoUsuario=credentialsDao.validarUsuarioPassword(correo,password);//1: Administrador | 2: Rector | 3: Decano | 4: Docente | 0:no encontrado

                switch (tipoUsuario){
                    case 0:
                        request.setAttribute("err","Usuario o password incorrectos "); //Va a mandar este mensaje a login/InicioSesion.jsp
                        request.getRequestDispatcher("login/InicioSesion.jsp").forward(request,response);
                        break;
                    case 1:
                        Alumno alumno = credentialsDao.obtenerAlumno(correo); //Obtener al alumno por el correo. Este es el alumno logueado
                        httpSession.setAttribute("usuariologueado",alumno); //Guardo el alumno que acaba de iniciar sesion
                        httpSession.setAttribute("tipoUsuario",1); //Para validar después en los servlets (anti copiar urls)
                        response.sendRedirect(request.getContextPath() + "/AlumnoServlet");
                        break;
                    case 2:
                        Alumno alumno2 = credentialsDao.obtenerAlumno(correo); //Obtener al alumno por el correo. Este es el alumno logueado
                        DelegadoActividad delegadoActividad = alumno2.getDelegadoActividad();
                        httpSession.setAttribute("usuariologueado",alumno2); //Guardo el alumno que acaba de iniciar sesion
                        httpSession.setAttribute("DelegadoActividad",delegadoActividad); //Guardo la tabla que relaciona al alumno con la actividad
                        httpSession.setAttribute("tipoUsuario",2); //Para validar después en los servlets (anti copiar urls)
                        response.sendRedirect(request.getContextPath() + "/DelegadoActividadServlet");
                        break;
                    case 3:

                        DelegadoGeneral delegadoGeneral = credentialsDao.obtenerDelegadoGeneral(correo);
                        httpSession.setAttribute("usuariologueado",delegadoGeneral); //Guardo el alumno que acaba de iniciar sesion
                        httpSession.setAttribute("tipoUsuario",3); //Para validar después en los servlets (anti copiar urls)
                        response.sendRedirect(request.getContextPath() + "/DelegadoGeneralServlet");
                        break;

                }
                break;

            case "olvido_contra":
                request.getRequestDispatcher("login/OlvidoContra.jsp").forward(request,response);
                break;
            case "registro_usuario":

                Alumno alumnoRegistrar = new Alumno();

                alumnoRegistrar.setNombre(request.getParameter("nombre"));
                alumnoRegistrar.setApellido(request.getParameter("apellido"));
                alumnoRegistrar.setCodigo(request.getParameter("codigo"));
                alumnoRegistrar.setCorreo(request.getParameter("correo"));
                alumnoRegistrar.setContrasena(request.getParameter("password"));
                alumnoRegistrar.setEgresado(request.getParameter("estadoAcademico"));
                alumnoRegistrar.setFoto(request.getPart("foto").getInputStream());
                alumnoRegistrar.setEstadoAlumno(estadoAlumnoDao.obtenerEstadoAlumno("3")); // se le asigna el estado de pendiente (luego será revisado por el delegado general)

                alumnoDao.crearAlumno(alumnoRegistrar); //crear el alumno en la base de datos

                response.sendRedirect(request.getContextPath() + "/SesionServlet");
                break;


        }*/
    }
}

