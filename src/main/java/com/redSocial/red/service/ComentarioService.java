package com.redSocial.red.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.redSocial.red.DTO.Request.ComentarioRequest;
import com.redSocial.red.DTO.Response.ComentarioResponse;
public interface ComentarioService {
    List<ComentarioResponse> obtenerTodos();
    Page<ComentarioResponse> obtenerPorPublicacion(Long publicacionId, Pageable pageable);
    ComentarioResponse obtenerPorId(Long id);
    ComentarioResponse guardar(ComentarioRequest producto);
    void eliminar(Long id);
    ComentarioResponse editar(Long id, ComentarioRequest dto);
}