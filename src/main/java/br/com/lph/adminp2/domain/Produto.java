package br.com.lph.adminp2.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private Integer validadeDias;
	
	@JsonBackReference // para o loop de ferencia cruzada
	@ManyToMany
	@JoinTable(name="PRODUTO_CATEGORIA", 
			joinColumns=@JoinColumn(name="produto_id"), 
			inverseJoinColumns=@JoinColumn(name="categoria_id")
	)
	private List<Categoria> categorias = new ArrayList<>();
	
	@OneToMany(mappedBy="produtos")
	private List<CodigoBarras> codigosBarras = new ArrayList<>();
	
	public Produto() {
		super();
	}

	public Produto(Integer id, String descricao, Integer validadeDias) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.validadeDias = validadeDias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getValidadeDias() {
		return validadeDias;
	}

	public void setValidadeDias(Integer validadeDias) {
		this.validadeDias = validadeDias;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<CodigoBarras> getCodigosBarras() {
		return codigosBarras;
	}

	public void setCodigosBarras(List<CodigoBarras> codigosBarras) {
		this.codigosBarras = codigosBarras;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
