package br.com.ctebenezer.domain;

import java.math.BigInteger;

public class Relatorio {
	private Integer ano;
	private boolean desistiu;
	private BigInteger numero;
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public boolean isDesistiu() {
		return desistiu;
	}
	public void setDesistiu(boolean desistiu) {
		this.desistiu = desistiu;
	}
	public BigInteger getNumero() {
		return numero;
	}
	public void setNumero(BigInteger numero) {
		this.numero = numero;
	}
	public Relatorio(Integer ano, boolean desistiu, BigInteger numero) {
		super();
		this.ano = ano;
		this.desistiu = desistiu;
		this.numero = numero;
	}
	public Relatorio() {
	}
	
	
	
	

}
