package com.redSocial.red.service;

import java.util.List;
import com.redSocial.red.DTO.Request.RolRequest;
import com.redSocial.red.DTO.Response.RolResponse;

public interface RolService {
    List<RolResponse> obtenerTodos();
    RolResponse obtenerPorId(Long id);
    RolResponse guardar(RolRequest producto);
    void eliminar(Long id);
    RolResponse editar(Long id, RolRequest dto);
}
