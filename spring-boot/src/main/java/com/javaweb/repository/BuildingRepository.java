package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.*;

public interface BuildingRepository {
	public List<BuildingEntity> findAll(Integer Id,Integer districtId);
}
