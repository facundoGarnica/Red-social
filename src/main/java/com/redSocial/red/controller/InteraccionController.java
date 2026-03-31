package com.redSocial.red.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redSocial.red.DTO.Request.InteraccionRequest;
import com.redSocial.red.DTO.Response.InteraccionResponse;
import com.redSocial.red.service.InteraccionService;

@RestController
@RequestMapping("/api/interaccion")
public class InteraccionController {
    @Autowired
    private InteraccionService service;

    @GetMapping("/todos")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<InteraccionResponse> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public InteraccionResponse buscarPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping("/crear")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public InteraccionResponse crear(@RequestBody InteraccionRequest dato) {
        return service.guardar(dato);
    }

    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public InteraccionResponse editar(
            @PathVariable Long id,
            @RequestBody InteraccionRequest dto) {
        return service.editar(id, dto);
    }

}
