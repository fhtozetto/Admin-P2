package br.com.lph.adminp2.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItensDescarte implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Double quantidade;
	private Double precoVenda;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="descarte_id")
	private Descarte descarte;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	public ItensDescarte() {
		super();
	}

	public ItensDescarte(Integer id, Double quantidade, Double precoVenda, Descarte descarte, Produto produto) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.descarte = descarte;
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

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Descarte getDescarte() {
		return descarte;
	}

	public void setDescarte(Descarte descarte) {
		this.descarte = descarte;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	

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
		ItensDescarte other = (ItensDescarte) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
