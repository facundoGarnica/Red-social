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

import com.redSocial.red.DTO.Request.PublicacionRequest;
import com.redSocial.red.DTO.Response.PublicacionResponse;
import com.redSocial.red.service.PublicacionService;

@RestController
@RequestMapping("/api/publicacion")
public class PublicacionController {
    @Autowired
    private PublicacionService service;

    @GetMapping("/todos")
    public List<PublicacionResponse> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/todasporusuario/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public List<PublicacionResponse> obtenerTodasPorUsuario(@PathVariable Long id) {
        return service.obtenerTodasPorUsuario(id);
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public PublicacionResponse buscarPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping("/crear")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public PublicacionResponse crear(@RequestBody PublicacionRequest dato) {
        return service.guardar(dato);
    }

    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public PublicacionResponse editar(
            @PathVariable Long id,
            @RequestBody PublicacionRequest dto) {
        return service.editar(id, dto);
    }
}
