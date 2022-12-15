package com.example.proyectofinal.Modelos;

import com.google.gson.annotations.SerializedName;

public class RegistrarResponse {

    @SerializedName("title")
    private String title;
    @SerializedName("mensaje")
    private String mensaje;

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

    public RegistrarResponse(String title, String mensaje) {
        this.title = title;
        this.mensaje = mensaje;
    }




}
