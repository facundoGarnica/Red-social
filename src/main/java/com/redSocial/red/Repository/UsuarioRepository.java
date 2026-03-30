package com.redSocial.red.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.redSocial.red.model.Usuario;

@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, Long>{
    @Query("SELECT COUNT(i) FROM Publicacion i WHERE i.usuario.id = :id")
    int contarPublicaciones(Long id);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNombreUsuario(String nombre);
}
