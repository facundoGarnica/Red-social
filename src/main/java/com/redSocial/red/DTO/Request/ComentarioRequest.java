package com.redSocial.red.DTO.Request;


public class ComentarioRequest {
    private Long usuarioId;
    private Long publicacionId;
    private String contenido;

    public ComentarioRequest() {
    }
    public ComentarioRequest(Long usuarioId, Long publicacionId, String contenido) {
        this.usuarioId = usuarioId;
        this.publicacionId = publicacionId;
        this.contenido = contenido;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public Long getPublicacionId() {
        return publicacionId;
    }
    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
