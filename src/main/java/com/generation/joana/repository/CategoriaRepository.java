package com.generation.joana.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.joana.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    // Método personalizado para buscar categorias pelo nome (ou parte dele)
    List<Categoria> findAllByNomeContainingIgnoreCase(String nome);
}
