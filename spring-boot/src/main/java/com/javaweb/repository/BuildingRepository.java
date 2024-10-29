package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.repository.entity.*;

public interface BuildingRepository {
	public List<BuildingEntity> findAll(Map<String,Object> request,List<String> typecode);
}
