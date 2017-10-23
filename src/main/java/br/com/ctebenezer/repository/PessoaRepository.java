package br.com.ctebenezer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Pessoa;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Long>{

}
