package com.redSocial.red.DTO.Response;

import java.time.LocalDateTime;

public class ComentarioResponse {

    private Long id;
    private String nombreUsuario;
    private String tituloPublicacion;
    private String contenido;
    private LocalDateTime fecha;

    public ComentarioResponse() {}

    public ComentarioResponse(Long id, String nombreUsuario, String descripcionPublicacion, String contenido,
            LocalDateTime fecha) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.tituloPublicacion = descripcionPublicacion;
        this.contenido = contenido;
        this.fecha = fecha;
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

    public String getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setTituloPublicacion(String tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    
}