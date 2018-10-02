package br.com.lph.adminp2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.Categoria;
import br.com.lph.adminp2.repositories.CategoriaRepository;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;;

@Service
public class CategoriaService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

}
