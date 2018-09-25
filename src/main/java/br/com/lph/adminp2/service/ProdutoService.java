package br.com.lph.adminp2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.Produto;
import br.com.lph.adminp2.repositories.ProdutoRepository;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private ProdutoRepository repo;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Produto insert(Produto obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Produto update(Produto obj) {
		find(obj.getId());
		return repo.save(obj);
	}

}
