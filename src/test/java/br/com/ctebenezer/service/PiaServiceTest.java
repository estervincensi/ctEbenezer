package br.com.ctebenezer.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ctebenezer.domain.Pia;
import br.com.ctebenezer.domain.Residente;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class PiaServiceTest {
	@Autowired
	PiaService piaService;
	
	@Test
	public void testa_se_retorna_dependencias(){
		assertThat(piaService.buscarTodasDependencias()).size().isEqualTo(4);
	}
	
	@Test
	public void testa_calcula_tempo_na_casa_id_null(){
		assertThat(piaService.calculaTempoNaCasa(null)).isNull();
	}
	
	@Test
	public void testa_calcula_tempo_na_casa_id_invalido(){
		assertThat(piaService.calculaTempoNaCasa(-1L)).isNull();
	}
	
	@Test
	public void testa_calcula_tempo_na_casa_tempo_menor_30(){
		//assertThat(piaService.calculaTempoNaCasa(-1L)).isNull();
	}
	
	@Test
	public void testa_calcula_tempo_na_casa_tempo_igual_30(){
		//assertThat(piaService.calculaTempoNaCasa(-1L)).isNull();
	}
	
	@Test
	public void testa_calcula_tempo_na_casa_tempo_maior_2_meses(){
		//assertThat(piaService.calculaTempoNaCasa(-1L)).isNull();
	}
	
	@Test
	public void testa_baixas_por_ano(){
		assertThat(piaService.baixasPorAno()).isNotEmpty();
	}
	
	@Test
	public void testa_atendimentos_por_ano(){
		assertThat(piaService.atendimentosPorAno()).isNotEmpty();
	}
	
	@Test
	public void testa_dependencias_da_comunidade(){
		assertThat(piaService.dependencias()).isNotEmpty();
	}
	
	@Test
	public void testa_buscar_pia_por_id_null(){
		assertThat(piaService.buscarPorId(null)).isNull();
	}
	
	@Test
	public void testa_buscar_pia_por_id_invalido(){
		assertThat(piaService.buscarPorId(-1L)).isNull();
	}
	
	@Test
	public void testa_buscar_pia_por_id_valido(){
		assertThat(piaService.buscarPorId(1L)).isNotNull();
	}
	
	@Test
	public void testa_buscar_pia_por_residente_id_null(){
		assertThat(piaService.buscarPorResidenteId(null)).isNull();
	}
	
	@Test
	public void testa_buscar_pia_por_residente_id_invalido(){
		assertThat(piaService.buscarPorResidenteId(-1L)).isNull();
	}
	
	@Test
	public void testa_buscar_pia_por_residente_id_valido(){
		assertThat(piaService.buscarPorResidenteId(1L)).isNotNull();
	}
	
	@Test
	public void testa_salvar_pia_null(){
		assertThat(piaService.salvar(null)).isNull();
	}
	
	@Test
	public void testa_salvar_pia_residente_null(){
		Pia p = new Pia();
		assertThat(piaService.salvar(p)).isNull();
	}
	
	@Test
	public void testa_salvar_pia_valido(){
		Pia p = new Pia();
		Residente r = new Residente();
		p.setResidente(r);
		assertThat(piaService.salvar(p)).isNotNull();
		
	}
	
	@Test
	public void testa_desligar_pia_null(){
		assertThat(piaService.desligar(null)).isNull();
	}
	
	@Test
	public void testa_desligar_pia_id_invalido(){
		Pia p = new Pia();
		p.setId(-1L);
		assertThat(piaService.desligar(p)).isNull();
	}
	
	@Test
	public void testa_desligar_pia_valido(){
		Pia p = new Pia();
		p.setId(1L);
		assertThat(piaService.desligar(p)).isNotNull();
	}
}
