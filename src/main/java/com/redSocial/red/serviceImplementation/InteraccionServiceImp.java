package com.redSocial.red.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redSocial.red.DTO.Request.InteraccionRequest;
import com.redSocial.red.DTO.Response.InteraccionResponse;
import com.redSocial.red.Repository.InteraccionRepository;
import com.redSocial.red.Repository.PublicacionRepository;
import com.redSocial.red.Repository.UsuarioRepository;
import com.redSocial.red.model.Interaccion;
import com.redSocial.red.model.Publicacion;
import com.redSocial.red.model.Usuario;
import com.redSocial.red.service.InteraccionService;

@Service
public class InteraccionServiceImp implements InteraccionService {
    @Autowired
    private InteraccionRepository interaccionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public List<InteraccionResponse> obtenerTodos() {
        return interaccionRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public InteraccionResponse obtenerPorId(Long id) {
        Interaccion interaccion = interaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interaccion no encontrada"));
        return toResponseDto(interaccion);
    }

    @Override
    public InteraccionResponse guardar(InteraccionRequest dto) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Publicacion publicacion = publicacionRepository.findById(dto.getPublicacionId())
                    .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));
            // buscar si ya existe interacción
            Interaccion interaccion = interaccionRepository
                    .findByUsuarioIdAndPublicacionId(dto.getUsuarioId(), dto.getPublicacionId())
                    .orElse(null);
            //si existe, le saca el mg
            if (interaccion != null) {
                interaccion.setLike(!interaccion.getLike());
            } else {
                // crea nueva
                interaccion = new Interaccion();
                interaccion.setUsuario(usuario);
                interaccion.setPublicacion(publicacion);
                interaccion.setLike(true); // por defecto like
            }
            Interaccion guardado = interaccionRepository.save(interaccion);
            return toResponseDto(guardado);
    }
    
    @Override
    public void eliminar(Long id) {
        interaccionRepository.deleteById(id);
    }

    // -------------------------
    // MAPEOS
    // -------------------------
    private InteraccionResponse toResponseDto(Interaccion interaccion) {
        InteraccionResponse dto = new InteraccionResponse();
        dto.setId(interaccion.getId());
        String usuario = interaccion.getUsuario().getNombreUsuario();
        String titulo = interaccion.getPublicacion().getTitulo();
        dto.setNombreUsuario(usuario);
        dto.setTituloPublicacion(titulo);
        dto.setLike(interaccion.getLike());
        return dto;
    }

    @Override
    public InteraccionResponse editar(Long id, InteraccionRequest dto) {
        Interaccion interaccion = interaccionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Interaccion no encontrada"));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = publicacionRepository.findById(dto.getPublicacionId())
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));
        interaccion.setUsuario(usuario);
        interaccion.setPublicacion(publicacion);
        interaccion.setLike(dto.getLike());
        Interaccion guardado = interaccionRepository.save(interaccion);
        return toResponseDto(guardado);
    }
}
