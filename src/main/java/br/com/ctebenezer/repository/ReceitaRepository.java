package br.com.ctebenezer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita,Long>{
	
	@Query(value="Select r.* from receita r join consulta c on r.consulta_id = c.id where r.ativo=true and c.data >= DATEADD(MONTH, -3, GETDATE())",nativeQuery=true)
	public List<Receita> buscarTodos();

}
