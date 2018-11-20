package br.com.lph.adminp2.dto;

import java.io.Serializable;

public class ProdutoNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	//CodigoBarras
	private String codBarras;
	private Double quantidade;
	//Produto
	private String nome;
	private Integer validadeDias;
	//ProdutoDetalhes
	private Double precoVenda;
	private Double precoCusto;
	private Double estoque;
	//Categoria
	private Integer categoria;
	//Unidade
	private Integer unidade;

	
	public ProdutoNewDTO() {
		super();
	}

	public ProdutoNewDTO(String codBarras,Double quantidade ,String nome, Integer validadeDias, Double precoVenda,
			Double precoCusto, Double estoque, Integer categoria, Integer unidade) {
		super();
		this.codBarras = codBarras;
		this.quantidade = quantidade;
		this.nome = nome;
		this.validadeDias = validadeDias;
		this.precoVenda = precoVenda;
		this.precoCusto = precoCusto;
		this.estoque = estoque;
		this.categoria = categoria;
		this.unidade = unidade;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
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

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Double getEstoque() {
		return estoque;
	}

	public void setEstoque(Double estoque) {
		this.estoque = estoque;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getUnidade() {
		return unidade;
	}

	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}

}
