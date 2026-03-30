package com.redSocial.red.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redSocial.red.model.Interaccion;

@Repository
public interface InteraccionRepository extends JpaRepository<Interaccion,Long>{
    Optional<Interaccion> findByUsuarioIdAndPublicacionId(Long usuarioId, Long publicacionId);
}
