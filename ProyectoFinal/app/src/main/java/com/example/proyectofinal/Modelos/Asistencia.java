package com.example.proyectofinal.Modelos;


import com.google.gson.annotations.SerializedName;

public class Asistencia {

    @SerializedName("estudiante_id")
    private int estudiante_id;
    @SerializedName("grupo_asignatura")
    private int grupo_asignatura;
    @SerializedName("estado_asistencia_id")
    private int estado_asistencia_id;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("hora")
    private String hora;
    @SerializedName("id")
    private int id;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;

    public Asistencia(int id, int estudiante_id, int grupo_asignatura, int estado_asistencia_id, String fecha, String hora, String created_at, String updated_at) {
        this.id = id;
        this.estudiante_id = estudiante_id;
        this.grupo_asignatura = grupo_asignatura;
        this.estado_asistencia_id = estado_asistencia_id;
        this.fecha = fecha;
        this.hora = hora;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Asistencia(int estudiante_id, int grupo_asignatura, int estado_asistencia_id, String fecha, String hora) {
        this.estudiante_id = estudiante_id;
        this.grupo_asignatura = grupo_asignatura;
        this.estado_asistencia_id = estado_asistencia_id;
        this.fecha = fecha;
        this.hora = hora;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudiante_id() {
        return estudiante_id;
    }

    public void setEstudiante_id(int estudiante_id) {
        this.estudiante_id = estudiante_id;
    }

    public int getGrupo_asignatura() {
        return grupo_asignatura;
    }

    public void setGrupo_asignatura(int grupo_asignatura) {
        this.grupo_asignatura = grupo_asignatura;
    }

    public int getEstado_asistencia_id() {
        return estado_asistencia_id;
    }

    public void setEstado_asistencia_id(int estado_asistencia_id) {
        this.estado_asistencia_id = estado_asistencia_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }




}
