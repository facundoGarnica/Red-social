package com.redSocial.red.service;

import java.util.List;
import com.redSocial.red.DTO.Request.PublicacionRequest;
import com.redSocial.red.DTO.Response.PublicacionResponse;

public interface PublicacionService {
    List<PublicacionResponse> obtenerTodos();
    List<PublicacionResponse> obtenerTodasPorUsuario(Long id);
    PublicacionResponse obtenerPorId(Long id);
    PublicacionResponse guardar(PublicacionRequest dto, String imagenUrl);
    void eliminar(Long id);
    PublicacionResponse editar(Long id, PublicacionRequest dto, String imagenUrl);

}
