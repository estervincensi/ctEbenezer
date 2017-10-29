package br.com.ctebenezer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Receita {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private Consulta consulta;
	
	private String descricaoDaReceita;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getDescricaoDaReceita() {
		return descricaoDaReceita;
	}

	public void setDescricaoDaReceita(String descricaoDaReceita) {
		this.descricaoDaReceita = descricaoDaReceita;
	}
	
	

}
