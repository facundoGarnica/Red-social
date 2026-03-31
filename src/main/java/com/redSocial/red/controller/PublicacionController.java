package com.redSocial.red.controller;

import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.redSocial.red.DTO.Request.PublicacionRequest;
import com.redSocial.red.DTO.Response.PublicacionResponse;
import com.redSocial.red.service.PublicacionService;

@RestController
@RequestMapping("/api/publicacion")
public class PublicacionController {
    @Autowired
    private PublicacionService service;

    @GetMapping("/todos")
    @PreAuthorize("hasAnyRole('ADMIN')")
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
    public PublicacionResponse crear(
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("usuarioId") Long usuarioId,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) throws IOException {

        // Guardar imagen en disco
        String imagenUrl = null;
        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
            Path ruta = Paths.get("uploads/" + nombreArchivo);
            Files.write(ruta, imagen.getBytes());
            imagenUrl = nombreArchivo;
        }

        // Crear DTO de request para el service
        PublicacionRequest dto = new PublicacionRequest(descripcion, usuarioId, titulo);

        // Llamar al service pasando también la ruta de la imagen
        return service.guardar(dto, imagenUrl);
    }

    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public PublicacionResponse editar(
            @PathVariable Long id,
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) throws IOException {

        String imagenUrl = null;
        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
            Path ruta = Paths.get("uploads/" + nombreArchivo);
            Files.write(ruta, imagen.getBytes());
            imagenUrl = nombreArchivo;
        }

        PublicacionRequest dto = new PublicacionRequest(descripcion, null, titulo);

        return service.editar(id, dto, imagenUrl);
    }
}
