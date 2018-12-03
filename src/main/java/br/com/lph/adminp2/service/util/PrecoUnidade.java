package br.com.lph.adminp2.service.util;

import java.io.Serializable;
import java.util.Set;

import br.com.lph.adminp2.domain.ProdutoDetalhes;
import br.com.lph.adminp2.domain.Unidade;

public class PrecoUnidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Double precoVenda;
	
	public Double buscar(Set<ProdutoDetalhes> lista, Unidade unidade) {
		for (ProdutoDetalhes pd : lista) {
			if (pd.getUnidade().equals(unidade)) {
				precoVenda = pd.getPrecoVenda();
			}
		}
		return precoVenda;
	}

}
