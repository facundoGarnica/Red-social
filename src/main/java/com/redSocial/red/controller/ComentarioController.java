package com.redSocial.red.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redSocial.red.DTO.Request.ComentarioRequest;
import com.redSocial.red.DTO.Response.ComentarioResponse;
import com.redSocial.red.service.ComentarioService;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {
    @Autowired
    private ComentarioService service;

    @GetMapping("/todos")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<ComentarioResponse> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/publicacion/{publicacionId}")
    public Page<ComentarioResponse> obtenerPorPublicacion(
            @PathVariable Long publicacionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.obtenerPorPublicacion(publicacionId, PageRequest.of(page, size));
    }

    @GetMapping("/buscar/{id}")
        @PreAuthorize("hasAnyRole('ADMIN')")
    public ComentarioResponse buscarPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping("/crear")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public ComentarioResponse crear(@RequestBody ComentarioRequest dato) {
        return service.guardar(dato);
    }

    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public ComentarioResponse editar(
            @PathVariable Long id,
            @RequestBody ComentarioRequest dto) {
        return service.editar(id, dto);
    }

}
