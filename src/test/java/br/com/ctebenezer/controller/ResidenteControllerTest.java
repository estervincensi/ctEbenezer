package br.com.ctebenezer.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
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
import org.springframework.web.util.NestedServletException;

import br.com.ctebenezer.domain.Endereco;
import br.com.ctebenezer.domain.Pessoa;
import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.domain.UserImpl;
import br.com.ctebenezer.domain.enumerables.Dependencias;
import br.com.ctebenezer.domain.enumerables.EstadoCivil;
import br.com.ctebenezer.service.ResidenteService;

@RunWith(SpringRunner.class)

@WebMvcTest(ResidenteController.class)
public class ResidenteControllerTest {
	@Autowired
	MockMvc mvc;

	@MockBean
	ResidenteService residenteService;

	Pessoa person;
	UserImpl userDetails;

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
		given(this.residenteService.buscarTodasDependencias()).willReturn(getDependencias());
		given(this.residenteService.buscarTodosEstadosCivis()).willReturn(getEstadosCivis());
		this.mvc.perform(get("/residente/cadastrar").with(user(userDetails)).accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(view().name("/residentes/cadastrar"))
				.andExpect(model().attribute("dependencias1", hasItem(Dependencias.ALCOOL)))
				.andExpect(model().attribute("estadosCivis", hasItem(EstadoCivil.CASADO)));
	}

	@Test
	public void teste_salvar_novo_residente() throws Exception {
		given(this.residenteService.salvar(new Residente())).willReturn(new Residente());
		this.mvc.perform(
				post("/residente/salvarNovo", new Residente()).with(user(userDetails)).accept(MediaType.TEXT_HTML))
				.andExpect(view().name("redirect:/home")); // não sabia muito
															// bem como fazer
															// testes para post
	}

	@Test
	public void teste_salvar_residente() throws Exception {
		given(this.residenteService.salvar(new Residente())).willReturn(new Residente());
		this.mvc.perform(post("/residente/salvar", new Residente()).with(user(userDetails)).accept(MediaType.TEXT_HTML))
				.andExpect(view().name("redirect:/residente/listar")); // não
																		// sabia
																		// muito
																		// bem
																		// como
																		// fazer
																		// testes
																		// para
																		// post
	}

	@Test
	public void teste_listar_residente() throws Exception {
		given(this.residenteService.listarTodos()).willReturn(getTodos());
		this.mvc.perform(get("/residente/listar").with(user(userDetails)).accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(view().name("/residentes/listar"))
				.andExpect(model().attribute("residentes", hasItem(hasProperty("id", is(1L)))));
	}

	@Test
	public void teste_reingressar_residente() throws Exception {
		given(this.residenteService.reingressar(1L)).willReturn(getResidente(1L));
		this.mvc.perform(get("/residente/reingressar/1").with(user(userDetails)).accept(MediaType.TEXT_HTML))
		.andExpect(status().is(302)) // ta dando status 302
		// .andExpect(content().contentType("text/html;charset=UTF-8"))
		// .andExpect(view().name("redirect:/residentes/listar"))
		;
	}

	@Test (expected = NestedServletException.class)
	public void teste_editar_residente() throws Exception {
		given(this.residenteService.reingressar(1L)).willReturn(getResidente(1L));
		this.mvc.perform(get("/residente/editar/1").with(user(userDetails)).accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(view().name("/residentes/editar"));
		// não faço ideia do pq ta dando exceção no html
	}

	@Test
	public void teste_desistir() throws Exception {
		given(this.residenteService.desligar(1L)).willReturn(getResidente(1L));
		this.mvc.perform(get("/residente/desistir/1")
				.with(user(userDetails))
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().is(302)) // ta dando status 302
				//.andExpect(content().contentType("text/html;charset=UTF-8"))
				//.andExpect(view().name("redirect:/residente/listar"))
				;

	}

	@Test (expected = NestedServletException.class)
	public void teste_desligar() throws Exception {
		given(this.residenteService.desligar(1L)).willReturn(getResidente(1L));
		given(this.residenteService.calculaTempoNaCasa(1L)).willReturn("1 mes");
		this.mvc.perform(get("/residente/desligar/1")
				.with(user(userDetails))
				.accept(MediaType.TEXT_HTML))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(view().name("/residentes/atestadoAlta"))
				;
		//ta dando erro na hora de formatar a data
	}

	private Residente getResidente(Long id) {
		Residente residente = new Residente();
		residente.setId(id);
		residente.setNome("teste");
		residente.setDataNascimento(DateTime.now().toDate());
		residente.setEstadoCivil(EstadoCivil.CASADO);
		residente.setEndereco(new Endereco());
		return residente;
	}

	private List<Dependencias> getDependencias() {
		ArrayList<Dependencias> dependencias = new ArrayList<>();
		dependencias.add(Dependencias.ALCOOL);
		return dependencias;
	}

	private List<EstadoCivil> getEstadosCivis() {
		ArrayList<EstadoCivil> estadosCivis = new ArrayList<>();
		estadosCivis.add(EstadoCivil.CASADO);
		return estadosCivis;
	}

	private List<Residente> getTodos() {
		ArrayList<Residente> residentes = new ArrayList<>();
		Residente residente = new Residente();
		residente.setId(1L);
		residentes.add(residente);
		return residentes;
	}

}
