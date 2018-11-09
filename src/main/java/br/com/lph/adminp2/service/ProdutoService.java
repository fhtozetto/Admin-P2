package br.com.lph.adminp2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.Produto;
import br.com.lph.adminp2.dto.ProdutoDTO;
import br.com.lph.adminp2.repositories.ProdutoRepository;
import br.com.lph.adminp2.services.exceptions.DataIntegrityException;
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
	
	public Produto update(Produto obj) {
		Produto newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Produto que possui movimentação!");
		}	
	}
	
	public List<Produto> findAll() {
		return repo.findAll();
	}
	
	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Produto fromDTO(ProdutoDTO objDTO) {
		return new Produto(objDTO.getId(), objDTO.getNome(), objDTO.getValidadeDias());
	}
	
	private void updateData(Produto newObj, Produto obj) {
		newObj.setNome(obj.getNome());
		newObj.setValidadeDias(obj.getValidadeDias());
	}

}
