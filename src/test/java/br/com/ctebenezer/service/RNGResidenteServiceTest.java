package br.com.ctebenezer.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.repository.ResidenteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class RNGResidenteServiceTest {
	//Esta classe contem apenas os testes para as regras de negócio que são aplicáveis a Service
	@Autowired
	ResidenteService residenteService;

	@Autowired
	ResidenteRepository residenteRepository;

	//TODO: RNG001
	@Test
	public void testa_se_retorna_residentes_em_ordem_alfabetica(){
		List<Residente> residentes = residenteService.listarTodos();
		assertThat(residentes.get(0).getNome()).startsWith("a");
		assertThat(residentes.get(1).getNome()).startsWith("j");
	}

	//TODO: RNG002
	@Test
	public void testa_se_altera_data_ao_reingressar(){
		residenteService.reingressar(1L); //residente que está com data de saída cadastrada
		Residente residente = residenteService.buscar(1L);
		assertThat(residente.getDataEntrada()).isEqualToIgnoringMinutes(DateTime.now().toDate());
		assertThat(residente.getDataSaida()).isNull();
	}

	//TODO: RNG003
	@Test
	public void testa_se_adiciona_em_observacoes_data_antiga_ao_reingressar(){
		String dataEntrada = residenteService.buscar(1L).getDataEntrada()+"";
		String dataSaida = residenteService.buscar(1L).getDataSaida()+"";
		residenteService.reingressar(1L); //residente que está com data de saída cadastrada
		Residente residente = residenteService.buscar(1L);
		assertThat(residente.getObservacoes()).contains(dataEntrada);
		assertThat(residente.getObservacoes()).contains(dataSaida);
	}
	//TODO: RNG005
	@Test
	public void testa_se_nao_salva_residente_com_campo_null(){
		Residente residente = new Residente(null, null, null, "teste", "teste", null, null, null, null);
		assertThat(residenteService.salvar(residente)).isNull();
	}
	//TODO: RNG009
	@Test
	public void testa_se_ao_desligar_residente_altera_data_de_saida(){
		residenteService.desligar(2L); //residente que tem data de saida null
		assertThat(residenteService.buscar(2L).getDataSaida()).isEqualToIgnoringMinutes(DateTime.now().toDate());
	}

}
