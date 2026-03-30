package com.redSocial.red.service;

import java.util.List;

import com.redSocial.red.DTO.Request.InteraccionRequest;
import com.redSocial.red.DTO.Response.InteraccionResponse;

public interface InteraccionService {
    List<InteraccionResponse> obtenerTodos();
    InteraccionResponse obtenerPorId(Long id);
    InteraccionResponse guardar(InteraccionRequest producto);
    void eliminar(Long id);
    InteraccionResponse editar(Long id, InteraccionRequest dto);

}
