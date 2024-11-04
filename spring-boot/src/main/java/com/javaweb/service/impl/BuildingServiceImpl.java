package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.Beans.response.RentareaResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentareaEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	public BuildingRepository buildingRepository;

	@Autowired
	public DistrictRepository districtRepository;
	
	@Autowired
	public RentareaRepository rentareaRepository;
	
	@Override
	public List<BuildingResponseDTO> findAll(Map<String,Object> request,List<String> typecode) {
		List<BuildingEntity> result=buildingRepository.findAll(request,typecode);
		List<BuildingResponseDTO> ans=new ArrayList<BuildingResponseDTO>();
		for(BuildingEntity it:result) {
			BuildingResponseDTO buildingReponseDto=new BuildingResponseDTO();
			List<RentareaEntity> rentareaEntity = rentareaRepository.findAll(it.getId());
			DistrictEntity districtEntity=districtRepository.findAll(it.getDistrictId());
			buildingReponseDto.setId(it.getId());
			buildingReponseDto.setName(it.getName());
			buildingReponseDto.setAddress(it.getStreet()+", "+it.getWard()+", "+districtEntity.getName());
			buildingReponseDto.setNumberofbasement(it.getNumberofbasement());
			StringBuilder area=new StringBuilder();
			for(RentareaEntity item:rentareaEntity) {
				area.append(item.getValue()+",");
			}
			area.deleteCharAt(area.length()-1);
			buildingReponseDto.setArea(area.toString());
			buildingReponseDto.setFloorarea(it.getFloorarea());
			buildingReponseDto.setManagername(it.getManagername());
			buildingReponseDto.setManagerphonenumber(it.getManagerphonenumber());
			buildingReponseDto.setRentprice(it.getRentprice());
			buildingReponseDto.setServicefee(it.getServicefee());
			buildingReponseDto.setBrokeragefee(it.getBrokeragefee());
			ans.add(buildingReponseDto);
		}
		return ans;
	}
	


	
}
