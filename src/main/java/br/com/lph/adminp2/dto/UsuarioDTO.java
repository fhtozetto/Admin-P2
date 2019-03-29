package br.com.lph.adminp2.dto;

import java.io.Serializable;

import br.com.lph.adminp2.domain.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nomeUsuario;
	private String nomeCompleto;
	private String email;
	

	public UsuarioDTO(Usuario usu) {
		super();
		this.id = usu.getId();
		this.nomeUsuario = usu.getNomeUsuario();
		this.nomeCompleto = usu.getNomeCompleto();
		this.email = usu.getEmail();
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

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
