package br.com.lph.adminp2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lph.adminp2.domain.Descarte;
import br.com.lph.adminp2.domain.ItensDescarte;
import br.com.lph.adminp2.domain.Produto;
import br.com.lph.adminp2.dto.DescarteNewDTO;
import br.com.lph.adminp2.dto.ItensDescarteNewDTO;
import br.com.lph.adminp2.repositories.DescarteRepository;
import br.com.lph.adminp2.repositories.ItensDescarteRepository;
import br.com.lph.adminp2.service.util.PrecoUnidade;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;

@Service
public class DescarteService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private DescarteRepository repo;
	
	@Autowired
	private UnidadeService unidadeService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItensDescarteRepository itensDescarteRepository;
	
	
	public Descarte find(Integer id) {
		Optional<Descarte> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Descarte.class.getName()));
	}
	
	public Descarte insert(Descarte obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Descarte update(Descarte obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public Descarte fromDTO(DescarteNewDTO objDTO) {
		Descarte obj = new Descarte(null, new Date(), unidadeService.find(objDTO.getUnidade()));
		return obj;
	}
	
	@Transactional
	public Descarte insert(DescarteNewDTO objDTO) {
		Descarte obj = fromDTO(objDTO);
		obj = repo.save(obj);

		List<ItensDescarte> lista = new ArrayList<>();
		for (ItensDescarteNewDTO id : objDTO.getItensDescarteNewDTO()) {
			Produto prod = produtoService.find(id.getProduto());
			lista.add(new ItensDescarte(null, 
					id.getQuantidade(), 
					new PrecoUnidade().buscar(prod.getDetalhesPorUnidades(), obj.getUnidade()),
					obj, 
					prod));
		}
		itensDescarteRepository.saveAll(lista);
		return obj;	
	}

}
