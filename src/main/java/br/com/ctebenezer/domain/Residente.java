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
import javax.persistence.ManyToOne;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.ctebenezer.domain.enumerables.Dependencias;
import br.com.ctebenezer.domain.enumerables.EstadoCivil;

@Entity
public class Residente {
	@Id
	@GeneratedValue
	private Long id;
	@Required
	private String nome, naturalidade,responsavel, telefone;

	private String profissao, observacoes;
	private boolean ativo, piaAtivo;

	@Required
	private Date dataNascimento;

	@Required
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;

	@Required
	@ManyToOne(cascade=CascadeType.ALL)
	private Endereco endereco;
	
	
	public boolean isPiaAtivo() {
		return piaAtivo;
	}
	public void setPiaAtivo(boolean temPia) {
		this.piaAtivo = temPia;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Residente(String nome, String naturalidade, String profissao, String observacoes, String responsavel,
			Date dataNascimento, EstadoCivil estadoCivil, Endereco endereco) {
		this.nome = nome;
		this.naturalidade = naturalidade;
		this.profissao = profissao;
		this.observacoes = observacoes;
		this.responsavel = responsavel;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.endereco = endereco;
	}
	public Residente() {
	}
	



}
