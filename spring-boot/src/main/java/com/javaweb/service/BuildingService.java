package com.javaweb.service;


import java.util.List;
import java.util.Map;

import com.javaweb.Beans.response.BuildingResponseDTO;

public interface BuildingService {
	//public List<BuildingResponseDTO> findAll(Integer Id,Integer districtId);

	public List<BuildingResponseDTO> findAll(Map<String, Object> request,List<String> typecode);
}
