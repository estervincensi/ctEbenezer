package br.com.ctebenezer.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ctebenezer.domain.Account;
import br.com.ctebenezer.domain.Pessoa;
import br.com.ctebenezer.domain.Role;
import br.com.ctebenezer.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class AccountUserDetailsServiceTest {

	@Autowired
	private AccountUserDetailsService accountUserDetailsService;
	
	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void testa_load_user_by_username(){
		assertThat(accountUserDetailsService.loadUserByUsername("user")).isNotNull();
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testa_null(){
		accountUserDetailsService.loadUserByUsername(null);
	}
	
	@Test
	public void testa_getAllRoles(){
		assertThat(accountUserDetailsService.getAllRoles()).size().isEqualTo(4);
	}
	
	@Test
	public void testa_buscarTodosMedicos(){
		assertThat(accountUserDetailsService.buscarTodosMedicos()).isNotNull();
	}
	
	@Test
	public void testa_salvar_usuario_ja_existente(){
		Account a = new Account();
		a.setActive(true);
		a.setPassword("teste");
		a.setUsername("user");
		Role role = new Role();
		role.setRole("ROLE_ADMIN");
		Set<Role> r = new LinkedHashSet<>();
		r.add(role);
		a.setRoles(r);
		a.setPessoa(new Pessoa());
		assertThat(accountUserDetailsService.salvarUsuario(a)).isFalse();
	}
	
	@Test
	public void testa_salvar_usuario() {
		Account a = new Account();
		a.setActive(true);
		a.setPassword("teste");
		a.setUsername("teste");
		Role role = new Role();
		role.setRole("ROLE_ADMIN");
		Set<Role> r = new LinkedHashSet<>();
		r.add(role);
		a.setRoles(r);
		a.setPessoa(new Pessoa());
		assertThat(accountUserDetailsService.salvarUsuario(a)).isTrue();
		accountRepository.delete(a);
	}
	
	@Test
	public void test_desativar_usuario_ineexistente() {
		assertThat(accountUserDetailsService.desativarUsuario(123456789L)).isFalse();
	}
	
	@Test
	public void testa_desativar_usuario_existente() {
		assertThat(accountUserDetailsService.desativarUsuario(1L)).isTrue();
	}
	
	@Test
	public void testa_ativar_usuario_existente() {
		assertThat(accountUserDetailsService.ativarUsuario(1L)).isTrue();
	}
	@Test
	public void testa_ativar_usuario_inexistente() {
		assertThat(accountUserDetailsService.ativarUsuario(123456789L)).isFalse();
	}
	
	@Test
	public void testa_listar_usuarios() {
		assertThat(accountUserDetailsService.listarUsuarios()).isNotEmpty();
	}

}
