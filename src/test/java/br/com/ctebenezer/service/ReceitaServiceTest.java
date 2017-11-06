package br.com.ctebenezer.service;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ctebenezer.domain.Consulta;
import br.com.ctebenezer.domain.Receita;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ReceitaServiceTest {
	@Autowired
	ReceitaService receitaService;
	
	@Test
	public void teste_salvar_receita() {
		Consulta c = new Consulta();
		Receita r = new Receita(); 
		r.setDescricaoDaReceita("teste");
		r.setConsulta(c);
		assertThat(receitaService.salvarReceita(r)).isTrue();
	}
	@Test
	public void teste_salvar_receita_sem_descrição() {
		Consulta c = new Consulta();
		Receita r = new Receita(); 
		r.setConsulta(c);
		assertThat(receitaService.salvarReceita(r)).isFalse();
	}
	@Test
	public void teste_salvar_receita_sem_consulta() {
		Consulta c = new Consulta();
		Receita r = new Receita(); 
		r.setDescricaoDaReceita("teste");
		assertThat(receitaService.salvarReceita(r)).isFalse();
	}
	@Test
	public void teste_buscar_todas() {
		assertThat(receitaService.buscarTodas()).isNotNull();
	}
	@Test
	public void teste_buscar_por_id_valido() {
		assertThat(receitaService.buscarPorId(1L)).isNotNull();
	}
	@Test
	public void teste_buscar_por_id_invalido() {
		assertThat(receitaService.buscarPorId(-1L)).isNull();
	}
	@Test
	public void teste_buscar_por_id_null() {
		assertThat(receitaService.buscarPorId(null)).isNull();
	}
	@Test
	public void teste_excluir_por_id_valido() {
		assertThat(receitaService.excluir(1L)).isTrue();
	}
	@Test
	public void teste_excluir_por_id_invalido() {
		assertThat(receitaService.excluir(-1L)).isFalse();
	}
	@Test
	public void teste_excluir_por_id_null() {
		assertThat(receitaService.excluir(null)).isFalse();
	}
	
	

}
