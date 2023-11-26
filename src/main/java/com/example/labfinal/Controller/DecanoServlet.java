package com.example.labfinal.Controller;

import com.example.labfinal.Beans.Curso;
import com.example.labfinal.Beans.Curso_Has_Docente;
import com.example.labfinal.Beans.Evaluaciones;
import com.example.labfinal.Beans.Usuario;
import com.example.labfinal.Daos.DecanoDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "DecanoServlet", value = "/DecanoServlet")
public class DecanoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "listar_cursos" : request.getParameter("action");

        RequestDispatcher view;
        DecanoDao decanoDao = new DecanoDao();
        //Datos de sesión:
        //DelegadoGeneral delegadoGeneral = (DelegadoGeneral) request.getSession().getAttribute("usuariologueado");
        Usuario decano = (Usuario) request.getSession().getAttribute("usuariologueado");
        int idDecano = decano.getIdusuario();
        Usuario decanoActualizado = decanoDao.obtenerIdFacultad(idDecano);
        int idFacultadDecano = decanoActualizado.getFacultad_has_decano().getFacultad().getIdfacultad();
        //tengo que tener su idfalcutad o asociarlo
        System.out.println("Este es el ID de la facultad del decano : " + idFacultadDecano);
        System.out.println("Este es el nombre del decano: " + decano.getNombre());
        switch (action) {
            case "listar_cursos":

                ArrayList<Curso> listaCursos = decanoDao.listarCursos();
                ArrayList<Curso> listaCursosOficial = new ArrayList<>();
                ArrayList<Evaluaciones> listaEvaluaciones =  decanoDao.listaEvaluaciones();
                ArrayList<Curso> listaCursoSinEvaluaciones = new ArrayList<>();
                //lista de evaluaciones
                for (Curso curso : listaCursos) {

                    int idFacultadCurso = curso.getFacultad().getIdfacultad();
                    System.out.println("Id de la facultad del docente: " + idFacultadCurso);
                    if (idFacultadDecano == idFacultadCurso) {
                        listaCursosOficial.add(curso);
                        //listaDocentesConFacultad.add(docenteFacu);
                        System.out.println("Encontramos un curso que pertecene a la facultad asignada al Decano\n");
                    }

                }

                request.setAttribute("listaCursosOficial", listaCursosOficial);
                request.setAttribute("listaEvaluaciones", listaEvaluaciones);
                view = request.getRequestDispatcher("decano/lista_cursos.jsp");
                view.forward(request, response);
                break;
            case "listar_docentes":

                ArrayList<Usuario> listaDocentes = decanoDao.listarDocentes();
                ArrayList<Usuario> listaDocentesOficial = new ArrayList<>();

                for (Usuario docenteFacu : listaDocentes) {
                    System.out.println("Nombres de los docentes: " + docenteFacu.getNombre());
                    int idFacultadDocente = docenteFacu.getCurso_has_docente().getCurso().getFacultad().getIdfacultad();
                    System.out.println("Id de la facultad del docente: " + idFacultadDocente);
                    if (idFacultadDecano == idFacultadDocente) {
                        listaDocentesOficial.add(docenteFacu);
                        //listaDocentesConFacultad.add(docenteFacu);
                        System.out.println("Encontramos un docente que pertecene a la facultad asignada al Decano\n");
                    } else {
                        if (idFacultadDocente == 0) {
                            listaDocentesOficial.add(docenteFacu);
                            //listaDocentesSinFacultadSinCursoAsignado.add(docenteFacu);
                            System.out.println("Encontramos un docente sin facultad y sin curso asignado\n");
                        }
                    }

                }

                request.setAttribute("listaDocentesOficial", listaDocentesOficial);

                view = request.getRequestDispatcher("decano/listaDocentes.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                view = request.getRequestDispatcher("decano/form_new_docente.jsp");
                view.forward(request, response);
                break;
            case "edit":
                String idDocente = request.getParameter("id"); //primary key
                Usuario docenteEdit = decanoDao.buscarProfesorId(Integer.parseInt(idDocente));
                //Le tengo que mandar el id en modo hidden
                if (docenteEdit != null) {
                    request.setAttribute("docenteEdit", docenteEdit);
                    request.getRequestDispatcher("decano/form_edit_docente.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/DecanoServlet");
                }
                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String docenteIdUduario = request.getParameter("id");
                    int docenteId = 0;
                    try {
                        docenteId = Integer.parseInt(docenteIdUduario);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("DecanoServlet?err=Error al borrar el empleado");
                    }

                    Usuario doocenteVerif = decanoDao.obtenerDocente(docenteId);

                    if (doocenteVerif != null) {
                        try {
                            decanoDao.borrarDocente(docenteId);
                            response.sendRedirect("DecanoServlet?msg=Empleado borrado exitosamente");
                        } catch (SQLException e) {
                            response.sendRedirect("DecanoServlet?err=Error al borrar el empleado");
                        }
                    }
                } else {
                    response.sendRedirect("DecanoServlet?err=Error al borrar el empleado");
                }
                break;


            default:
                response.sendRedirect("DecanoServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        DecanoDao decanoDao = new DecanoDao();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action) {
            case "crear"://voy a crear un nuevo trabajo
                String nombreDocente = request.getParameter("nombre_docente");
                String correoDocente = request.getParameter("correo_docente");
                String contrasenaDocente = request.getParameter("contrasena_docente");

                System.out.println("Nombre Docente: " + nombreDocente);
                System.out.println("Correo Docente: " + correoDocente);
                System.out.println("Contrasena Docente: " + contrasenaDocente);



                boolean isAllValid = true;

                if (nombreDocente.length() > 35) {
                    isAllValid = false;
                    System.out.println("Se entro?");
                }

                if (isAllValid) {

                    Usuario docente = decanoDao.buscarPorCorreo(correoDocente); //Busca si hay alguien con el mismo dni
                    //Creamos Trabajador
                    if (docente == null) {
                        System.out.println("Ingresamos al Null");
                        decanoDao.crear(nombreDocente, correoDocente, contrasenaDocente);
                        response.sendRedirect(request.getContextPath() + "/DecanoServlet"); //Una vez creado y dado click a submit se devuelve a la página donde está la lista
                    } else {
                        request.getRequestDispatcher("decano/form_new_docente.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("decano/form_new_docente.jsp").forward(request, response);
                }
                break;
            case "e": //voy a actualizar de la parte editar
                String docenteNombre = request.getParameter("docente_nombre");  //Obtenemos el valor de lo que tipeo el usuario
                String docenteID = request.getParameter("docenteId");
                int idDocente = Integer.parseInt(docenteID);
                boolean isAllValid2 = true;

                if(docenteNombre.length() > 35){
                    isAllValid2 = false;
                }


                if(isAllValid2){
                    Usuario docente = new Usuario();
                    docente.setNombre(docenteNombre);  // Guardamos el valor que se dio por consola
                    docente.setIdusuario(idDocente);

                    decanoDao.actualizar(docente); // Se manda a actualizar
                    response.sendRedirect(request.getContextPath() + "/DecanoServlet");
                }else{

                    request.setAttribute("docenteEdit",decanoDao.buscarProfesorId(idDocente));
                    request.getRequestDispatcher("decano/form_edit_docente.jsp").forward(request,response);
                }
                break;
        }
    }
}

