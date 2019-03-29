package br.com.lph.adminp2.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.Categoria;
import br.com.lph.adminp2.domain.CodigoBarras;
import br.com.lph.adminp2.domain.Descarte;
import br.com.lph.adminp2.domain.ItensDescarte;
import br.com.lph.adminp2.domain.ItensProducao;
import br.com.lph.adminp2.domain.Producao;
import br.com.lph.adminp2.domain.Produto;
import br.com.lph.adminp2.domain.ProdutoDetalhes;
import br.com.lph.adminp2.domain.Unidade;
import br.com.lph.adminp2.domain.Usuario;
import br.com.lph.adminp2.domain.enums.Perfil;
import br.com.lph.adminp2.repositories.CategoriaRepository;
import br.com.lph.adminp2.repositories.CodigoBarrasRepository;
import br.com.lph.adminp2.repositories.DescarteRepository;
import br.com.lph.adminp2.repositories.ItensDescarteRepository;
import br.com.lph.adminp2.repositories.ItensProducaoRepository;
import br.com.lph.adminp2.repositories.ProducaoRepository;
import br.com.lph.adminp2.repositories.ProdutoDetalhesRepository;
import br.com.lph.adminp2.repositories.ProdutoRepository;
import br.com.lph.adminp2.repositories.UnidadeRepository;
import br.com.lph.adminp2.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CodigoBarrasRepository codigoBarrasRepository;
	
	@Autowired
	private UnidadeRepository unidadeRepository;
	
	@Autowired
	private ProducaoRepository producaoRepository;
	
	@Autowired
	private ItensProducaoRepository itensProducaoRepository;
	
	@Autowired
	private DescarteRepository descarteRepository;
	
	@Autowired
	private ItensDescarteRepository itensDescarteRepository;
	
	@Autowired
	private ProdutoDetalhesRepository produtoDetalhesRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	
	public void instantiateTestDataBase() throws ParseException {

		Unidade un1 = new Unidade(null, "Loja 28 - Cajuru", "15 3225-2443");
		Unidade un2 = new Unidade(null, "Loja 32 - Eden", "15 3325-6339");
		
		Usuario usu1 = new Usuario(null, "Fernando Henrique Tozetto", "fhtozetto", pe.encode("123"), un1, "fhtozetto@gmail.com");
		usu1.addPerfil(Perfil.ADMIN);
		Usuario usu2 = new Usuario(null, "Karina Mayumi Une", "kmune", pe.encode("123"), un2, "fernando@superune.com.br");
		Usuario usu3 = new Usuario(null, "Lucas Eiji Une Tozetto", "leutozetto", pe.encode("123"), un1, "fhtozetto@hotmail.com");
	
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
		
		unidadeRepository.saveAll(Arrays.asList(un1, un2));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		codigoBarrasRepository.saveAll(Arrays.asList(cb1, cb2, cb3, cb4, cb5, cb6));
		usuarioRepository.saveAll(Arrays.asList(usu1, usu2, usu3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Producao producao1 = new Producao(null, sdf.parse("27/09/2017 12:32"), un1.getId(), un2);
		Producao producao2 = new Producao(null, sdf.parse("30/09/2017 10:37"), un2.getId(), un2);
		
		ItensProducao ip1 = new ItensProducao(null, 5.0, 2.35, sdf.parse("10/10/2017 08:00"), producao1, p1);
		ItensProducao ip2 = new ItensProducao(null, 10.0, 5.35, sdf.parse("11/10/2017 08:00"), producao1, p2);
		ItensProducao ip3 = new ItensProducao(null, 7.0, 1.35, sdf.parse("12/10/2017 08:00"), producao1, p3);
		ItensProducao ip4 = new ItensProducao(null, 3.0, 2.35, sdf.parse("10/10/2017 08:00"), producao1, p1);
		ItensProducao ip5 = new ItensProducao(null, 10.0, 5.35, sdf.parse("11/10/2017 13:00"), producao2, p2);
		ItensProducao ip6 = new ItensProducao(null, 7.0, 1.35, sdf.parse("12/10/2017 13:01"), producao2, p3);
		ItensProducao ip7 = new ItensProducao(null, 3.0, 2.35, sdf.parse("10/10/2017 13:15"), producao2, p3);
		
		producao1.getItensProducaos().addAll(Arrays.asList(ip1, ip2, ip3, ip4));
		producao2.getItensProducaos().addAll(Arrays.asList(ip5, ip6, ip7));
		
		producaoRepository.saveAll(Arrays.asList(producao1, producao2));
		itensProducaoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4, ip5, ip6, ip7));
		
		Descarte descarte1 = new Descarte(null, sdf.parse("10/10/2017 13:15"), un1);
		Descarte descarte2 = new Descarte(null, sdf.parse("10/10/2017 13:15"), un2);
		
		ItensDescarte id1 = new ItensDescarte(null, 2.00, 2.35, descarte1, p1);
		ItensDescarte id2 = new ItensDescarte(null, 2.00, 2.35, descarte2, p1);
		
		descarte1.getItensDescartes().addAll(Arrays.asList(id1));
		descarte2.getItensDescartes().addAll(Arrays.asList(id2));
		
		descarteRepository.saveAll(Arrays.asList(descarte1, descarte2));
		itensDescarteRepository.saveAll(Arrays.asList(id1, id2));
		
		ProdutoDetalhes pd1 = new ProdutoDetalhes(p1, un1, 5.00, 3.00, 10.00);
		ProdutoDetalhes pd2 = new ProdutoDetalhes(p1, un2, 5.50, 3.00, 6.00);
		ProdutoDetalhes pd3 = new ProdutoDetalhes(p2, un1, 10.00, 7.50, 30.00);
		ProdutoDetalhes pd4 = new ProdutoDetalhes(p2, un2, 10.50, 6.50, 50.00);
		ProdutoDetalhes pd5 = new ProdutoDetalhes(p3, un1, 2.00, 1.00, 120.00);
		ProdutoDetalhes pd6 = new ProdutoDetalhes(p3, un2, 2.50, 1.00, 150.00);
		
		p1.getDetalhesPorUnidades().addAll(Arrays.asList(pd1, pd2));
		p2.getDetalhesPorUnidades().addAll(Arrays.asList(pd3, pd4));
		p3.getDetalhesPorUnidades().addAll(Arrays.asList(pd5, pd6));
		
		un1.getProdutosPorUnidade().addAll(Arrays.asList(pd1, pd3, pd5));
		un2.getProdutosPorUnidade().addAll(Arrays.asList(pd2, pd4, pd6));
		
		produtoDetalhesRepository.saveAll(Arrays.asList(pd1, pd2, pd3, pd4, pd5, pd6));
		
	}
}
