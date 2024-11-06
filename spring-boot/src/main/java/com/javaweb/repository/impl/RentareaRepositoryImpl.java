package com.javaweb.repository.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.RentareaEntity;

@Repository
public class RentareaRepositoryImpl implements RentareaRepository {

	
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/estabasic";
	static final String USER = "root";
	static final String PASS = "amfrbghaf123@";

	@Override
	public List<RentareaEntity> findAll(Integer id) {
		String sql="SELECT * FROM rentarea RA WHERE RA.buildingid="+id;
		List<RentareaEntity> arr=new ArrayList<RentareaEntity>();
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {
				while(rs.next()) {
					RentareaEntity rentareaEntity=new RentareaEntity();
					rentareaEntity.setValue(rs.getString("value"));
					arr.add(rentareaEntity);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				//System.out.println("Connected database failed...");
			}

		return arr;
	}
	
	
}
