package br.com.ctebenezer.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.ctebenezer.domain.enumerables.Dependencias;

@Entity
public class Pia {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Residente residente;
	
	private boolean viveuNaRua, protecaoJudicial, desistiu, ativo;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<Dependencias> dependencias;
	
	@ElementCollection
	private List<String> aptidoes;
	
	private Date dataEntrada, dataSaida;
	
	private String avaliacaoInicial;
	
	private String tempoPrevisto;
	
	@ManyToMany
	private List<Atividades> agendaSemanal;
	
	private String avaliacaoFinal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Residente getResidente() {
		return residente;
	}

	public void setResidente(Residente residente) {
		this.residente = residente;
	}

	public boolean isViveuNaRua() {
		return viveuNaRua;
	}

	public void setViveuNaRua(boolean viveuNaRua) {
		this.viveuNaRua = viveuNaRua;
	}

	public boolean isProtecaoJudicial() {
		return protecaoJudicial;
	}

	public void setProtecaoJudicial(boolean protecaoJudicial) {
		this.protecaoJudicial = protecaoJudicial;
	}

	public List<Dependencias> getDependencias() {
		return dependencias;
	}

	public void setDependencias(List<Dependencias> dependencias) {
		this.dependencias = dependencias;
	}

	public List<String> getAptidoes() {
		return aptidoes;
	}

	public void setAptidoes(List<String> aptidoes) {
		this.aptidoes = aptidoes;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getAvaliacaoInicial() {
		return avaliacaoInicial;
	}

	public void setAvaliacaoInicial(String avaliacaoInicial) {
		this.avaliacaoInicial = avaliacaoInicial;
	}

	public String getTempoPrevisto() {
		return tempoPrevisto;
	}

	public void setTempoPrevisto(String tempoPrevisto) {
		this.tempoPrevisto = tempoPrevisto;
	}

	public List<Atividades> getAgendaSemanal() {
		return agendaSemanal;
	}

	public void setAgendaSemanal(List<Atividades> agendaSemanal) {
		this.agendaSemanal = agendaSemanal;
	}

	public String getAvaliacaoFinal() {
		return avaliacaoFinal;
	}

	public void setAvaliacaoFinal(String avaliacaoFinal) {
		this.avaliacaoFinal = avaliacaoFinal;
	}

	public boolean isDesistiu() {
		return desistiu;
	}

	public void setDesistiu(boolean desistiu) {
		this.desistiu = desistiu;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
		
}
