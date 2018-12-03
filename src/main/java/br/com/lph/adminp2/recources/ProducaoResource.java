package br.com.lph.adminp2.recources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lph.adminp2.domain.Producao;
import br.com.lph.adminp2.dto.ProducaoNewDTO;
import br.com.lph.adminp2.service.ProducaoService;

@RestController // Marca a classe como controladora rest
@RequestMapping(value="/producao") // EndPoint Rest
public class ProducaoResource {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private ProducaoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Producao> find(@PathVariable Integer id) {
		Producao obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ProducaoNewDTO objDTO) { // precisar ser corrigido
		Producao obj = new Producao();
		obj = service.insert(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Producao obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}


}
	