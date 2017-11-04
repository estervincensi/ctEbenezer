package br.com.ctebenezer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Residente;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Long> {
	
	public Residente findByRg(String rg);
	
	@Query(value="select r.* from residente r join endereco e on e.id = r.endereco_id "
			+"order by r.nome", nativeQuery=true)
	public List<Residente> findAll();
	
	@Query(value="select r.* from residente r where r.ativo = true", nativeQuery=true)
	public List<Residente> findAtivos();
	
	@Query(value="select r.* from residente r where r.pia_ativo = true and r.ativo = true", nativeQuery=true)
	public List<Residente> findWithPIA();
	
	
}
