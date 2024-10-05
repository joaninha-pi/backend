package com.generation.joana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.generation.joana.model.ImagemProduto;
import com.generation.joana.repository.ImagemProdutoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagens")
public class ImagemProdutoController {

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    // Método para listar todas as imagens (disponível para todos os usuários)
    @GetMapping
    public ResponseEntity<List<ImagemProduto>> listarImagens() {
        return ResponseEntity.ok(imagemProdutoRepository.findAll());
    }

    // Método para buscar uma imagem por ID (disponível para todos os usuários)
    @GetMapping("/{id}")
    public ResponseEntity<ImagemProduto> buscarImagemPorId(@PathVariable Long id) {
        Optional<ImagemProduto> imagemProduto = imagemProdutoRepository.findById(id);
        return imagemProduto.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para criar uma nova imagem (disponível apenas para vendedores e administradores)
    @PostMapping
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<ImagemProduto> criarImagem(@RequestBody ImagemProduto imagemProduto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imagemProdutoRepository.save(imagemProduto));
    }

    // Método para atualizar uma imagem (disponível apenas para vendedores e administradores)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<ImagemProduto> atualizarImagem(@PathVariable Long id, @RequestBody ImagemProduto imagemProduto) {
        if (!imagemProdutoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        imagemProduto.setId(id);
        return ResponseEntity.ok(imagemProdutoRepository.save(imagemProduto));
    }

    // Método para excluir uma imagem (disponível apenas para vendedores e administradores)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> excluirImagem(@PathVariable Long id) {
        if (!imagemProdutoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        imagemProdutoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}