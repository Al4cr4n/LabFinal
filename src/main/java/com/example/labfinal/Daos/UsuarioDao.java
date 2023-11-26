package com.example.labfinal.Daos;

import com.example.labfinal.Beans.Facultad;
import com.example.labfinal.Beans.Facultad_Has_Decano;
import com.example.labfinal.Beans.Rol;
import com.example.labfinal.Beans.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao extends DaoBase{
    public int validarUsuarioPassword(String correo, String password){

        int tipoUsuario = 0;//1: administrador | 2: rector | 3: decano | 4: docente |  0:no encontrado


        String sql = "SELECT * FROM usuario where correo= ? and password_hasheado= SHA2(?,256) and idrol = 1";
        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,correo);
            pstmt.setString(2,password);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    tipoUsuario = 1; //es un administrador
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql2 = "SELECT * FROM usuario where correo= ? and password_hasheado= SHA2(?,256) and idrol = 2";
        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql2)){
            pstmt.setString(1,correo);
            pstmt.setString(2,password);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    tipoUsuario = 2; //es un rector
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        String sql3 = "SELECT * FROM usuario where correo= ? and password_hasheado= SHA2(?,256) and idrol = 3";
        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql3)){
            pstmt.setString(1,correo);
            pstmt.setString(2,password);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    tipoUsuario = 3; // es un decano
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        String sql4 = "SELECT * FROM usuario where correo= ? and password_hasheado= SHA2(?,256) and idrol = 4";
        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql4)){
            pstmt.setString(1,correo);
            pstmt.setString(2,password);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    tipoUsuario = 4; // es un docente
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return tipoUsuario;
    }


    /*public Usuario obtenerAdministrador(String correo){

    }*/

    /*public Usuario obtenerRector(String correo){

    }*/

    public Usuario obtenerDecano(String correo){
        Usuario decano = new Usuario();


        String sql = "\tSELECT\n" +
                "\t\tu.idusuario,\n" +
                "\t\tu.nombre AS nombre_usuario,\n" +
                "\t\tu.correo,\n" +
                "\t\tu.ultimo_ingreso,\n" +
                "\t\tu.cantidad_ingresos,\n" +
                "\t\tu.fecha_registro,\n" +
                "\t\tu.fecha_edicion,\n" +
                "        u.idrol,        \n" +
                "        c.idfacultad,\n" +
                "\t\tc.nombre AS nombre_curso\n" +
                "        \n" +
                "\tFROM\n" +
                "\t\tlab_9.usuario u\n" +
                "\tleft join\n" +
                "\t\tlab_9.curso_has_docente cd ON u.idusuario = cd.iddocente\n" +
                "\tleft join\n" +
                "\t\tlab_9.curso c ON cd.idcurso = c.idcurso where idrol=3 and u.correo = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("No Entramos");
                if (rs.next()) {
                    System.out.println("Entramos");
                    decano.setIdusuario(rs.getInt(1));
                    decano.setNombre(rs.getString(2));
                    decano.setCorreo(rs.getString(3));
                    decano.setUltimo_ingreso(rs.getString(4));
                    decano.setCantidad_ingresos(rs.getInt(5));
                    decano.setFecha_registro(rs.getString(6));
                    decano.setFecha_edicion(rs.getString(7));

                    Rol rol = new Rol();
                    rol.setIdrol(rs.getInt(8));
                    decano.setRol(rol);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return decano;
    }
    public Usuario obtenerDocente(String correo){
        Usuario docente = new Usuario();


        String sql = "select * from usuario where correo = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    docente.setIdusuario(rs.getInt("idusuario"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setCorreo(rs.getString("correo"));
                    docente.setUltimo_ingreso(rs.getString("ultimo_ingreso"));
                    docente.setCantidad_ingresos(rs.getInt("cantidad_ingresos"));
                    docente.setFecha_registro(rs.getString("fecha_registro"));
                    docente.setFecha_edicion(rs.getString("fecha_edicion"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return docente;
    }

}
