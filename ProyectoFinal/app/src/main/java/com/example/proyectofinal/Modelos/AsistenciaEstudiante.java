package com.example.proyectofinal.Modelos;

public class AsistenciaEstudiante {

    public String fecha, nombre, apellido, cedula, foto_url;
    int estado;



    public AsistenciaEstudiante(String fecha, String nombre, String apellido, String cedula, String foto_url, int estado) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.foto_url = foto_url;
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }









}
