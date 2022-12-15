package com.example.proyectofinal.Modelos;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {


    @SerializedName("title")
    private String title;
    @SerializedName("mensaje")
    private String mensaje;
    @SerializedName("route")
    private String route;
    @SerializedName("usuario")
    private Login usuario;


    public LoginResponse(String title, String mensaje, String route, Login usuario) {
        this.title = title;
        this.mensaje = mensaje;
        this.route = route;
        this.usuario = usuario;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Object getUsuario() {
        return usuario;
    }

    public void setUsuario(Login usuario) {
        this.usuario = usuario;
    }






}
