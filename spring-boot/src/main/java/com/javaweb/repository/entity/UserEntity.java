package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	 
	@Column(name="password")
	private String passWord;
	
	@Column(name="fullname")
	private String fullName;
	
	@Column(name="status")
	private Boolean status;

	
	@ManyToMany
	@JoinTable(
		name="user_role",
		joinColumns = @JoinColumn(name="userid"),
		inverseJoinColumns = @JoinColumn(name="roleid")
	)
	private List<RoleEntity> role=new ArrayList<>();

//	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
//	private List<UserRoleEntity> UserRoleEntity=new ArrayList<>();

//	public List<UserRoleEntity> getUserRoleEntity() {
//		return UserRoleEntity;
//	}
//	public void setUserRoleEntity(List<UserRoleEntity> userRoleEntity) {
//		UserRoleEntity = userRoleEntity;
//	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
}
