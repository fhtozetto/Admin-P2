package br.com.lph.adminp2.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lph.adminp2.domain.Categoria;
import br.com.lph.adminp2.domain.CodigoBarras;
import br.com.lph.adminp2.domain.Produto;
import br.com.lph.adminp2.domain.ProdutoDetalhes;
import br.com.lph.adminp2.domain.Unidade;
import br.com.lph.adminp2.dto.ProdutoDTO;
import br.com.lph.adminp2.dto.ProdutoNewDTO;
import br.com.lph.adminp2.repositories.CategoriaRepository;
import br.com.lph.adminp2.repositories.CodigoBarrasRepository;
import br.com.lph.adminp2.repositories.ProdutoDetalhesRepository;
import br.com.lph.adminp2.repositories.ProdutoRepository;
import br.com.lph.adminp2.repositories.UnidadeRepository;
import br.com.lph.adminp2.services.exceptions.DataIntegrityException;
import br.com.lph.adminp2.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired // cria o objeto automaticamente por injeção de dependência ou inversão de controle.
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoDetalhesRepository produtoDetalhesRepository;
	
	@Autowired
	private CodigoBarrasRepository codigoBarrasRepository;
	
	@Autowired
	private UnidadeRepository unidadeRepository;
	
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
	
	public Produto fromDTO(ProdutoNewDTO objDTO) {
		Produto prod = new Produto(null, objDTO.getNome(), objDTO.getValidadeDias());
		
		Categoria cat = categoriaRepository.getOne(objDTO.getCategoria());
		prod.getCategorias().add(cat);
		
		CodigoBarras cbar = new CodigoBarras(null, objDTO.getCodBarras(), 1.0, prod);
		prod.getCodigosBarras().add(cbar);

		Set<ProdutoDetalhes> lista = new HashSet<>();
		for (Unidade un : unidadeRepository.findAll()) {
			if (objDTO.getUnidade() == un.getId()) { 
				lista.add(new ProdutoDetalhes(prod, un, objDTO.getPrecoVenda(), objDTO.getPrecoCusto(), objDTO.getEstoque()));
			} else {
				lista.add(new ProdutoDetalhes(prod, un, 0.0, 0.0, 0.0));
			}
		}
		prod.getDetalhesPorUnidades().addAll(lista);
		
		return prod;
	}
	
	private void updateData(Produto newObj, Produto obj) {
		newObj.setNome(obj.getNome());
		newObj.setValidadeDias(obj.getValidadeDias());
	}
	
	@Transactional
	public Produto insert(Produto obj) {
		obj.setId(null);
		obj = repo.save(obj);
		categoriaRepository.saveAll(obj.getCategorias());
		codigoBarrasRepository.saveAll(obj.getCodigosBarras());
		produtoDetalhesRepository.saveAll(obj.getDetalhesPorUnidades());
		
		return obj;
	}


}
