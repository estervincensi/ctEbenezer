package br.com.ctebenezer.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ctebenezer.domain.Endereco;
import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.domain.enumerables.EstadoCivil;
import br.com.ctebenezer.repository.ResidenteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ResidenteServiceTest {
	//Esta classe tem os testes dos métodos da classe Service
	@Autowired
	ResidenteService residenteService;
	
	@Autowired
	ResidenteRepository residenteRepository;


	@Test
	public void testa_se_retorna_todos_os_estadosCivis(){
		assertThat(residenteService.buscarTodosEstadosCivis()).isNotEmpty();
		assertThat(residenteService.buscarTodosEstadosCivis().size()).isEqualTo(5);
	}

	@Test
	public void testa_se_salva_residente_com_todos_os_campos(){

		Residente residente = new Residente("teste", "teste", "teste", "teste", "teste", DateTime.now().toDate(), EstadoCivil.CASADO, new Endereco());
		assertThat(residenteService.salvar(residente)).isNotNull();
		residenteRepository.delete(residente);
	}
	@Test
	public void testa_se_nao_salva_residente_null(){
		Residente residente = null;
		assertThat(residenteService.salvar(residente)).isNull();
	}
	@Test
	public void testa_se_nao_salva_residente_com_campo_null(){
		Residente residente = new Residente(null, null, null, "teste", "teste", null, null, null);
		assertThat(residenteService.salvar(residente)).isNull();
	}

	@Test
	public void testa_buscar_todos_residentes(){
		assertThat(residenteService.listarTodos()).isNotEmpty();
	}

	@Test
	public void busca_com_id_valido(){
		assertThat(residenteService.buscar(2L)).isNotNull();
	}

	@Test
	public void busca_com_id_invalido(){
		assertThat(residenteService.buscar(-1L)).isNull();
	}

	@Test
	public void busca_com_id_null(){
		assertThat(residenteService.buscar(null)).isNull();
	}
	@Test
	public void testa_reingressar_residente_id_invalido(){
		assertThat(residenteService.reingressar(-1L)).isNull();
	}
	@Test
	public void testa_reingressar_residente_id_valido(){
		assertThat(residenteService.reingressar(1L)).isNotNull();
	}
	@Test
	public void testa_reingressar_residente_id_null(){
		assertThat(residenteService.reingressar(null)).isNull();
	}
	@Test
	public void testa_desligar_residente_id_invalido(){
		assertThat(residenteService.desligar(-1L)).isNull();
	}
	@Test
	public void testa_desligar_residente_id_valido(){
		assertThat(residenteService.desligar(2L)).isNotNull();
	}
	@Test
	public void testa_desligar_residente_id_null(){
		assertThat(residenteService.desligar(null)).isNull();
	}
	@Test
	public void testa_buscar_ativos() {
		assertThat(residenteService.buscarAtivos()).isNotEmpty();
	}
	@Test
	public void testa_buscar_ativos_com_pia() {
		assertThat(residenteService.buscarAtivosComPia()).isNotEmpty();
	}
	@Test
	public void testa_buscar_por_rg_valido() {
		assertThat(residenteService.buscarPorRg("123456789")).isNotNull();
	}
	@Test
	public void testa_buscar_por_rg_invalido() {
		assertThat(residenteService.buscarPorRg("000000000")).isNull();
	}

}
