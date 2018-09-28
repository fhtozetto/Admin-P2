package br.com.lph.adminp2.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Producao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataProducao;
	private Integer pontoDeVenda;
	
	@ManyToOne
	@JoinColumn(name="unidade_id")
	private Unidade unidade;
	

	@OneToMany(mappedBy="producao")
	private List<ItensProducao> itensProducaos = new ArrayList<>();
	
	public Producao() {
		super();
	}

	public Producao(Integer id, Date dataProducao, Integer pontoDeVenda,Unidade unidade) {
		super();
		this.id = id;
		this.dataProducao = dataProducao;
		this.pontoDeVenda = pontoDeVenda;
		this.unidade = unidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataProducao() {
		return dataProducao;
	}

	public void setDataProducao(Date dataProducao) {
		this.dataProducao = dataProducao;
	}

	public Integer getPontoDeVenda() {
		return pontoDeVenda;
	}

	public void setPontoDeVenda(Integer pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<ItensProducao> getItensProducaos() {
		return itensProducaos;
	}

	public void setItensProducaos(List<ItensProducao> itensProducaos) {
		this.itensProducaos = itensProducaos;
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
		Producao other = (Producao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
