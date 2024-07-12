package com.generation.joana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatorio")
	@Size(max = 255, message = "o atributo deve conter no maximo 255 caracteres")
	private String nome;
	
	@NotNull(message = "O atributo quantidade é obrigatorio")
	private Integer quantidade;
	
	@NotNull(message = "O atributo preço é obrigatorio")
	private float preco;
	
	@NotBlank(message = "O atributo imagem é obrigatorio")
	private String image;
	
	@NotBlank(message = "O atributo descrição é obrigatorio")
	@Size(max = 500, message = "O atributo deve conter no maximo 500 caracteres")
	private String descricao;
}
