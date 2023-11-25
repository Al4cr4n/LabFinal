package com.example.labfinal.Beans;

public class Usuario {
    private int idusuario;
    private String nombre;
    private String correo;
    private String password;
    private Rol rol;
    private String ultimo_ingreso;
    private int cantidad_ingresos;
    private String fecha_registro;
    private String fecha_edicion;
    private Curso_Has_Docente curso_has_docente;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUltimo_ingreso() {
        return ultimo_ingreso;
    }

    public void setUltimo_ingreso(String ultimo_ingreso) {
        this.ultimo_ingreso = ultimo_ingreso;
    }

    public int getCantidad_ingresos() {
        return cantidad_ingresos;
    }

    public void setCantidad_ingresos(int cantidad_ingresos) {
        this.cantidad_ingresos = cantidad_ingresos;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getFecha_edicion() {
        return fecha_edicion;
    }

    public void setFecha_edicion(String fecha_edicion) {
        this.fecha_edicion = fecha_edicion;
    }

    public Curso_Has_Docente getCurso_has_docente() {
        return curso_has_docente;
    }

    public void setCurso_has_docente(Curso_Has_Docente curso_has_docente) {
        this.curso_has_docente = curso_has_docente;
    }
}
