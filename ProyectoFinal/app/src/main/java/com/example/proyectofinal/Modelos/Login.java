package com.example.proyectofinal.Modelos;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("id")
    private int id;
    @SerializedName("role")
    private int role;
    @SerializedName("active")
    private int active;
    @SerializedName("nombres")
    private String nombres;
    @SerializedName("apellidos")
    private String apellidos;
    @SerializedName("email")
    private String email;
    @SerializedName("cedula")
    private String cedula;
    @SerializedName("email_verified_at")
    private String email_verified_at;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("docente_id")
    private int docente_id;
    @SerializedName("estudiante_id")
    private int estudiante_id;


    public int getEstudiante_id() {
        return estudiante_id;
    }

    public void setEstudiante_id(int estudiante_id) {
        this.estudiante_id = estudiante_id;
    }

    public int getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(int docente_id) {
        this.docente_id = docente_id;
    }




    public Login(int id, int role, int active, String nombres, String apellidos, String email, String cedula, String email_verified_at, String created_at, String updated_at) {
        this.id = id;
        this.role = role;
        this.active = active;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.cedula = cedula;
        this.email_verified_at = email_verified_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
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
