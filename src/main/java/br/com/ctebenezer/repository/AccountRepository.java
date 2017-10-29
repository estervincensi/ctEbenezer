package br.com.ctebenezer.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ctebenezer.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
    
    @Query(value="select a.* from CTEBENEZER.ACCOUNT a where a.username = :username", nativeQuery=true)
    Account bucarPorUsername(@Param("username") String username);
    
    @Query(value="SELECT a.* FROM CTEBENEZER.ACCOUNT a join CTEBENEZER.ACCOUNT_ROLES  ar on a.id = ar.accounts_id join CTEBENEZER.ROLE  r on  ar.roles_id = r.id where r.role = 'ROLE_MEDICO'", nativeQuery=true)
    List<Account> buscarTodosMedicos();
}