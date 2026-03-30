package com.redSocial.red.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redSocial.red.DTO.Request.PublicacionRequest;
import com.redSocial.red.DTO.Response.PublicacionResponse;
import com.redSocial.red.Repository.PublicacionRepository;
import com.redSocial.red.Repository.UsuarioRepository;
import com.redSocial.red.model.Publicacion;
import com.redSocial.red.model.Usuario;
import com.redSocial.red.service.PublicacionService;

@Service
public class PublicacionServiceImp implements PublicacionService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public List<PublicacionResponse> obtenerTodos() {
        return publicacionRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }
    @Override
    public List<PublicacionResponse> obtenerTodasPorUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return publicacionRepository.findByUsuario(usuario)
            .stream().map(this::toResponseDto)
            .toList();
    }

    @Override
    public PublicacionResponse obtenerPorId(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));
        return toResponseDto(publicacion);
    }

    @Override
    public PublicacionResponse guardar(PublicacionRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = new Publicacion();
        publicacion.setUsuario(usuario);
        publicacion.setTitulo(dto.getTitulo());
        publicacion.setDescripcion(dto.getDescripcion());
        Publicacion guardado = publicacionRepository.save(publicacion);
        return toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        publicacionRepository.deleteById(id);
    }

    // -------------------------
    // MAPEOS
    // -------------------------
    private PublicacionResponse toResponseDto(Publicacion publicacion) {
        PublicacionResponse dto = new PublicacionResponse();
        dto.setId(publicacion.getId());
        String usuario = publicacion.getUsuario().getNombreUsuario();
        dto.setNombreUsuario(usuario);
        String titulo = publicacion.getTitulo();
        dto.setTitulo(titulo);
        dto.setDescripcion(publicacion.getDescripcion());
        dto.setFechaCreacion(publicacion.getFechaCreacion());
        dto.setCantidadLikes(publicacionRepository.contarLikes(publicacion.getId()));
        dto.setCantidadComentarios(publicacionRepository.contarComentarios(publicacion.getId()));
        return dto;
    }

    @Override
    public PublicacionResponse editar(Long id, PublicacionRequest dto) {
        Publicacion publicacion = publicacionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));
        publicacion.setDescripcion(dto.getDescripcion());
        publicacion.setTitulo(dto.getTitulo());
        Publicacion guardado = publicacionRepository.save(publicacion);
        return toResponseDto(guardado);
    }
}
