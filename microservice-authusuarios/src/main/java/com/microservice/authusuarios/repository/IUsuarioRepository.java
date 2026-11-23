package com.microservice.authusuarios.repository;

import com.microservice.authusuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Boolean existsByNombreUsuario(String nombreUsuario);

    Boolean existsByEmail(String email);

    boolean existsByNombreUsuarioAndIdUsuarioNot(String nombreUsuario, Long idUsuario);

    boolean existsByEmailAndIdUsuarioNot(String email, Long idUsuario);
}
