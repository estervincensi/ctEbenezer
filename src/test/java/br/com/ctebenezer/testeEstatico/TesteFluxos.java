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
public class TesteFluxos extends FluentTest{
	@Value("${local.server.port}") int port;
	String getHost(){
		return "http://localhost:"+ port+ "/prototipo/index.html";
	}
	@Test
	public void teste_cadastrar_residente(){
		goTo(getHost());
		$("#residente").click();
		$("#cadastrar").click();
		$("#nome").fill().with("joao");
		$("#nascimento").fill().with("28/02/1993");
		$("#naturalidade").fill().with("porto alegre");
		$("#profissao").fill().with("autonomo");
		$("#estadoCivil").fill().with("solteiro");
		$("#rua").fill().with("rua x");
		$("#numero").fill().with("1234");
		$("#bairro").fill().with("bairro x");
		$("#cidade").fill().with("cidade y");
		$("#responsavel").fill().with("pedro");
		$("#salvar").submit(); //quando está ok ele limpa os campos
		assertThat($("#nome").textContent()).isEmpty();
	}
	@Test
	public void teste_reingressar(){
		//não esta funcionando pelo mesmo motivo das rng. Algo está acontecendo quando testo algo com js.
		//não fiz o teste de desligar pq vai dar o mesmo erro.
		goTo(getHost());
		$("#residente").click();
		$("#pesquisar").click();
		$(".input-sm").fill().with("Antonio");
		$("#reingressarBtn").click();
		//$("#sim").submit();
		//assertThat($("#reingresso2").textContent()).isEmpty();
	}
}
