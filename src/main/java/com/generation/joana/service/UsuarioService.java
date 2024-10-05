package com.generation.joana.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.joana.model.UsuarioLogin;
import com.generation.joana.model.Usuario;
import com.generation.joana.repository.UsuarioRepository;
import com.generation.joana.security.JwtService;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			return Optional.empty();

		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getId()).isPresent()) {

			Optional<Usuario> buscaUsuario = usuarioRepository.findByEmail(usuario.getEmail());

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.ofNullable(usuarioRepository.save(usuario));

		}

		return Optional.empty();

	}

	public Optional<UsuarioLogin> autenticarUsuario(UsuarioLogin usuarioLogin) { // Remover Optional
		var credenciais = new UsernamePasswordAuthenticationToken(usuarioLogin.getUsuario(), usuarioLogin.getSenha());
	
		Authentication authentication = authenticationManager.authenticate(credenciais);
	
		if (authentication.isAuthenticated()) {
			Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.getUsuario());
			
			if (usuario.isPresent()) {
				UsuarioLogin loginResponse = new UsuarioLogin();
				loginResponse.setId(usuario.get().getId());
				loginResponse.setNome(usuario.get().getNome());
				loginResponse.setToken(gerarToken(usuarioLogin.getUsuario(), usuario.get().getTipoUsuario().toString()));
				loginResponse.setSenha(""); // Limpa a senha para segurança
				
				return Optional.of(loginResponse);
			}
		}
	
		return Optional.empty();
	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);

	}

	private String gerarToken(String usuario, String role) {
		return "Bearer " + jwtService.generateToken(usuario, role);
	}

}