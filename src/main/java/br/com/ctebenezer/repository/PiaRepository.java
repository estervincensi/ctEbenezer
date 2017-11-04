package br.com.ctebenezer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ctebenezer.domain.Pia;
import br.com.ctebenezer.domain.Relatorio;

public interface PiaRepository extends JpaRepository<Pia, Long>{
	@Query(value="select p.* from CTEBENEZER.PIA p where p.ativo = true and p.residente_id = ?1", nativeQuery=true)
	Pia findByResidenteId(Long id);
	
	@Query(value="SELECT year(p.data_saida) as ano, count(p.desistiu=true), count (p.desistiu=false) FROM CTEBENEZER.PIA p where p.ativo = false group by year(p.data_saida)", nativeQuery=true)
	List<Object> baixasPorAno();
	
	@Query(value="SELECT year(p.data_entrada) as ano, count (p.*) as numero FROM CTEBENEZER.PIA p group by year(p.data_entrada)", nativeQuery=true)
	List<Object> atendimentosPorAno();
	
	@Query(value="SELECT d.dependencias, count(d.*) FROM CTEBENEZER.PIA_DEPENDENCIAS d  group by d.dependencias", nativeQuery=true)
	List<Object> dependencias();

}
