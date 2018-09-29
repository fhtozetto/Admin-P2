package br.com.lph.adminp2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.Descarte;
import br.com.lph.adminp2.repositories.DescarteRepository;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;

@Service
public class DescarteService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private DescarteRepository repo;
	
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

}
