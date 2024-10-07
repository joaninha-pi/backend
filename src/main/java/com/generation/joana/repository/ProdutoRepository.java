package com.generation.joana.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.joana.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // Método personalizado para buscar produtos por nome (ou parte dele)
    List<Produto> findAllByNomeContainingIgnoreCase(String nome);

    // Método para buscar produtos dentro de um intervalo de preço
    List<Produto> findByPrecoBetween(BigDecimal minPreco, BigDecimal maxPreco);

    // Método para buscar produtos por categoria
    List<Produto> findByCategoriaId(Long categoriaId);
    
    // Método para buscar produtos por vendedor (usuário)
    List<Produto> findByUsuarioId(Long usuarioId);
}
