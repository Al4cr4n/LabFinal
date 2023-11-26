package com.example.labfinal.Daos;

import com.example.labfinal.Beans.*;
import com.example.labfinal.SHA256;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class DecanoDao extends DaoBase {
    public ArrayList<Usuario> listarDocentes() {
        ArrayList<Usuario> listaDocentes = new ArrayList<>();

        String sql = "SELECT\n" +
                "\t\tu.idusuario,\n" +
                "\t\tu.nombre AS nombre_usuario,\n" +
                "\t\tu.correo,\n" +
                "\t\tu.ultimo_ingreso,\n" +
                "\t\tu.cantidad_ingresos,\n" +
                "\t\tu.fecha_registro,\n" +
                "\t\tu.fecha_edicion, \n" +
                "        c.idfacultad,\n" +
                "\t\tc.nombre AS nombre_curso\n" +
                "        \n" +
                "\tFROM\n" +
                "\t\tlab_9.usuario u\n" +
                "\tleft join\n" +
                "\t\tlab_9.curso_has_docente cd ON u.idusuario = cd.iddocente\n" +
                "\tleft join\n" +
                "\t\tlab_9.curso c ON cd.idcurso = c.idcurso where idrol=4";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario docente = new Usuario();
                docente.setIdusuario(rs.getInt(1));
                docente.setNombre(rs.getString(2));
                docente.setCorreo(rs.getString(3));
                docente.setUltimo_ingreso(rs.getString(4));
                docente.setCantidad_ingresos(rs.getInt(5));
                docente.setFecha_registro(rs.getString(6));
                docente.setFecha_edicion(rs.getString(7));

                Curso_Has_Docente curso_has_docente = new Curso_Has_Docente();
                Facultad facultad_docente = new Facultad();
                facultad_docente.setIdfacultad(rs.getInt(8));
                Curso curso_docente = new Curso();
                curso_docente.setNombre(rs.getString(9));
                curso_docente.setFacultad(facultad_docente);
                curso_has_docente.setCurso(curso_docente);
                docente.setCurso_has_docente(curso_has_docente);

                listaDocentes.add(docente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaDocentes;
    }

    public boolean validarDocente(int idDocente) {
        boolean validarDocente = false;
        String sql = "SELECT * FROM curso_has_docente where iddocente = ?";

        try (Connection connection = getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, idDocente);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    validarDocente = true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return validarDocente;
    }

    public ArrayList<Curso_Has_Docente> listaCursoDocente() {
        ArrayList<Curso_Has_Docente> listaDocenteCurso = new ArrayList<>();

        String sql = "SELECT * FROM curso_has_docente";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso_Has_Docente curso_has_docente = new Curso_Has_Docente();
                Usuario docente = new Usuario();
                docente.setIdusuario(rs.getInt(2));
                curso_has_docente.setUsuario(docente);

                listaDocenteCurso.add(curso_has_docente);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaDocenteCurso;
    }

    public Usuario obtenerIdFacultad(int idDecano) {
        Usuario decano = new Usuario();

        String sql = "SELECT * FROM facultad_has_decano where iddecano = ?";
        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDecano);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Facultad_Has_Decano facultad_has_decano = new Facultad_Has_Decano();
                    Facultad facultad = new Facultad();
                    facultad.setIdfacultad(rs.getInt(1));
                    facultad_has_decano.setFacultad(facultad);
                    decano.setFacultad_has_decano(facultad_has_decano);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return decano;
    }

    public Usuario obtenerDocente(int docenteId) {
        Usuario docente = new Usuario();


        String sql = "SELECT * FROM usuario where idusuario=?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, docenteId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    docente.setIdusuario(rs.getInt(1));
                    docente.setNombre(rs.getString(2));
                    docente.setCorreo(rs.getString(3));


                    Rol rol = new Rol();
                    rol.setIdrol(rs.getInt(6));
                    docente.setRol(rol);

                    docente.setUltimo_ingreso(rs.getString(7));
                    docente.setCantidad_ingresos(rs.getInt(8));
                    docente.setFecha_registro(rs.getString(9));
                    docente.setFecha_edicion(rs.getString(10));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return docente;
    }

    public void borrarDocente(int docenteId) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, docenteId);
            pstmt.executeUpdate();
        }
    }

    public Usuario buscarPorCorreo(String correoDocente) {
        Usuario docente = null;
        //Falta ponerle el null
        String sql = "SELECT * FROM usuario where correo = ?";
        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoDocente);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    docente = new Usuario();
                    docente.setIdusuario(rs.getInt(1));
                    docente.setNombre(rs.getString(2));
                    docente.setCorreo(rs.getString(3));

                    Rol rol = new Rol();
                    rol.setIdrol(rs.getInt(6));
                    docente.setRol(rol);

                    docente.setUltimo_ingreso(rs.getString(7));
                    docente.setCantidad_ingresos(rs.getInt(8));
                    docente.setFecha_registro(rs.getString(9));
                    docente.setFecha_edicion(rs.getString(10));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return docente;
    }

    public void crear(String nombreDocente, String correoDocente, String contrasenaDocente) {
        int idDocente = 0;
        idDocente = obtenerUltimoId(idDocente) + 1;

        int rol = 4;



        String sql = "insert into usuario (idusuario, nombre, correo, password ,password_hasheado, idrol) values (?,?,?,?,SHA2(?,256),?)";
        //Tienes que sacar el Rol NOS quedamos aquí

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDocente);
            pstmt.setString(2, nombreDocente);
            pstmt.setString(3, correoDocente);
            pstmt.setString(4, contrasenaDocente);
            pstmt.setString(5, contrasenaDocente);
            pstmt.setInt(6, rol);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario buscarProfesorId(int idDocente) {
        Usuario docente = new Usuario();

        String sql = "SELECT * FROM usuario where correo = ?";
        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDocente);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    docente.setIdusuario(rs.getInt(1));
                    docente.setNombre(rs.getString(2));
                    docente.setCorreo(rs.getString(3));

                    Rol rol = new Rol();
                    rol.setIdrol(rs.getInt(6));
                    docente.setRol(rol);

                    docente.setUltimo_ingreso(rs.getString(7));
                    docente.setCantidad_ingresos(rs.getInt(8));
                    docente.setFecha_registro(rs.getString(9));
                    docente.setFecha_edicion(rs.getString(10));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return docente;

    }

    public void actualizar(Usuario docente) {


        String sql = "update usuario set nombre = ? where idusuario= ?";  //aquí creo que es el problema

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setInt(2, docente.getIdusuario());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int obtenerUltimoId(int docenteId) {
        Usuario docente = new Usuario();

        String sql = "SELECT MAX(idusuario) as ultimo_id FROM usuario";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    docente.setIdusuario(rs.getInt(1));
                    docenteId = docente.getIdusuario();
                }
            }

            

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return docenteId;
    }

    public ArrayList<Curso> listarCursos() {
        ArrayList<Curso> listarCursos = new ArrayList<>();

        String sql = "SELECT * FROM curso";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdcurso(rs.getInt(1));
                curso.setCodigo(rs.getString(2));
                curso.setNombre(rs.getString(3));

                Facultad facultad = new Facultad();
                facultad.setIdfacultad(rs.getInt(4));
                curso.setFacultad(facultad);

                curso.setFecha_registro(rs.getString(5));
                curso.setFecha_edicion(rs.getString(6));




                listarCursos.add(curso);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listarCursos;
    }

    public ArrayList<Evaluaciones> listaEvaluaciones() {
        ArrayList<Evaluaciones> listaEvaluaciones = new ArrayList<>();

        String sql = "SELECT * FROM evaluaciones";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Evaluaciones evaluaciones = new Evaluaciones();
                evaluaciones.setIdevaluaciones(rs.getInt(1));

                Curso curso = new Curso();
                curso.setIdcurso(rs.getInt(6));
                evaluaciones.setCurso(curso);

                listaEvaluaciones.add(evaluaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEvaluaciones;
    }
}



