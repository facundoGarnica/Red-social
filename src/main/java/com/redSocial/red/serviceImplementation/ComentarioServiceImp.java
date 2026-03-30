package com.redSocial.red.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redSocial.red.DTO.Request.ComentarioRequest;
import com.redSocial.red.DTO.Response.ComentarioResponse;
import com.redSocial.red.Repository.ComentarioRepository;
import com.redSocial.red.Repository.PublicacionRepository;
import com.redSocial.red.Repository.UsuarioRepository;
import com.redSocial.red.model.Comentario;
import com.redSocial.red.model.Publicacion;
import com.redSocial.red.model.Usuario;
import com.redSocial.red.service.ComentarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ComentarioServiceImp implements ComentarioService{
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public List<ComentarioResponse> obtenerTodos() {
        return comentarioRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public Page<ComentarioResponse> obtenerPorPublicacion(Long publicacionId, Pageable pageable) {
        return comentarioRepository
            .findByPublicacionId(publicacionId, pageable)
            .map(this::toResponseDto);
    }

    @Override
    public ComentarioResponse obtenerPorId(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        return toResponseDto(comentario);
    }

    @Override
    public ComentarioResponse guardar(ComentarioRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = publicacionRepository.findById(dto.getPublicacionId())
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));

        Comentario comentario = new Comentario();
        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);
        comentario.setContenido(dto.getContenido());
        Comentario guardado = comentarioRepository.save(comentario);
        return toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        comentarioRepository.deleteById(id);
    }

    // -------------------------
    // MAPEOS
    // -------------------------
    private ComentarioResponse toResponseDto(Comentario comentario) {
        ComentarioResponse dto = new ComentarioResponse();
        dto.setId(comentario.getId());
        String usuario = comentario.getUsuario().getNombreUsuario();
        dto.setNombreUsuario(usuario);
        String titulo = comentario.getPublicacion().getTitulo();
        dto.setTituloPublicacion(titulo);
        dto.setContenido(comentario.getContenido());
        dto.setFecha(comentario.getFecha());
        return dto;
    }

    @Override
    public ComentarioResponse editar(Long id, ComentarioRequest dto) {
        Comentario comentario = comentarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = publicacionRepository.findById(dto.getPublicacionId())
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));
        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);
        comentario.setContenido(dto.getContenido());
        Comentario guardado = comentarioRepository.save(comentario);
        return toResponseDto(guardado);
    }

}
