package br.com.ctebenezer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ctebenezer.domain.Pia;

public interface PiaRepository extends JpaRepository<Pia, Long>{
	@Query(value="select p.* from CTEBENEZER.PIA p where p.ativo = true and p.residente_id = ?1", nativeQuery=true)
	Pia findByResidenteId(Long id);
}
