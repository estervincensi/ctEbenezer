package br.com.ctebenezer.domain;

import java.math.BigInteger;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "relatorio" , types = Pia.class)
public interface RelatorioProjection {
	
	Integer getAno();
	boolean getDesistiu();
	BigInteger getNumero();

}
