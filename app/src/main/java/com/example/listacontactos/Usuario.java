package com.example.listacontactos;

public class Usuario {

    private String nombre;
    private String correo;
    private String password;
    private String rpassword;
    private String id;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String password, String rpassword, String id) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rpassword = rpassword;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
