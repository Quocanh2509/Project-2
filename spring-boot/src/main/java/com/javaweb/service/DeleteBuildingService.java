package com.javaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.repository.DeleteBuildingRepository;

@Service
public class DeleteBuildingService {
	
	@Autowired
	public DeleteBuildingRepository deleteBuildingrepositoty;
	
	
	public void deleteBuilding(int id) {
		deleteBuildingrepositoty.deleteBuildingById(id);
    }
}
