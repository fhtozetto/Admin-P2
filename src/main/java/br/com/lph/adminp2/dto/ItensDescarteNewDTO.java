package br.com.lph.adminp2.dto;

import java.io.Serializable;

public class ItensDescarteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer produto;
	private Double quantidade;
	
	public ItensDescarteNewDTO() {
		super();
	}

	public ItensDescarteNewDTO(Integer produto, Double quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Integer getProduto() {
		return produto;
	}

	public void setProduto(Integer produto) {
		this.produto = produto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

}
