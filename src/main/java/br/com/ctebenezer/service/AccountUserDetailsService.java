package br.com.ctebenezer.service;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import br.com.ctebenezer.domain.File;
import br.com.ctebenezer.domain.Role;
import br.com.ctebenezer.domain.UserImpl;
import br.com.ctebenezer.repository.AccountRepository;
import br.com.ctebenezer.repository.FileRepository;
import br.com.ctebenezer.repository.PessoaRepository;
import br.com.ctebenezer.repository.RoleRepository;

@Service
public class AccountUserDetailsService implements UserDetailsService {

	private final AccountRepository accountRepository;
	private final RoleRepository roleRepository;
	private final PessoaRepository pessoaRepository;
	private final FileRepository fileRepository;

	@Autowired
	public AccountUserDetailsService(AccountRepository accountRepository, RoleRepository roleRepository, PessoaRepository pessoaRepository,FileRepository fileRepository) {
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
		this.pessoaRepository = pessoaRepository;
		this.fileRepository = fileRepository;
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
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public boolean salvarUsuario(Account account) {
		if(accountRepository.bucarPorUsername(account.getUsername())!=null) {
			return false;
		}
		String newPassword = this.passwordEncoder().encode(account.getPassword());
		account.setPassword(newPassword);
		File file = account.getPessoa().getPicture();
		/*try {
			file.setContent(imageToByte(file.getDescription()));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		file.setDescription(null);
		fileRepository.save(file);
		account.getPessoa().setPicture(file);
		pessoaRepository.save(account.getPessoa());
		account.setActive(true);
		accountRepository.save(account);
		return true;
	}
	
	public byte[] imageToByte(String image) throws IOException {
	    InputStream is = null;
	    byte[] buffer = null;
	    is = new FileInputStream(image);
	    buffer = new byte[is.available()];
	    is.read(buffer);
	    is.close();
	    return buffer;
	}
}