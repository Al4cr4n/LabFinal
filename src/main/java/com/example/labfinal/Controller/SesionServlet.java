package com.example.labfinal.Controller;

import com.example.labfinal.Beans.Usuario;
import com.example.labfinal.Daos.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SesionServlet", urlPatterns = {"/SesionServlet", ""})
public class SesionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Usuario usuarioLogged = (Usuario) httpSession.getAttribute("usuarioLogueado");

        if(usuarioLogged != null && usuarioLogged.getIdusuario() > 0){

            if(request.getParameter("a") != null){//logout
                httpSession.invalidate();
            }
            response.sendRedirect(request.getContextPath());
        }else{
            request.getRequestDispatcher("sesion/loginForm.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username: " + correo + "| password: " + password );



        //Herramientas:
        UsuarioDao usuarioDao = new UsuarioDao();





        String action = request.getParameter("action");


        switch (action){
            case "inicio_sesion":
                HttpSession httpSession = request.getSession();
                System.out.println("hola");
                int tipoUsuario=usuarioDao.validarUsuarioPassword(correo,password);//1: Administrador | 2: Rector | 3: Decano | 4: Docente | 0:no encontrado
                System.out.println("El Rol es: " + tipoUsuario);
                switch (tipoUsuario){
                    case 0:
                        request.setAttribute("err","Usuario o password incorrectos "); //Va a mandar este mensaje a login/InicioSesion.jsp
                        request.getRequestDispatcher("sesion/loginForm.jsp").forward(request,response);
                        break;
                    case 1:
                        //Usuario administrador = usuarioDao.obtenerAdministrador(correo); //Obtener al usuario por el correo. Este es el administrador logueado
                        //httpSession.setAttribute("usuariologueado",administrador); //Guardo el usuario que acaba de iniciar sesion
                        //httpSession.setAttribute("tipoUsuario",1); //Para validar después en los servlets (anti copiar urls)
                        //response.sendRedirect(request.getContextPath() + "/AdministradorServlet");
                        break;
                    case 2:

                        //Usuario rector = usuarioDao.obtenerRector(correo); //Obtener al usuario por el correo. Este es el rector logueado

                        //httpSession.setAttribute("usuariologueado",rector); //Guardo el usuario que acaba de iniciar sesion
                        //httpSession.setAttribute("DelegadoActividad",delegadoActividad); //Guardo la tabla que relaciona al alumno con la actividad
                        //httpSession.setAttribute("tipoUsuario",2); //Para validar después en los servlets (anti copiar urls)
                        //response.sendRedirect(request.getContextPath() + "/DelegadoActividadServlet");
                        break;
                    case 3:
                        Usuario decano = usuarioDao.obtenerDecano(correo); //Obtener al usuario por el correo. Este es el decano logueado
                        System.out.println("El número de rol Ingresado es"+decano.getRol().getIdrol());
                        httpSession.setAttribute("usuariologueado",decano); //Guardo el usuario que acaba de iniciar sesion
                        httpSession.setAttribute("tipoUsuario",3); //Para validar después en los servlets (anti copiar urls)
                        response.sendRedirect(request.getContextPath() + "/DecanoServlet");

                        break;
                    case 4:

                        Usuario docente = usuarioDao.obtenerDocente(correo); //Obtener al usuario por el correo. Este es el docente logueado
                        httpSession.setAttribute("usuariologueado",docente); //Guardo el usuario que acaba de iniciar sesion
                        httpSession.setAttribute("tipoUsuario",4); //Para validar después en los servlets (anti copiar urls)
                        response.sendRedirect(request.getContextPath() + "/DocenteServlet");
                        break;

                }
                break;




        }
    }
}

