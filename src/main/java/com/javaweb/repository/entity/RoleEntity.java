package com.javaweb.repository.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "role")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "code",unique = true, nullable = false)
	private String code;

//	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//	private List<UserRoleEntity> items = new ArrayList<>() ;
//	
	
//	public List<UserRoleEntity> getItems() {
//		return items;
//	}
//
//	public void setItems(List<UserRoleEntity> items) {
//		this.items = items;
//	}
	
	@ManyToMany(mappedBy = "roles")
	private List<UserEntity> users;
	
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
