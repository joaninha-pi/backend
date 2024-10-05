package com.generation.joana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.generation.joana.model.Produto;
import com.generation.joana.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	// Método para listar todos os produtos (disponível para todos os usuários)
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	// Método para buscar um produto por ID (disponível para todos os usuários)
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Método para criar um novo produto (disponível apenas para vendedores e
	// administradores)
	@PostMapping
	@PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	// Método para atualizar um produto (disponível apenas para vendedores e
	// administradores)
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		produto.setId(id);
		return ResponseEntity.ok(produtoRepository.save(produto));
	}

	// Método para excluir um produto (disponível apenas para vendedores e
	// administradores)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
	public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}