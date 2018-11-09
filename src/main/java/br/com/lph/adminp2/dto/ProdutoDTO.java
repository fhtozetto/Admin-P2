package br.com.lph.adminp2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.lph.adminp2.domain.Produto;



public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento Obtigatório")
	@Length(min=5, max=100, message="O tamanho deve ser entre 5 e 100 caracteres.")
	private String nome;
	private Integer validadeDias;
	
	
	public ProdutoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.validadeDias = obj.getValidadeDias();
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


	public Integer getValidadeDias() {
		return validadeDias;
	}


	public void setValidadeDias(Integer validadeDias) {
		this.validadeDias = validadeDias;
	}

	
	
}
