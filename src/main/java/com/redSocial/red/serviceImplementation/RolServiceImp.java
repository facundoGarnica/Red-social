package com.redSocial.red.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redSocial.red.DTO.Request.RolRequest;
import com.redSocial.red.DTO.Response.RolResponse;
import com.redSocial.red.Repository.RolRepository;
import com.redSocial.red.model.Rol;
import com.redSocial.red.service.RolService;

@Service
public class RolServiceImp implements RolService{
    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<RolResponse> obtenerTodos() {
        return rolRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public RolResponse obtenerPorId(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return toResponseDto(rol);
    }

    @Override
    public RolResponse guardar(RolRequest dto) {

        Rol rol = new Rol();
        if(rolRepository.existsByNombre(dto.getNombre().toUpperCase())){
            throw new RuntimeException("El rol ya existe");
        }
            rol.setNombre(dto.getNombre());
            Rol guardado = rolRepository.save(rol);
            return toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }

    // -------------------------
    // MAPEOS
    // -------------------------
    private RolResponse toResponseDto(Rol rol) {
        RolResponse dto = new RolResponse();
        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());
        return dto;
    }

    @Override
    public RolResponse editar(Long id, RolRequest dto) {
        Rol rol = rolRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        rol.setNombre(dto.getNombre());
        Rol guardado = rolRepository.save(rol);
        return toResponseDto(guardado);
    }
}
