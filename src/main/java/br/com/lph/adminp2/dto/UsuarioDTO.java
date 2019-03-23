package br.com.lph.adminp2.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lph.adminp2.domain.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nomeUsuario;
	
	@JsonIgnore
	private String senha;
	

	public UsuarioDTO(Usuario usu) {
		super();
		this.id = usu.getId();
		this.nomeUsuario = usu.getNomeUsuario();
		this.senha = usu.getSenha();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
