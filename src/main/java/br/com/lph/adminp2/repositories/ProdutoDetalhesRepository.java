package br.com.lph.adminp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lph.adminp2.domain.ProdutoDetalhes;

/*
 * A Classe Repository é responsavel pelo acesso aos dados (DAO)
 * ações esperadas: Pesquisa, Inclusão, Alteração e Exclusão
 */

@Repository
public interface ProdutoDetalhesRepository extends JpaRepository <ProdutoDetalhes, Integer> {

}
