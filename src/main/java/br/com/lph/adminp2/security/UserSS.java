package br.com.lph.adminp2.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.lph.adminp2.domain.enums.Perfil;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nomeUsuario;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	
	public UserSS() {
		super();
	}

	public UserSS(Integer id, String nomeUsuario, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return nomeUsuario;
	}

	@Override
	public boolean isAccountNonExpired() { // a conta não está expirada?
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // a conta não está bloqueada?
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // as credenciais não estão espiradas?
		return true;
	}

	@Override
	public boolean isEnabled() {// o usuário está habilitado (Ativo)?
		return true;
	}

}
