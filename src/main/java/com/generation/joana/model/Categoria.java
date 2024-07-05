package com.generation.joana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome é obrigatório!")
	@Size(min = 3, max = 255, message = "O nome deve conter no mínimo 03 e no máximo 255 caracteres")
	private String nome;
	
	@Size(min = 10, max = 500, message = "A descrição deve conter no mínimo 10 e no máximo 500 caracteres")
	private String descricao;
}
