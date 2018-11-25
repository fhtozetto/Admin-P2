package br.com.lph.adminp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lph.adminp2.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT prod FROM Produto prod INNER jOIN prod.codigosBarras cb WHERE cb.codBarras = :codBarras")
	Produto search(@Param("codBarras") String codigoDeBarras);
}
