package br.com.ctebenezer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ctebenezer.domain.Pia;
import br.com.ctebenezer.domain.Relatorio;

public interface PiaRepository extends JpaRepository<Pia, Long>{
	@Query(value="select p.* from pia p where p.ativo = true and p.residente_id = ?1", nativeQuery=true)
	Pia findByResidenteId(Long id);
	
	@Query(value="SELECT year(p.data_saida) as ano, count(p.desistiu=1), count(p.desistiu=0) FROM pia p where p.ativo = 0 group by year(p.data_saida)", nativeQuery=true)
	List<Object> baixasPorAno();
	
	@Query(value="SELECT year(p.data_entrada) as ano, count(*) as numero FROM pia p group by year(p.data_entrada)", nativeQuery=true)
	List<Object> atendimentosPorAno();
	
	@Query(value="SELECT d.dependencias, count(*) FROM pia_dependencias d  group by d.dependencias", nativeQuery=true)
	List<Object> dependencias();

}
