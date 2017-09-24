package br.com.ctebenezer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ctebenezer.domain.Pia;

public interface PiaRepository extends JpaRepository<Pia, Long>{
	Pia findByResidenteId(Long id);
}
