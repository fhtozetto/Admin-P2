package br.com.lph.adminp2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DescarteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer Unidade;
	
	private List<ItensDescarteNewDTO> itensDescarteNewDTO = new ArrayList<>();

	public DescarteNewDTO() {
		super();
	}

	public DescarteNewDTO(Integer unidade) {
		super();
		this.Unidade = unidade;
	}

	public Integer getUnidade() {
		return Unidade;
	}

	public void setUnidade(Integer unidade) {
		Unidade = unidade;
	}

	public List<ItensDescarteNewDTO> getItensDescarteNewDTO() {
		return itensDescarteNewDTO;
	}

	public void setItensDescarteNewDTO(List<ItensDescarteNewDTO> itensDescarteNewDTO) {
		this.itensDescarteNewDTO = itensDescarteNewDTO;
	}
	
}
