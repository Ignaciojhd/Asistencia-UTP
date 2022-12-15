package com.example.proyectofinal.Modelos;

public class SpinnerItem {

    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public SpinnerItem(int id, String name) {
        this.name = name;
        this.id = id;
    }






}
