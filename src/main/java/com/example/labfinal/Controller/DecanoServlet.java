package com.example.labfinal.Controller;

import com.example.labfinal.Beans.Curso_Has_Docente;
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
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        DecanoDao decanoDao = new DecanoDao();
        //Datos de sesión:
        //DelegadoGeneral delegadoGeneral = (DelegadoGeneral) request.getSession().getAttribute("usuariologueado");
        Usuario decano = (Usuario) request.getSession().getAttribute("usuariologueado");
        int idDecano= decano.getIdusuario();
        Usuario decanoActualizado= decanoDao.obtenerIdFacultad(idDecano);
        int idFacultadDecano = decanoActualizado.getFacultad_has_decano().getFacultad().getIdfacultad();
        //tengo que tener su idfalcutad o asociarlo
        System.out.println("Este es el ID de la facultad del decano : "+idFacultadDecano);
        System.out.println("Este es el nombre del decano: "+ decano.getNombre());
        switch (action) {
            case "lista":

                ArrayList<Usuario> listaDocentes = decanoDao.listarDocentes();
                ArrayList<Usuario> listaDocentesOficial = new ArrayList<>();
                ArrayList<Usuario> listaDocentesConFacultad = new ArrayList<>();
                ArrayList<Usuario> listaDocentesSinFacultadSinCursoAsignado = new ArrayList<>();
                for (Usuario docenteFacu : listaDocentes){
                    System.out.println("Nombres de los docentes: "+ docenteFacu.getNombre());
                    int idFacultadDocente = docenteFacu.getCurso_has_docente().getCurso().getFacultad().getIdfacultad();
                    System.out.println("Id de la facultad del docente: " + idFacultadDocente);
                    if (idFacultadDecano == idFacultadDocente){
                        listaDocentesOficial.add(docenteFacu);
                        //listaDocentesConFacultad.add(docenteFacu);
                        System.out.println("Encontramos un docente que pertecene a la facultad asignada al Decano\n");
                    }else {
                        if(idFacultadDocente==0) {
                            listaDocentesOficial.add(docenteFacu);
                            //listaDocentesSinFacultadSinCursoAsignado.add(docenteFacu);
                            System.out.println("Encontramos un docente sin facultad y sin curso asignado\n");
                        }
                    }

                }

                request.setAttribute("listaDocentesOficial", listaDocentesOficial);
                //request.setAttribute("listaDocentesConFacultad", listaDocentesConFacultad);
                //request.setAttribute("listaDocentesSinFacultadSinCursoAsignado", listaDocentesSinFacultadSinCursoAsignado);
                view = request.getRequestDispatcher("decano/listaDocentes.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                //request.setAttribute("listaTrabajos", jobDao.listarTrabajos());
                //request.setAttribute("listaDepartamentos", departmentDao.listaDepartamentos());
                //request.setAttribute("listaJefes", employeeDao.listarEmpleados());

                view = request.getRequestDispatcher("employees/formularioNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                HttpSession httpSession = request.getSession();
                //Employee employee = (Employee) httpSession.getAttribute("usuarioLogueado");

                /*if(employee != null && employee.getEmployeeId() > 0) {
                    if (request.getParameter("id") != null) {
                        String employeeIdString = request.getParameter("id");
                        int employeeId = 0;
                        try {
                            employeeId = Integer.parseInt(employeeIdString);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect("EmployeeServlet");
                        }

                        Employee emp = employeeDao.obtenerEmpleado(employeeId);

                        if (emp != null) {
                            request.setAttribute("empleado", emp);
                            request.setAttribute("listaTrabajos", jobDao.listarTrabajos());
                            request.setAttribute("listaDepartamentos", departmentDao.listaDepartamentos());
                            request.setAttribute("listaJefes", employeeDao.listarEmpleados());
                            view = request.getRequestDispatcher("employees/formularioEditar.jsp");
                            view.forward(request, response);
                        } else {
                            response.sendRedirect("EmployeeServlet");
                        }

                    } else {
                        response.sendRedirect("EmployeeServlet");
                    }
                } else {
                    response.sendRedirect("EmployeeServlet");
                }
                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                    }

                    Employee emp = employeeDao.obtenerEmpleado(employeeId);

                    if (emp != null) {
                        try {
                            employeeDao.borrarEmpleado(employeeId);
                            response.sendRedirect("EmployeeServlet?msg=Empleado borrado exitosamente");
                        } catch (SQLException e) {
                            response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                        }
                    }
                } else {
                    response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                }
                break;

                 */
            default:
                response.sendRedirect("DecanoServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

