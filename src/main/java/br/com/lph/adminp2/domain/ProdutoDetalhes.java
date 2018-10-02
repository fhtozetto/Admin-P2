package br.com.lph.adminp2.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ProdutoDetalhes implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProdutoDetalhesPK id = new ProdutoDetalhesPK();
	
	private Double precoVenda;
	private Double precoCusto;
	private Double estoque;
	
	
	public ProdutoDetalhes() {
		super();
	}

	public ProdutoDetalhes(Produto produto, Unidade unidade, Double precoVenda, Double precoCusto, Double estoque) {
		super();
		id.setProduto(produto);
		id.setUnidade(unidade);
		this.precoVenda = precoVenda;
		this.precoCusto = precoCusto;
		this.estoque = estoque;
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public Unidade getUnidade() {
		return id.getUnidade();
	}

	public ProdutoDetalhesPK getId() {
		return id;
	}

	public void setId(ProdutoDetalhesPK id) {
		this.id = id;
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
		ProdutoDetalhes other = (ProdutoDetalhes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
