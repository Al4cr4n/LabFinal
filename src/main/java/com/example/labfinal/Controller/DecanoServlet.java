package com.example.labfinal.Controller;

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

        switch (action) {
            case "lista":

                ArrayList<Usuario> listaDocentes = decanoDao.listarDocentes();
                ArrayList<Usuario> listaDocentesConCurso = new ArrayList<>();
                ArrayList<Usuario> listaDocentesSinCurso = new ArrayList<>();

                // Iterar a través de la lista usando un bucle for each
                for (Usuario docente : listaDocentes) {
                    // Acceder a cada miembro del objeto Docente
                    int idDocente = docente.getIdusuario();
                    boolean validarDocenteCursoAsignado= decanoDao.validarDocente(idDocente);
                    if(validarDocenteCursoAsignado){
                        listaDocentesConCurso.add(docente);
                    }else {
                        listaDocentesSinCurso.add(docente);
                    }
                }
                request.setAttribute("listaDocentes", listaDocentes);
                request.setAttribute("listaDocentesConCurso", listaDocentesConCurso);
                request.setAttribute("listaDocentesSinCurso", listaDocentesSinCurso);
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
                response.sendRedirect("EmployeeServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

