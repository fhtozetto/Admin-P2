package br.com.lph.adminp2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.Producao;
import br.com.lph.adminp2.repositories.ProducaoRepository;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;

@Service
public class ProducaoService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private ProducaoRepository repo;
	
	public Producao find(Integer id) {
		Optional<Producao> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Producao.class.getName()));
	}
	
	public Producao insert(Producao obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Producao update(Producao obj) {
		find(obj.getId());
		return repo.save(obj);
	}

}
