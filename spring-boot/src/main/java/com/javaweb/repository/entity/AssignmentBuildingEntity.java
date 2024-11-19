//package com.javaweb.repository.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="assignmentbuilding")
//public class AssignmentBuildingEntity {
//
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	
//	@Column(name="staffid")
//	private Integer staffId;
//	
//	
//	@ManyToMany(mappedBy = "assignmentBuildingEntities")
//	private List<BuildingEntity> buildingEntity=new ArrayList<>();
//	
//	
//	
//	
//}
