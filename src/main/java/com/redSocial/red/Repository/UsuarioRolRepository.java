package com.redSocial.red.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redSocial.red.model.Usuario;
import com.redSocial.red.model.UsuarioRol;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol,Long>{
    boolean existsByUsuarioIdAndRolId(Long usuarioId, Long rolId);
    List<UsuarioRol> findByUsuario(Usuario usuario);
}
