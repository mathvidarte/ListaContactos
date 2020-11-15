package com.example.listacontactos;

public class Usuario {

    private String elUsuario;
    private String id;

    public Usuario() {
    }

    public Usuario(String nombre, String id) {
        this.elUsuario = nombre;
        this.id = id;
    }

    public String getElUsuario() {
        return elUsuario;
    }

    public void setElUsuario(String elUsuario) {
        this.elUsuario = elUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
