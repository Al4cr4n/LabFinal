package com.example.labfinal.Beans;

public class Curso {
    private int idcurso;
    private String codigo;
    private String nombre;
    private Facultad facultad;
    private String fecha_registro;
    private String fecha_edicion;

    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
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
}
