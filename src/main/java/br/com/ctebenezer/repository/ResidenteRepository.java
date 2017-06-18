package br.com.ctebenezer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Residente;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Long> {
	@Query(value="select r.* from CTEBENEZER.RESIDENTE r join CTEBENEZER.ENDERECO e on e.id = r.endereco_id "
			+ "join CTEBENEZER.RESIDENTE_DEPENDENCIAS rd on rd.residente_id = r.id order by r.nome", nativeQuery=true)
	public List<Residente> findAll();
}
