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
	public BuildingRepository buildingrepository;

	@Autowired
	public DistrictRepository districtrepository;
	
	@Autowired
	public RentareaRepository rentarearepository;
	
	@Override
	public List<BuildingResponseDTO> findAll(Map<String,Object> request,List<String> typecode) {
		List<BuildingEntity> result=buildingrepository.findAll(request,typecode);
		List<BuildingResponseDTO> ans=new ArrayList<BuildingResponseDTO>();
		for(BuildingEntity it:result) {
			BuildingResponseDTO buildingreponsedto=new BuildingResponseDTO();
			List<RentareaEntity> rentareaentity = rentarearepository.findAll(it.getId());
			DistrictEntity buildingentity=districtrepository.findAll(it.getDistrictId());
			buildingreponsedto.setId(it.getId());
			buildingreponsedto.setName(it.getName());
			buildingreponsedto.setAddress(it.getStreet()+", "+it.getWard()+", "+buildingentity.getName());
			buildingreponsedto.setNumberofbasement(it.getNumberofbasement());
			StringBuilder area=new StringBuilder();
			for(RentareaEntity item:rentareaentity) {
				area.append(item.getValue()+",");
			}
			area.deleteCharAt(area.length()-1);
			buildingreponsedto.setArea(area.toString());
			buildingreponsedto.setFloorarea(it.getFloorarea());
			buildingreponsedto.setManagername(it.getManagername());
			buildingreponsedto.setManagerphonenumber(it.getManagerphonenumber());
			buildingreponsedto.setRentprice(it.getRentprice());
			buildingreponsedto.setServicefee(it.getServicefee());
			buildingreponsedto.setBrokeragefee(it.getBrokeragefee());
			ans.add(buildingreponsedto);
		}
		return ans;
	}
	


	
}
