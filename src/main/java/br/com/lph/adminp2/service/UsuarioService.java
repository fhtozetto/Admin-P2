package br.com.lph.adminp2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.Usuario;
import br.com.lph.adminp2.dto.UsuarioDTO;
import br.com.lph.adminp2.repositories.UsuarioRepository;
import br.com.lph.adminp2.services.exceptions.DataIntegrityException;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;;

@Service
public class UsuarioService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
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
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Usuario fromDTO(UsuarioDTO objDTO) {
		return new Usuario(objDTO.getId(), null, objDTO.getNomeUsuario(), pe.encode(objDTO.getSenha()), null );
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNomeUsuario(obj.getNomeUsuario());
	}
}
