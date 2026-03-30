package com.redSocial.red.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.redSocial.red.DTO.Request.UsuarioRequest;
import com.redSocial.red.DTO.Request.UsuarioRolRequest;
import com.redSocial.red.DTO.Response.UsuarioResponse;
import com.redSocial.red.Repository.RolRepository;
import com.redSocial.red.Repository.UsuarioRepository;
import com.redSocial.red.model.Rol;
import com.redSocial.red.model.Usuario;
import com.redSocial.red.service.UsuarioRolService;
import com.redSocial.red.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRolService usuarioRolService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public List<UsuarioResponse> obtenerTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public UsuarioResponse obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponseDto(usuario);
    }

    @Override
    public UsuarioResponse guardar(UsuarioRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        Usuario guardado = usuarioRepository.save(usuario);

        Rol rol = rolRepository.findByNombre("ROLE_USUARIO")
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        UsuarioRolRequest usuarioRolRequest = new UsuarioRolRequest();
        usuarioRolRequest.setRolId(rol.getId());
        usuarioRolRequest.setUsuarioId(guardado.getId());

        usuarioRolService.guardar(usuarioRolRequest);

        return toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // -------------------------
    // MAPEOS
    // -------------------------
    private UsuarioResponse toResponseDto(Usuario usuario) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(usuario.getId());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setFechaCreacion(usuario.getFechaCreacion());
        dto.setEmail(usuario.getEmail());
        dto.setCantidadPublicaciones(usuarioRepository.contarPublicaciones(usuario.getId()));
        return dto;
    }

    @Override
    public UsuarioResponse editar(Long id, UsuarioRequest dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        Usuario guardado = usuarioRepository.save(usuario);
        return toResponseDto(guardado);
    }
}