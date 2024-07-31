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
public class UserRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "roleid")
	private RoleEntity role;

	
	public Long getId() {
		return id;
	}

	public UserEntity getUser() {
		return user;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	
}
