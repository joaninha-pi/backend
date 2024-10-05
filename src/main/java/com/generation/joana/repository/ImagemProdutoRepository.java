package com.generation.joana.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.joana.model.ImagemProduto;

public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
    
    // Método personalizado para buscar imagens por ID do produto
    List<ImagemProduto> findByProdutoId(Long produtoId);
}