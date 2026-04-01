package com.redSocial.red.service;

import java.util.List;

import com.redSocial.red.DTO.Request.UsuarioRolRequest;
import com.redSocial.red.DTO.Response.UsuarioRolResponse;

public interface UsuarioRolService {
    List<UsuarioRolResponse> obtenerTodos();
    UsuarioRolResponse obtenerPorId(Long id);
    UsuarioRolResponse guardar(UsuarioRolRequest producto);
    void eliminar(Long id);
    UsuarioRolResponse editar(Long id, UsuarioRolRequest dto);
    List<UsuarioRolResponse> obtenerRolesPorUsuario(Long usuarioId);
}
