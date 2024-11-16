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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

//@Entity
//@Table(name="building")
public class BuildingEntity {
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//@Column(name="name")
	private String name;
	
	//@Column(name="street")
	private String street;
	
	//@Column(name="ward")
	private String ward;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="districtid")
//	private DistrictEntity district;
//	
//	@OneToMany(mappedBy = "buildingEntity",fetch = FetchType.LAZY)
//	private List<RentareaEntity> rentareas = new ArrayList<>();
	
	
	
//	public List<RentareaEntity> getRentareas() {
//		return rentareas;
//	}
//	public void setRentareas(List<RentareaEntity> rentareas) {
//		this.rentareas = rentareas;
//	}
//	public Integer getDistrictid() {
//		return districtid;
//	}
//	public void setDistrictid(Integer districtid) {
//		this.districtid = districtid;
//	}
	private Integer districtid;
	private Integer numberofbasement;
	private Integer floorarea;
	private String managername;
	private String managerphonenumber;
	private Integer rentprice;
	private String servicefee;
	private Integer brokeragefee;
	private Integer emptyspace;
	
	//@Column(name="map")
	private String map;
	
	
	
	
	
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Integer getDistrictid() {
		return districtid;
	}
	public void setDistrictid(Integer districtid) {
		this.districtid = districtid;
	}
	public Integer getNumberofbasement() {
		return numberofbasement;
	}
	public void setNumberofbasement(Integer numberofbasement) {
		this.numberofbasement = numberofbasement;
	}
	public Integer getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(Integer floorarea) {
		this.floorarea = floorarea;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public void setManagerphonenumber(String managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}
	public Integer getRentprice() {
		return rentprice;
	}
	public void setRentprice(Integer rentprice) {
		this.rentprice = rentprice;
	}
	public String getServicefee() {
		return servicefee;
	}
	public void setServicefee(String servicefee) {
		this.servicefee = servicefee;
	}
	public Integer getBrokeragefee() {
		return brokeragefee;
	}
	public void setBrokeragefee(Integer brokeragefee) {
		this.brokeragefee = brokeragefee;
	}
	public Integer getEmptyspace() {
		return emptyspace;
	}
	public void setEmptyspace(Integer emptyspace) {
		this.emptyspace = emptyspace;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
//	public DistrictEntity getDistrict() {
//		return district;
//	}
//	public void setDistrict(DistrictEntity district) {
//		this.district = district;
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
}
