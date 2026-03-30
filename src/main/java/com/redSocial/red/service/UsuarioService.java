package com.redSocial.red.service;

import java.util.List;

import com.redSocial.red.DTO.Request.UsuarioRequest;
import com.redSocial.red.DTO.Response.UsuarioResponse;

public interface UsuarioService {
    List<UsuarioResponse> obtenerTodos();
    UsuarioResponse obtenerPorId(Long id);
    UsuarioResponse guardar(UsuarioRequest producto);
    void eliminar(Long id);
    UsuarioResponse editar(Long id, UsuarioRequest dto);
}
