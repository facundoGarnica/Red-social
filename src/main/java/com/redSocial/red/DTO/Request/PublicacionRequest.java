package com.redSocial.red.DTO.Request;

public class PublicacionRequest {
    private String descripcion;
    private String titulo;
    private Long usuarioId;
    public PublicacionRequest() {
    }
    
    public PublicacionRequest(String descripcion, Long usuarioId, String titulo) {
        this.descripcion = descripcion;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
}
