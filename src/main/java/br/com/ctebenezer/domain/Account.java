package br.com.ctebenezer.domain;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	private String username;
	private String password;
	private boolean active;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	@OneToOne(fetch = FetchType.EAGER)
	private Pessoa pessoa;

	public Account() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}