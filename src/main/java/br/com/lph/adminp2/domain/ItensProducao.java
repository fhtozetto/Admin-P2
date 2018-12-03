package br.com.lph.adminp2.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItensProducao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Double quantidade;
	private Double valorProduto;
	//private Double precoUnitarioVenda;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date validade;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="producao_id")
	private Producao producao;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;

	public ItensProducao() {
		super();
	}

	public ItensProducao(Integer id, Double quantidade, Double valorProduto, Date validade, Producao producao, Produto produto) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valorProduto = valorProduto;
		//this.precoUnitarioVenda = precoUnitarioVenda;
		this.validade = validade;
		this.producao = producao;
		this.produto = produto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public Producao getProducao() {
		return producao;
	}

	public void setProducao(Producao producao) {
		this.producao = producao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(Double valorProduto) {
		this.valorProduto = valorProduto;
	}
	
	/*
	public Double getPrecoUnitarioVenda() {
		return precoUnitarioVenda;
	}

	public void setPrecoUnitarioVenda(Double precoUnitarioVenda) {
		this.precoUnitarioVenda = precoUnitarioVenda;
	}
	*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensProducao other = (ItensProducao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
