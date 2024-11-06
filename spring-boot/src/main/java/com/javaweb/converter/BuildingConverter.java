package com.javaweb.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentareaEntity;


@Component
public class BuildingConverter {
	
	@Autowired
	public DistrictRepository districtRepository;
	
	@Autowired
	public RentareaRepository rentareaRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	public BuildingResponseDTO toBuildingResponseDTO(BuildingEntity it) {
		BuildingResponseDTO buildingReponseDto=modelMapper.map(it, BuildingResponseDTO.class);
		List<RentareaEntity> rentareaEntity = rentareaRepository.findAll(it.getId());
		DistrictEntity districtEntity=districtRepository.findAll(it.getDistrictid());
//		buildingReponseDto.setId(it.getId());
//		buildingReponseDto.setName(it.getName());
		buildingReponseDto.setAddress(it.getStreet()+", "+it.getWard()+", "+districtEntity.getName());
//		buildingReponseDto.setNumberofbasement(it.getNumberofbasement());
		StringBuilder area=new StringBuilder();
		for(RentareaEntity item:rentareaEntity) {
			area.append(item.getValue()+",");
		} 
		if(area.length()!=0) area.deleteCharAt(area.length()-1);
		buildingReponseDto.setArea(area.toString());
//		buildingReponseDto.setFloorarea(it.getFloorarea());
//		buildingReponseDto.setManagername(it.getManagername());
//		buildingReponseDto.setManagerphonenumber(it.getManagerphonenumber());
//		buildingReponseDto.setRentprice(it.getRentprice());
//		buildingReponseDto.setServicefee(it.getServicefee());
//		buildingReponseDto.setBrokeragefee(it.getBrokeragefee());
		return buildingReponseDto;
	}
}
