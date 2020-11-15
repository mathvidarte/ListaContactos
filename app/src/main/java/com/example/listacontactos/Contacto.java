package com.example.listacontactos;

import androidx.annotation.NonNull;

public class Contacto {

    private String nombre;
    private String telefono;
    private String id;

    public Contacto() {
    }

    public Contacto(String nombre, String telefono, String id) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
   public String toString() {
        return nombre+"\n"+telefono;
    }
}
