package br.com.lph.adminp2.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Unidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String fone;
	
	@JsonIgnore
	@OneToMany(mappedBy="unidade")
	private List<Producao> producoes = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="unidade")
	private List<Descarte> descartes =  new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="id.unidade")
	private Set<ProdutoDetalhes> produtosPorUnidade = new HashSet<>(); 
	
	
	public Unidade() {
		super();
	}

	public Unidade(Integer id, String nome, String fone) {
		super();
		this.id = id;
		this.nome = nome;
		this.fone = fone;
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

	public Set<ProdutoDetalhes> getProdutosPorUnidade() {
		return produtosPorUnidade;
	}

	public void setProdutosPorUnidade(Set<ProdutoDetalhes> produtosPorUnidade) {
		this.produtosPorUnidade = produtosPorUnidade;
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
		Unidade other = (Unidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
