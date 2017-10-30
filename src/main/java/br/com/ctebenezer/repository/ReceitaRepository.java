package br.com.ctebenezer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita,Long>{

}
