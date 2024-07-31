package com.javaweb.repository.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
@Table(name = "userrole")
public class userRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private userEntity user;
	
	@ManyToOne
	@JoinColumn(name = "roleid")
	private roleEntity role;

	
	public Long getId() {
		return id;
	}

	public userEntity getUser() {
		return user;
	}

	public roleEntity getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(userEntity user) {
		this.user = user;
	}

	public void setRole(roleEntity role) {
		this.role = role;
	}
	
	
}
