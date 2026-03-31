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

import com.redSocial.red.DTO.Request.RolRequest;
import com.redSocial.red.DTO.Response.RolResponse;
import com.redSocial.red.service.RolService;

@RestController
@RequestMapping("/admin/rol")
public class RolController {
    @Autowired
    private RolService service;

    @GetMapping("/todos")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<RolResponse> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public RolResponse buscarPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping("/crear")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public RolResponse crear(@RequestBody RolRequest dato) {
        return service.guardar(dato);
    }

    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public RolResponse editar(
            @PathVariable Long id,
            @RequestBody RolRequest dto) {
        return service.editar(id, dto);
    }
}
