package br.com.ctebenezer.domain;

import javax.persistence.*;

@Entity
public class Pessoa {
	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	@OneToOne
	private File picture;

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
