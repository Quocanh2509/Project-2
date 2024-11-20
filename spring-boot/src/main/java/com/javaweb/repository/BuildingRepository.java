package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.*;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Integer>,BuildingRepositoryCustom{
	//public List<BuildingEntity> findAll(BuildingSearchBuilder builder);
}
