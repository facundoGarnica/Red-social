package com.redSocial.red.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.redSocial.red.model.Publicacion;
import com.redSocial.red.model.Usuario;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long>{
    @Query("SELECT COUNT(i) FROM Interaccion i WHERE i.publicacion.id = :id")
    int contarLikes(Long id);
    @Query("SELECT COUNT(i) FROM Comentario i WHERE i.publicacion.id = :id")
    int contarComentarios(Long id);
    List<Publicacion> findByUsuario(Usuario usuario);
}
