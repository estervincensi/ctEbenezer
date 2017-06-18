package br.com.ctebenezer.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class AccountUserDetailsServiceTest {

	@Autowired
	private AccountUserDetailsService accountUserDetailsService;

	@Test
	public void testa_load_user_by_username(){
		assertThat(accountUserDetailsService.loadUserByUsername("user")).isNotNull();
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testa_null(){
		accountUserDetailsService.loadUserByUsername(null);
	}

}
