package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="code")
	private String code;
	
	@ManyToMany(mappedBy = "role")
	private List<UserEntity>userEntity = new ArrayList<UserEntity>();
	
	
//	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
//	private List<UserRoleEntity> UserRoleEntity=new ArrayList<>();
//	
//
//	public List<UserRoleEntity> getUserRoleEntity() {
//		return UserRoleEntity;
//	}
//
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
