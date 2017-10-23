package br.com.ctebenezer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long>{
	
}
