package br.com.ctebenezer.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ctebenezer.domain.Account;
import br.com.ctebenezer.domain.Role;
import br.com.ctebenezer.domain.UserImpl;
import br.com.ctebenezer.repository.AccountRepository;
import br.com.ctebenezer.repository.PessoaRepository;
import br.com.ctebenezer.repository.RoleRepository;

@Service
public class AccountUserDetailsService implements UserDetailsService {

	private final AccountRepository accountRepository;
	private final RoleRepository roleRepository;
	private final PessoaRepository pessoaRepository;

	@Autowired
	public AccountUserDetailsService(AccountRepository accountRepository, RoleRepository roleRepository, PessoaRepository pessoaRepository) {
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.accountRepository.findByUsername(username)
				.map(account -> new UserImpl(account.getUsername(), account.getPassword(), account.isActive(),
						account.isActive(), account.isActive(), account.isActive(),
						account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole()))
								.collect(Collectors.toList()),
						account.getPessoa()))
				.orElseThrow(() -> new UsernameNotFoundException("couldn't find " + username + "!"));
	}
	
	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}
	
	public List<Account> buscarTodosMedicos() {
		return accountRepository.buscarTodosMedicos();
	}
	
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public boolean salvarUsuario(Account account) {
		if(accountRepository.bucarPorUsername(account.getUsername())!=null) {
			return false;
		}
		String newPassword = this.passwordEncoder().encode(account.getPassword());
		account.setPassword(newPassword);
		pessoaRepository.save(account.getPessoa());
		account.setActive(true);
		accountRepository.save(account);
		return true;
	}
	public List<Account> listarUsuarios(){
		return accountRepository.findAll();
	}
	public boolean desativarUsuario(Long id) {
		Account account = accountRepository.findOne(id);
		if(account!=null) {
			account.setActive(false);
			accountRepository.save(account);
			return true;
		}
		return false;
		
	}
	public boolean ativarUsuario(Long id) {
		Account account = accountRepository.findOne(id);
		if(account!=null) {
			account.setActive(true);
			accountRepository.save(account);
			return true;
		}
		return false;
		
	}
	

}