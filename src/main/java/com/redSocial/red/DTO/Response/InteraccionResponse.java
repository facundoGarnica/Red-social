package com.redSocial.red.DTO.Response;

public class InteraccionResponse {
    private Long id;
    private String nombreUsuario;
    private String tituloPublicacion;
    private Boolean like;

    public InteraccionResponse() {
    }

    public InteraccionResponse(Long id, String nombreUsuario, String descripcionPublicacion, Boolean like) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.tituloPublicacion = descripcionPublicacion;
        this.like = like;
        
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

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }


    
}
