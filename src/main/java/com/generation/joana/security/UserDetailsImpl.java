package com.generation.joana.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.joana.model.Usuario;

public class UserDetailsImpl implements UserDetails {
    private String userName;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public UserDetailsImpl(Usuario user) {
        this.userName = user.getEmail();
        this.password = user.getSenha();
        this.authorities = new ArrayList<>(); // Inicializa a coleção aqui
        this.authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getTipoUsuario().name()));
    }

	public UserDetailsImpl() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}