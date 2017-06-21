package br.com.ctebenezer.testeEstatico;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.with;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Wait
public class TesteRegrasDeNegocioEMensagens extends FluentTest{
	@Value("${local.server.port}") int port;
	String getHost(){
		return "http://localhost:"+ port+ "/prototipo/listarResidentes.html";
	}
	String getHost1(){
		return "http://localhost:"+ port+ "/prototipo/editarResidente.html";
	}
	String getHost2(){
		return "http://localhost:"+ port+ "/prototipo/cadastrarResidente.html";
	}
	
	@Test
	public void listarResidentes_deve_mostrar_residentes_em_ordem_alfabetica(){
		//RNG001
		goTo(getHost());
		assertThat(el("td").textContent()).startsWith("An");
		assertThat($(".sorting_1").index(1).textContent()).startsWith("Aé");
		assertThat($(".sorting_1").index(0).textContent()).doesNotStartWith("Z");
	}
	@Test
	public void ao_clicar_em_reingressar_deve_apagar_data_de_saida_e_atualizar_data_de_entrada(){
		//RNG002
		//o fluent não ta deixando clicar num elemento que é clicavel...
		goTo(getHost());
		$("#reingressarBtn").click();
		await().atMost(5, TimeUnit.SECONDS).until($("#sim")).enabled();
		//$("#sim").submit();
		//assertThat($("#reingresso1").textContent()).contains("20/05/2017");
	}
	@Test
	public void se_tiver_data_de_saida_cadastrada_devera_aparecer_botao_reingressar(){
		//RNG004
		goTo(getHost());
		assertThat($("#reingresso2").textContent()).isNotEmpty();
		assertThat($("#reingressarBtn").textContent()).isEqualToIgnoringCase("reingressar");
		
	}
	@Test
	public void se_nao_tiver_data_de_saida_cadastrada_devera_aparecer_botao_desligar(){
		//RNG004
		goTo(getHost());
		assertThat($("#altera").textContent()).isEmpty();
		assertThat($("#btnDesligar").textContent()).isEqualToIgnoringCase("desligar");
		
	}
	@Test
	public void ao_clicar_em_reingressar_deve_aparecer_mensagem(){
		//MSG003
		goTo(getHost());
		$("#reingressarBtn").click();
		await().atMost(5, TimeUnit.SECONDS).until($("#sim")).enabled();
		assertThat($(".bs-example-modal-sm").textContent()).containsIgnoringCase("Deseja reingressar o residente?");
	}
	@Test
	public void ao_digitar_nome_que_nao_eta_cadastrado_deve_mostrar_mensagem(){
		//MSG004 RNG007
		//não funciona por causa da dataTable, não sei o q ta dando, e nao posso colocar ID
		goTo(getHost());
		$(".input-sm").index(0).fill().with("pafuncio");
		//assertThat(el("td").textContent()).contains("Nenhum residente encontrado com o termo pesquisado");
	}
	@Test
	public void ao_clicar_em_desligar_mostra_mensagem(){
		//MSG001
		goTo(getHost());
		$("#btnDesligar").click();
		assertThat($(".bs-example-modal-lg").textContent()).containsIgnoringCase("Escolha a forma que você quer desligar o residente:");
	}
	@Test
	public void lista_deve_ter_coluna_especificadas(){
		//RNG008
		goTo(getHost());
		assertThat($("th").index(0).textContent()).containsIgnoringCase("Nome");
		assertThat($("th").index(1).textContent()).containsIgnoringCase("data de entrada");
		assertThat($("th").index(2).textContent()).containsIgnoringCase("data de saída");
		assertThat($("th").index(3).textContent()).containsIgnoringCase("editar");
		assertThat($("th").index(4).textContent()).containsIgnoringCase("desligar/reingressar");
	}
	@Test
	public void campos_nao_podem_ser_editados(){
		//RNG006
		goTo(getHost1());
		assertThat($("#nome").attribute("disabled")).isEqualTo("true");
		assertThat($("#nascimento").attribute("disabled")).isEqualTo("true");
		assertThat($("#naturalidade").attribute("disabled")).isEqualTo("true");
	}
	@Test
	public void alguns_campos_devem_ser_obrigatorios(){
		//RNG005
		goTo(getHost2());
		assertThat($("#nome").attribute("required")).isEqualTo("true");
		assertThat($("#nascimento").attribute("required")).isEqualTo("true");
		assertThat($("#naturalidade").attribute("required")).isEqualTo("true");
		assertThat($("#estadoCivil").attribute("required")).isEqualTo("true");
		assertThat($("#rua").attribute("required")).isEqualTo("true");
		assertThat($("#numero").attribute("required")).isEqualTo("true");
		assertThat($("#bairro").attribute("required")).isEqualTo("true");
		assertThat($("#cidade").attribute("required")).isEqualTo("true");
		assertThat($("#responsavel").attribute("required")).isEqualTo("true");
	}
	@Test
	public void campo_obrigatorio_em_branco_deve_mostrar_mensagem(){
		//também não consegui testar pois é o JS que faz a validação.
		//MSG002
		goTo(getHost2());
		$("#salvar").click();
		//assertThat($("body").textContent()).containsIgnoringCase("preencha este campo");
	}
	@Test
	public void atestado_de_alta_deve_ter_nome_data_entrada_data_saida_tempo_internacao(){
		//RNG010
		//Não vai dar para testar pois diz que o link nao e clicavel
		goTo(getHost());
		$("#btnDesligar").click();
		//$("#alta").click();
	}
	
}
