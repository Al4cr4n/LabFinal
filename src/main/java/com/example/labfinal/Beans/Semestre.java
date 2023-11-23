package com.example.labfinal.Beans;

public class Semestre {
    private int idsemestre;
    private String nombre;
    private Rol rol;
    private int habilitado;
    private String fecha_registro;
    private String fecha_edicion;

    public int getIdsemestre() {
        return idsemestre;
    }

    public void setIdsemestre(int idsemestre) {
        this.idsemestre = idsemestre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
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
