package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;


@Component
public class BuildingSearchBuilderConverter {
	
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> request,List<String> typeCode) {
		// truy cập vào class Builder rồi truy cập vào từng trường dữ liệu 
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
				                                       .setId(MapUtil.getObject(request, "id", Integer.class))
				                                       .setName(MapUtil.getObject(request, "name", String.class))
				                                       .setDistrictId(MapUtil.getObject(request, "districtId", Integer.class))
				                                       .setAreaFrom(MapUtil.getObject(request, "areaFrom", Integer.class))
				                                       .setAreaTo(MapUtil.getObject(request, "areaTo", Integer.class))
				                                       .setFloorArea(MapUtil.getObject(request, "floorArea", Integer.class))
				                                       .setManagerName(MapUtil.getObject(request, "managerName", String.class))
				                                       .setManagerPhoneNumber(MapUtil.getObject(request, "managerPhoneNumber", String.class))
				                                       .setTypeCode(typeCode)
				                                       .setRentPriceFrom(MapUtil.getObject(request, "rentPriceFrom", Integer.class))
				                                       .setRentPriceTo(MapUtil.getObject(request, "rentPriceTo", Integer.class))
				                                       .setNumberOfBasement(MapUtil.getObject(request, "numberOfBasement", Integer.class))
				                                       .setStaffId(MapUtil.getObject(request, "staffId", Integer.class))
				                                       .setStreet(MapUtil.getObject(request, "street", String.class))
				                                       .setWard(MapUtil.getObject(request, "ward", String.class))
				                                       .build();
		return buildingSearchBuilder;
	}
}
