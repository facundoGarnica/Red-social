package com.redSocial.red.DTO.Response;

import java.time.LocalDateTime;

public class PublicacionResponse {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private String autor;
    private int cantidadLikes;
    private int cantidadComentarios;

    public PublicacionResponse() {
    }

    public PublicacionResponse(Long id, String titulo, String descripcion, LocalDateTime fechaCreacion,
            String nombreUsuario, int cantidadLikes, int cantidadComentarios) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.autor = nombreUsuario;
        this.cantidadLikes = cantidadLikes;
        this.cantidadComentarios = cantidadComentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombreUsuario() {
        return autor;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.autor = nombreUsuario;
    }

    public int getCantidadLikes() {
        return cantidadLikes;
    }

    public void setCantidadLikes(int cantidadLikes) {
        this.cantidadLikes = cantidadLikes;
    }

    public int getCantidadComentarios() {
        return cantidadComentarios;
    }

    public void setCantidadComentarios(int cantidadComentarios) {
        this.cantidadComentarios = cantidadComentarios;
    }

    
    
}
