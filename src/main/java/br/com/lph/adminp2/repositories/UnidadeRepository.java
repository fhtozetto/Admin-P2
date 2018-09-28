package br.com.lph.adminp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lph.adminp2.domain.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository <Unidade, Integer> {

}
