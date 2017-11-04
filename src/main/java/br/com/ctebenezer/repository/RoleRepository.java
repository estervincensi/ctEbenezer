package br.com.ctebenezer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	@Query(value="select * from role", nativeQuery = true)
	public List<Role> findAll();
}
