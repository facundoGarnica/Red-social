package com.redSocial.red.DTO.Request;

public class RolRequest {
    private String nombre;

    
    public RolRequest(String nombre) {
        this.nombre = nombre;
    }

    public RolRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
