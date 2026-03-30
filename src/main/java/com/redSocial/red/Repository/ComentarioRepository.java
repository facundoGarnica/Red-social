package com.redSocial.red.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import com.redSocial.red.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long>{
    Page<Comentario> findByPublicacionId(Long publicacionId, Pageable pageable);
}
