package br.com.lph.adminp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lph.adminp2.domain.Producao;

@Repository
public interface ProducaoRepository extends JpaRepository <Producao, Integer> {

}
