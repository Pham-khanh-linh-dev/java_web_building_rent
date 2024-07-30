package com.javaweb.repository.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Table(name = "userrole")
public class userRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "userid", nullable = false)
	private Long userid;
	
	@Column(name = "roleid", nullable = false)
	private Long roleid;

	@ManyToOne
	@JoinColumn(name = "userid")
	private userEntity user;
	
	@ManyToOne
	@JoinColumn(name = "roleid")
	private roleEntity role;

	public Long getId() {
		return id;
	}

	public Long getUserid() {
		return userid;
	}

	public Long getRoleid() {
		return roleid;
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

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public void setUser(userEntity user) {
		this.user = user;
	}

	public void setRole(roleEntity role) {
		this.role = role;
	}
	
	
}
