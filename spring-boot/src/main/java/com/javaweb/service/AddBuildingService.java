package com.javaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.repository.AddBuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Service
public class AddBuildingService {
	
	
	@Autowired
	private AddBuildingRepository addbuildingrepository;
	
	
	 public void addBuilding(BuildingEntity building) {
	        addbuildingrepository.save(building);
	 }
}
