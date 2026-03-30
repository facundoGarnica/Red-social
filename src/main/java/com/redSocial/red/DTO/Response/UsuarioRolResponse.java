package com.redSocial.red.DTO.Response;

public class UsuarioRolResponse {
    private Long id;
    private Long usuarioId;
    private String nombreUsuario;
    private Long rolId;
    private String nombreRol;

    public UsuarioRolResponse() {
    }

    public UsuarioRolResponse(Long id, Long usuarioId, String nombreUsuario, Long rolId, String nombreRol) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.rolId = rolId;
        this.nombreRol = nombreRol;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public Long getRolId() {
        return rolId;
    }
    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }
    public String getNombreRol() {
        return nombreRol;
    }
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

}
