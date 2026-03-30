package com.redSocial.red.DTO.Request;

public class UsuarioRolRequest {
    private Long usuarioId;
    private Long rolId;

    public UsuarioRolRequest() {
    }
    
    public UsuarioRolRequest(Long usuarioId, Long rolId) {
        this.usuarioId = usuarioId;
        this.rolId = rolId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public Long getRolId() {
        return rolId;
    }
    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    
}
