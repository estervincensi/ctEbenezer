package br.com.ctebenezer.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.ctebenezer.domain.enumerables.Dependencias;
import br.com.ctebenezer.domain.enumerables.EstadoCivil;

public class Residente {
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome, naturalidade, profissao, observacoes, responsavel;
	private Date dataNascimento;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	@ManyToOne
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private List<Dependencias> dependencias;
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
	public List<Dependencias> getDependencias() {
		return dependencias;
	}
	public void setDependencias(List<Dependencias> dependencias) {
		this.dependencias = dependencias;
	}
	public Residente(String nome, String naturalidade, String profissao, String observacoes, String responsavel,
			Date dataNascimento, EstadoCivil estadoCivil, Endereco endereco,
			List<Dependencias> dependencias) {
		this.nome = nome;
		this.naturalidade = naturalidade;
		this.profissao = profissao;
		this.observacoes = observacoes;
		this.responsavel = responsavel;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.endereco = endereco;
		this.dependencias = dependencias;
	}
	public Residente() {
	}
	
	
	
}
