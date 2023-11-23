package com.example.labfinal.Beans;

public class Evaluaciones {
    private int idevaluaciones;
    private String nombre_estudiante;
    private String codigo_estudiantes;
    private String correo_estudiantes;
    private int nota;
    private Curso curso;
    private Semestre semestre;
    private String fecha_registro;
    private String fecha_edicion;

    public int getIdevaluaciones() {
        return idevaluaciones;
    }

    public void setIdevaluaciones(int idevaluaciones) {
        this.idevaluaciones = idevaluaciones;
    }

    public String getNombre_estudiante() {
        return nombre_estudiante;
    }

    public void setNombre_estudiante(String nombre_estudiante) {
        this.nombre_estudiante = nombre_estudiante;
    }

    public String getCodigo_estudiantes() {
        return codigo_estudiantes;
    }

    public void setCodigo_estudiantes(String codigo_estudiantes) {
        this.codigo_estudiantes = codigo_estudiantes;
    }

    public String getCorreo_estudiantes() {
        return correo_estudiantes;
    }

    public void setCorreo_estudiantes(String correo_estudiantes) {
        this.correo_estudiantes = correo_estudiantes;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
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
