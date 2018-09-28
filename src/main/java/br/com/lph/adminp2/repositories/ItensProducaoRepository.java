package br.com.lph.adminp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lph.adminp2.domain.ItensProducao;

@Repository
public interface ItensProducaoRepository extends JpaRepository <ItensProducao, Integer> {

}
