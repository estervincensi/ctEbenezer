package br.com.ctebenezer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
