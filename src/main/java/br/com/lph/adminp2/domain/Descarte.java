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

@Entity
public class Descarte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataDescarte;
	
	@OneToMany(mappedBy="descarte")
	private List<ItensDescarte> itensDescartes = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="unidade_id")
	private Unidade unidade;
	
	public Descarte() {
		super();
	}

	public Descarte(Integer id, Date dataDescarte, Unidade unidade) {
		super();
		this.id = id;
		this.dataDescarte = dataDescarte;
		this.unidade = unidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataDescarte() {
		return dataDescarte;
	}

	public void setDataDescarte(Date dataDescarte) {
		this.dataDescarte = dataDescarte;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<ItensDescarte> getItensDescartes() {
		return itensDescartes;
	}

	public void setItensDescartes(List<ItensDescarte> itensDescartes) {
		this.itensDescartes = itensDescartes;
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
		Descarte other = (Descarte) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
