package com.redSocial.red.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redSocial.red.DTO.Request.UsuarioRolRequest;
import com.redSocial.red.DTO.Response.UsuarioRolResponse;
import com.redSocial.red.Repository.RolRepository;
import com.redSocial.red.Repository.UsuarioRepository;
import com.redSocial.red.Repository.UsuarioRolRepository;
import com.redSocial.red.model.Rol;
import com.redSocial.red.model.Usuario;
import com.redSocial.red.model.UsuarioRol;
import com.redSocial.red.service.UsuarioRolService;

@Service
public class UsuarioRolServiceImp implements UsuarioRolService {
    @Autowired
    private UsuarioRolRepository usuarioRolRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<UsuarioRolResponse> obtenerTodos() {
        return usuarioRolRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public UsuarioRolResponse obtenerPorId(Long id) {
        UsuarioRol usuarioRol = usuarioRolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado"));
        return toResponseDto(usuarioRol);
    }

    @Override
    public UsuarioRolResponse guardar(UsuarioRolRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // Prevención de duplicados
        if (usuarioRolRepository.existsByUsuarioIdAndRolId(usuario.getId(), rol.getId())) {
            throw new RuntimeException("El usuario ya tiene asignado este rol");
        }

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        UsuarioRol guardado = usuarioRolRepository.save(usuarioRol);
        return toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRolRepository.deleteById(id);
    }

    // -------------------------
    // MAPEOS
    // -------------------------
    private UsuarioRolResponse toResponseDto(UsuarioRol usuarioRol) {
        UsuarioRolResponse dto = new UsuarioRolResponse();
        dto.setId(usuarioRol.getId());
        dto.setNombreUsuario(usuarioRol.getUsuario().getNombreUsuario());
        dto.setNombreRol(usuarioRol.getRol().getNombre());
        dto.setUsuarioId(usuarioRol.getUsuario().getId());
        dto.setRolId(usuarioRol.getRol().getId());
        return dto;
    }

    @Override
    public UsuarioRolResponse editar(Long id, UsuarioRolRequest dto) {
        // Buscar relación existente
        UsuarioRol usuarioRol = usuarioRolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // Prevención de duplicados al editar
        if (usuarioRolRepository.existsByUsuarioIdAndRolId(usuario.getId(), rol.getId()) &&
            !(usuarioRol.getUsuario().getId().equals(usuario.getId()) && usuarioRol.getRol().getId().equals(rol.getId()))) {
            throw new RuntimeException("El usuario ya tiene asignado este rol");
        }

        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        UsuarioRol guardado = usuarioRolRepository.save(usuarioRol);
        return toResponseDto(guardado);
    }
}