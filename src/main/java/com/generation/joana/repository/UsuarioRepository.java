package com.generation.joana.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.joana.model.Usuario;
import com.generation.joana.model.Usuario.TipoUsuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método para buscar usuário por e-mail
    Optional<Usuario> findByEmail(String email);

    // Método para buscar usuários por tipo (vendedor, comprador, administrador)
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
}