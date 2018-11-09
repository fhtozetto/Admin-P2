package br.com.lph.adminp2.dto;

import java.io.Serializable;

import br.com.lph.adminp2.domain.Unidade;

public class UnidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String fone;
	
	public UnidadeDTO() {
		super();
	}
	
	public UnidadeDTO(Unidade obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.fone = obj.getFone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}
	
}
