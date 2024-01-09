package com.hygorluciano.lojaderoupa.domain.repository;

import com.hygorluciano.lojaderoupa.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
}
