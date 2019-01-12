package br.com.lph.adminp2.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lph.adminp2.domain.enums.Perfil;


@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	private String nomeCompleto;
	
	@Column(unique=true) //nome do usuario deve ser unico
	@NotEmpty(message="Preenchimento obrigatório!")
	private String nomeUsuario;
	
	@JsonIgnore // para que não mostre a senha em pesquisas (mesmo estando criptografada)
	@NotEmpty(message="Preenchimento obrigatório!")
	private String senha;

	@ManyToOne
	@JoinColumn(name="unidade_id")
	private Unidade unidade;
	
	@ElementCollection(fetch=FetchType.EAGER) // formaça trazer o perfis junto com o usuario
	@CollectionTable(name="PERFIS") // nome da tabela
	private Set<Integer> perfis = new HashSet<>();
	
	
	public Usuario() {
		super();
		addPerfil(Perfil.USUARIO);
	}

	public Usuario(Integer id, String nomeCompleto, String nomeUsuario, String senha, Unidade unidade) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.unidade = unidade;
		addPerfil(Perfil.USUARIO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
