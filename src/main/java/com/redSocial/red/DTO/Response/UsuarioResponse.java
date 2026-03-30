package com.redSocial.red.DTO.Response;

import java.time.LocalDateTime;
import java.util.List;

public class UsuarioResponse {
    private Long id;
    private String nombreUsuario;
    private String email;
    private LocalDateTime fechaCreacion;
    private List<String> roles;
    private int cantidadPublicaciones;

    public UsuarioResponse() {
    }
    
    public UsuarioResponse(Long id, String nombreUsuario, String email, LocalDateTime fechaCreacion, List<String> roles,
            int cantidadPublicaciones) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.fechaCreacion = fechaCreacion;
        this.roles = roles;
        this.cantidadPublicaciones = cantidadPublicaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public int getCantidadPublicaciones() {
        return cantidadPublicaciones;
    }

    public void setCantidadPublicaciones(int cantidadPublicaciones) {
        this.cantidadPublicaciones = cantidadPublicaciones;
    }

    
}
