package br.com.ctebenezer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ctebenezer.domain.Pia;
import br.com.ctebenezer.domain.Relatorio;

public interface PiaRepository extends JpaRepository<Pia, Long>{
	@Query(value="select p.* from CTEBENEZER.PIA p where p.ativo = true and p.residente_id = ?1", nativeQuery=true)
	Pia findByResidenteId(Long id);
	
	@Query(value="SELECT year(p.data_saida) as ano, p.desistiu, count (p.*) as numero FROM CTEBENEZER.PIA p where p.ativo = false group by p.desistiu, year(p.data_saida)", nativeQuery=true)
	List<Object> baixasPorAno();

}
