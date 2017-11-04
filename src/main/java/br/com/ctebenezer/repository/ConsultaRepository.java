package br.com.ctebenezer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Long>{
	public Consulta findByDataAndHoraAndAtivo(Date data, String hora, boolean ativo);
	
	@Query(value="select c.* from consulta  c where c.data > getDate() and c.ativo=true", nativeQuery=true)
	public List<Consulta> buscarRecentes();
}
