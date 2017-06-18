package br.com.ctebenezer.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ResidenteServiceTest {
	//Esta classe tem os testes dos métodos da classe Service
	@Autowired
	ResidenteService residenteService;
	@Test
	public void testa_se_retorna_todas_as_dependencias(){
		assertThat(residenteService.buscarTodasDependencias()).isNotEmpty();
		assertThat(residenteService.buscarTodasDependencias().size()).isEqualTo(7);
	}

	@Test
	public void testa_se_retorna_todos_os_estadosCivis(){
		assertThat(residenteService.buscarTodosEstadosCivis()).isNotEmpty();
		assertThat(residenteService.buscarTodosEstadosCivis().size()).isEqualTo(5);
	}

	@Test
	public void testa_se_salva_residente_com_todos_os_campos(){

		Residente residente = new Residente("teste", "teste", "teste", "teste", "teste", DateTime.now().toDate(), EstadoCivil.CASADO, new Endereco(), residenteService.buscarTodasDependencias());
		assertThat(residenteService.salvar(residente)).isNotNull();
	}
	@Test
	public void testa_se_nao_salva_residente_null(){
		Residente residente = null;
		assertThat(residenteService.salvar(residente)).isNull();
	}
	@Test
	public void testa_se_nao_salva_residente_com_campo_null(){
		Residente residente = new Residente(null, null, null, "teste", "teste", null, null, null, null);
		assertThat(residenteService.salvar(residente)).isNull();
	}

	@Test
	public void testa_calcula_vagas(){
		int [] vagas = residenteService.calculaVagas();
		assertThat(vagas[0]).isEqualTo(2);
		assertThat(vagas[1]).isEqualTo(38);
	}

	@Test
	public void testa_buscar_todos_residentes(){
		assertThat(residenteService.listarTodos().size()).isEqualTo(2);
	}

	@Test
	public void busca_com_id_valido(){
		assertThat(residenteService.buscar(2L)).isNotNull();
	}

	@Test
	public void busca_com_id_invalido(){
		assertThat(residenteService.buscar(3L)).isNull();
	}

	@Test
	public void busca_com_id_null(){
		assertThat(residenteService.buscar(null)).isNull();
	}

	@Test
	public void testa_reingressar_residente_id_valido(){
		String dataEntrada = residenteService.buscar(1L).getDataEntrada()+"";
		Residente residente = residenteService.reingressar(1L);
		assertThat(residente.getDataEntrada()).isEqualToIgnoringMinutes(DateTime.now().toDate());
		assertThat(residente.getObservacoes()).contains(dataEntrada);
		assertThat(residente.getDataSaida()).isNull();
	}
	@Test
	public void testa_reingressar_residente_id_invalido(){
		assertThat(residenteService.reingressar(3L)).isNull();
	}
	@Test
	public void testa_reingressar_residente_id_null(){
		assertThat(residenteService.reingressar(null)).isNull();
	}

	@Test
	public void testa_desligar_residente_id_valido(){
		Residente residente = residenteService.desligar(2L);
		assertThat(residente.getDataSaida()).isEqualToIgnoringMinutes(DateTime.now().toDate());
	}
	@Test
	public void testa_desligar_residente_id_invalido(){
		assertThat(residenteService.desligar(3L)).isNull();
	}
	@Test
	public void testa_desligar_residente_id_null(){
		assertThat(residenteService.desligar(null)).isNull();
	}

	@Test
	public void testa_calcula_tempo_na_casa_id_null(){
		assertThat(residenteService.calculaTempoNaCasa(null)).isNull();
	}
	@Test
	public void testa_calcula_tempo_na_casa_id_invalido(){
		assertThat(residenteService.calculaTempoNaCasa(3L)).isNull();
	}
	@Test
	public void testa_calcula_tempo_na_casa_menor_que_30_dias(){
		assertThat(residenteService.calculaTempoNaCasa(1L)).isEqualTo("17 dias");
	}

	@Test
	public void testa_calcula_tempo_na_casa_um_mes(){
		Residente residente = residenteService.buscar(1L);
		Calendar data = Calendar.getInstance();
		data.set(Calendar.YEAR, 2017);
		data.set(Calendar.MONTH, 04);
		data.set(Calendar.DAY_OF_MONTH,01);
		Date date = new Date(data.getTimeInMillis());
		residente.setDataEntrada(date);
		residente.setId(1L);
		residenteService.salvar(residente);
		assertThat(residenteService.calculaTempoNaCasa(1L)).isEqualTo("1 mes");
	}
	@Test
	public void testa_calcula_tempo_na_casa_mais_de_mes(){
		Residente residente = residenteService.buscar(1L);
		Calendar data = Calendar.getInstance();
		data.set(Calendar.YEAR, 2017);
		data.set(Calendar.MONTH, 03);
		data.set(Calendar.DAY_OF_MONTH,01);
		Date date = new Date(data.getTimeInMillis());
		residente.setDataEntrada(date);
		residente.setId(1L);
		residenteService.salvar(residente);
		assertThat(residenteService.calculaTempoNaCasa(1L)).isEqualTo("2 meses");
	}
}
