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

import br.com.ctebenezer.domain.Consulta;
import br.com.ctebenezer.domain.Residente;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ConsultaServiceTest {
	
	@Autowired
	ConsultaService consultaService;
	
	@Test
	public void testa_salvar_info_null(){
		Consulta consulta = new Consulta();
		assertThat(consultaService.salvarInfo(consulta)).isNull();
	}
	
	@Test
	public void testa_salvar_info_empty(){
		Consulta consulta = new Consulta();
		consulta.setObservacoes("");
		assertThat(consultaService.salvarInfo(consulta)).isNull();
	}
	
	@Test
	public void testa_salvar_info_valida(){
		Consulta consulta = new Consulta();
		consulta.setObservacoes("teste");
		assertThat(consultaService.salvarInfo(consulta)).isNotNull();
	}
	
	@Test
	public void testa_buscar_horas_para_consulta(){
		assertThat(consultaService.buscarHoras()).size().isEqualTo(10);
	}
	
	@Test
	public void testa_cancelar_consulta_null(){
		assertThat(consultaService.cancelar(null)).isFalse();
	}
	
	@Test
	public void testa_cancelar_consulta_id_invalido(){
		assertThat(consultaService.cancelar(-1L)).isFalse();
	}
	
	@Test
	public void testa_cancelar_consulta_id_valido(){
		assertThat(consultaService.cancelar(1L)).isTrue();
	}
	
	@Test
	public void testa_buscar_consulta_por_id_null(){
		assertThat(consultaService.buscarPorId(null)).isNull();
	}
	
	@Test
	public void testa_buscar_consulta_por_id_invalido(){
		assertThat(consultaService.buscarPorId(-1L)).isNull();
	}
	
	@Test
	public void testa_buscar_consulta_por_id_valido(){
		assertThat(consultaService.buscarPorId(1L)).isNotNull();
	}
	
	@Test
	public void testa_buscar_todas(){
		assertThat(consultaService.buscarTodas()).isNotEmpty();
	}
	

	@Test
	public void testa_horario_com_residente_id_null_e_data_correta(){
		Consulta c = new Consulta();
		Residente r = new Residente();
		r.setRg("123456789");
		c.setResidente(r);
		c.setData(DateTime.now().plusDays(20).toDate());
		c.setHora("10:00");
		assertThat(consultaService.salvar(c)).isTrue();
		
	}

	@Test
	public void testa_horario_com_residente_id_null_e_data_inccorreta(){
		Consulta c = new Consulta();
		Residente r = new Residente();
		r.setRg("123456789");
		c.setResidente(r);
		c.setData(DateTime.now().minusDays(20).toDate());
		c.setHora("10:00");
		assertThat(consultaService.salvar(c)).isFalse();
		
	}
	@Test
	public void testa_horario_com_residente_rg_null_e_data_correta(){
		Consulta c = new Consulta();
		Residente r = new Residente();
		r.setRg(null);
		c.setResidente(r);
		c.setData(DateTime.now().plusDays(20).toDate());
		c.setHora("10:00");
		assertThat(consultaService.salvar(c)).isFalse();
		
	}
	
	//TODO: Verificar data ja existente no banco
	@Test
	public void testa_horario_indisponivel(){
		Consulta c = new Consulta();
		Residente r = new Residente();
		r.setRg("123456789");
		c.setResidente(r);
		//c.setData(DateTime.now().plusDays(20).toDate());
		//c.setHora("10:00");
		assertThat(consultaService.salvar(c)).isFalse();
	}

}
