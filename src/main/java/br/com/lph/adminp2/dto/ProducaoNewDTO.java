package br.com.lph.adminp2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProducaoNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer pontoDeVenda;
	private Integer Unidade;
	
	private List<ItensProducaoNewDTO> itensProducaoNewDTO = new ArrayList<>();

	public ProducaoNewDTO() {
		super();
	}

	public ProducaoNewDTO(Integer pontoDeVenda, Integer unidade) {
		super();
		this.pontoDeVenda = pontoDeVenda;
		this.Unidade = unidade;
	}

	public Integer getPontoDeVenda() {
		return pontoDeVenda;
	}

	public void setPontoDeVenda(Integer pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}

	public Integer getUnidade() {
		return Unidade;
	}

	public void setUnidade(Integer unidade) {
		Unidade = unidade;
	}

	public List<ItensProducaoNewDTO> getItensProducaoNewDTO() {
		return itensProducaoNewDTO;
	}

	public void setItensProducaos(List<ItensProducaoNewDTO> itensProducaoNewDTO) {
		this.itensProducaoNewDTO = itensProducaoNewDTO;
	}
	
}
