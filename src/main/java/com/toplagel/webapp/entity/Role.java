package com.toplagel.webapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	private Long id;
	private String role;

	Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	String getRole() {
		return role;
	}

	void setRole(String role) {
		this.role = role;
	}

}
