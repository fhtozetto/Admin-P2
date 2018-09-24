package br.com.lph.adminp2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.lph.adminp2.domain.Categoria;
import br.com.lph.adminp2.domain.CodigoBarras;
import br.com.lph.adminp2.domain.Produto;
import br.com.lph.adminp2.repositories.CategoriaRepository;
import br.com.lph.adminp2.repositories.CodigoBarrasRepository;
import br.com.lph.adminp2.repositories.ProdutoRepository;

@SpringBootApplication
public class AdminP2Application implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CodigoBarrasRepository codigoBarrasRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdminP2Application.class, args);
	}
		
		@Override
		public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Saladas");
		Categoria cat2 = new Categoria(null, "Marmitex");
		Categoria cat3 = new Categoria(null, "Acompanhametos");
		
		Produto p1 = new Produto(null, "Salada de Folhas", 3);
		Produto p2 = new Produto(null, "Marmitex media", 1);
		Produto p3 = new Produto(null, "Frango Assado", 1);		
		
		CodigoBarras cb1 = new CodigoBarras(null, "1234", 1.0, p1);
		CodigoBarras cb2 = new CodigoBarras(null, "2345", 1.0, p1);
		CodigoBarras cb3 = new CodigoBarras(null, "3456", 1.0, p2);
		CodigoBarras cb4 = new CodigoBarras(null, "4567", 1.0, p3);
		CodigoBarras cb5 = new CodigoBarras(null, "5678", 1.0, p3);
		CodigoBarras cb6 = new CodigoBarras(null, "6789", 1.0, p3);
		
		cat1.getProdutos().addAll(Arrays.asList(p1));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p1, p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat3));
		
		p1.getCodigosBarras().addAll(Arrays.asList(cb1, cb2));
		p2.getCodigosBarras().addAll(Arrays.asList(cb3));
		p3.getCodigosBarras().addAll(Arrays.asList(cb4, cb5, cb6));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		codigoBarrasRepository.saveAll(Arrays.asList(cb1, cb2, cb3, cb4, cb5, cb6));
		
		
	}
}
