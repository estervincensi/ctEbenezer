package br.com.ctebenezer.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Consulta {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Required
	@ManyToOne(cascade=CascadeType.ALL)
	private Account medico;
	
	@Required
	@ManyToOne(cascade=CascadeType.ALL)
	private Residente residente;
	
	private String observacoes;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date data;

	private String hora;
	
	private boolean ativo;
	
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Account getMedico() {
		return medico;
	}
	public void setMedico(Account medico) {
		this.medico = medico;
	}
	public Residente getResidente() {
		return residente;
	}
	public void setResidente(Residente residente) {
		this.residente = residente;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	
	
	
	
}
