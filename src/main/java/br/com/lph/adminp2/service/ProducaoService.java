package br.com.lph.adminp2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lph.adminp2.domain.ItensProducao;
import br.com.lph.adminp2.domain.Producao;
import br.com.lph.adminp2.domain.Produto;
import br.com.lph.adminp2.dto.ItensProducaoNewDTO;
import br.com.lph.adminp2.dto.ProducaoNewDTO;
import br.com.lph.adminp2.repositories.ItensProducaoRepository;
import br.com.lph.adminp2.repositories.ProducaoRepository;
import br.com.lph.adminp2.service.util.DataValidade;
import br.com.lph.adminp2.service.util.PrecoUnidade;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;

@Service
public class ProducaoService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private ProducaoRepository repo;
	
	@Autowired
	private ItensProducaoRepository itensProducaoRepository;
	
	@Autowired
	private UnidadeService unidadeService;
	
	@Autowired
	private ProdutoService produtoService;
	
	
	public Producao find(Integer id) {
		Optional<Producao> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Producao.class.getName()));
	}
	
	public Producao update(Producao obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public Producao fromDTO(ProducaoNewDTO obj) {
		Producao producao = new Producao(null, new Date(), obj.getPontoDeVenda(), unidadeService.find(obj.getUnidade()));
		return producao;
	}
	
	
	@Transactional
	public Producao insert(ProducaoNewDTO objDTO) {
		Producao obj = fromDTO(objDTO);
		obj = repo.save(obj);

		List<ItensProducao> lista = new ArrayList<>();
		for (ItensProducaoNewDTO ip : objDTO.getItensProducaoNewDTO()) {
			Produto prod = produtoService.find(ip.getProduto());
			lista.add(new ItensProducao(null, 
					ip.getQuantidade(), 
					new PrecoUnidade().buscar(prod.getDetalhesPorUnidades(), unidadeService.find(obj.getPontoDeVenda())),
					new DataValidade().calcula(new Date(), prod.getValidadeDias()),
					obj, 
					prod));
		}
		itensProducaoRepository.saveAll(lista);
		return obj;	
	}

}
