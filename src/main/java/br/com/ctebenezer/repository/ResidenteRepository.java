package br.com.ctebenezer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Residente;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Long> {

}
