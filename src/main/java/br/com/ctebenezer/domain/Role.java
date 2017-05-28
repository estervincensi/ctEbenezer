package br.com.ctebenezer.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	private String role;
	@ManyToMany (mappedBy="roles")
	private Set<Account> accounts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
}
