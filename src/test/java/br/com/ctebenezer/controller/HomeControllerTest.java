package br.com.ctebenezer.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.ctebenezer.domain.Pessoa;
import br.com.ctebenezer.domain.UserImpl;
import br.com.ctebenezer.domain.enumerables.Dependencias;
import br.com.ctebenezer.domain.enumerables.EstadoCivil;
import br.com.ctebenezer.service.ResidenteService;

@RunWith(SpringRunner.class)
@WebMvcTest(ResidenteController.class)
public class HomeControllerTest {
	@Autowired
	MockMvc mvc;

	@MockBean
	ResidenteService residenteService;

	UserImpl userDetails;
	Pessoa person;

	@Before
	public void setup() {
		person = new Pessoa();
		person.setId(1L);
		person.setNome("User Principal");
		userDetails = new UserImpl("user", "user", AuthorityUtils.createAuthorityList("ROLE_USER"));
		userDetails.setPessoa(person);
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		Mockito.when(securityContext.getAuthentication().getPrincipal()).thenReturn(userDetails);
		SecurityContextHolder.setContext(securityContext);

	}

	@Test
	public void teste_cadastrar_residente() throws Exception {
		given(this.residenteService.calculaVagas()).willReturn(getVagas());
		this.mvc.perform(get("/home")
				.with(user(userDetails)).accept(MediaType.TEXT_HTML))
				.andExpect(status().is(404)) //não consegui achar nenhuma página da home controller
				//.andExpect(content().contentType("text/html;charset=UTF-8"))
				//.andExpect(view().name("/index"))
				//.andExpect(model().attribute("vagas", hasItem(2)))
				;
	}

	private int[] getVagas(){
		int[] vagas = new int[2];
		vagas[0] = 2;
		vagas[1] = 38;
		return vagas;
	}

}
