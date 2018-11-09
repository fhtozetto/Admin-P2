package br.com.lph.adminp2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.Unidade;
import br.com.lph.adminp2.dto.UnidadeDTO;
import br.com.lph.adminp2.repositories.UnidadeRepository;
import br.com.lph.adminp2.services.exceptions.DataIntegrityException;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;;

@Service
public class UnidadeService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private UnidadeRepository repo;
	
	public Unidade find(Integer id) {
		Optional<Unidade> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Unidade.class.getName()));
	}
	
	public Unidade insert(Unidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Unidade update(Unidade obj) {
		Unidade newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Unidade que possui produtos vinculados!");
		}	
	}
	
	public List<Unidade> findAll() {
		return repo.findAll();
	}
	
	public Page<Unidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Unidade fromDTO(UnidadeDTO objDTO) {
		return new Unidade(objDTO.getId(), objDTO.getNome(), objDTO.getFone());
	}
	
	private void updateData(Unidade newObj, Unidade obj) {
		newObj.setNome(obj.getNome());
	}
}
