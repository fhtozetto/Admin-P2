package br.com.lph.adminp2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.CodigoBarras;
import br.com.lph.adminp2.repositories.CodigoBarrasRepository;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;

@Service
public class CodigoBarrasService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private CodigoBarrasRepository repo;
	
	public CodigoBarras find(Integer id) {
		Optional<CodigoBarras> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + CodigoBarras.class.getName()));
	}
	
	public CodigoBarras insert(CodigoBarras obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public CodigoBarras update(CodigoBarras obj) {
		find(obj.getId());
		return repo.save(obj);
	}

}
