package br.com.lph.adminp2.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeUsuario;
	private String senha;
	
	
	public CredenciaisDTO() {
		super();
	}
		
	public CredenciaisDTO(String nomeUsuario, String senha) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
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
