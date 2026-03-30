package com.redSocial.red.DTO.Request;

public class InteraccionRequest {
    private Long usuarioId;
    private Long publicacionId;
    private Boolean like;
    
    public InteraccionRequest() {
    }
    public InteraccionRequest(Long usuarioId, Long publicacionId, Boolean like) {
        this.usuarioId = usuarioId;
        this.publicacionId = publicacionId;
        this.like = like;
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
    public Boolean getLike() {
        return like;
    }
    public void setLike(Boolean like) {
        this.like = like;
    }

    
}
