package com.javaweb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javaweb.repository.entity.BuildingEntity;


@Repository
public class AddBuildingRepository {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(BuildingEntity building) {
        String sql = "INSERT INTO building (id, name, street, ward, districtid, numberofbasement, floorarea, managername, managerphonenumber, rentprice, servicefee, brokeragefee) \r\n"
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);\r\n"
        		+ "";
        return jdbcTemplate.update(sql, 
                building.getId(), 
                building.getName(), 
                building.getStreet(), 
                building.getWard(), 
                building.getDistrictid(), 
                building.getNumberofbasement(), 
                building.getFloorarea(), 
                building.getManagername(), 
                building.getManagerphonenumber(), 
                building.getRentprice(), 
                building.getServicefee(), 
                building.getBrokeragefee()
         
            );
    }
}
