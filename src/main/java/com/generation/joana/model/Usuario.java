package com.generation.joana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo noem é obrigatorio")
	private String nome;
	
	@Email(message = "O email deve seguir o padrão '@dominio.com'")
	@NotBlank(message = "O atributo email é obrigatorio")
	private String email;
	
	@NotBlank(message = "o atributo senha é obrigatorio")
	@Size(min = 8, message = "O atributo deve conter no minimo 8 caracteres")
	private String senha;
	
	private String foto;
}
