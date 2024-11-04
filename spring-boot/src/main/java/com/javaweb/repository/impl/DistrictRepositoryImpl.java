package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{

	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/estabasic";
	static final String USER = "root";
	static final String PASS = "amfrbghaf123@";
	
	@Override
	public DistrictEntity findAll(Integer id) {
		String sql="SELECT name from district where id="+id;
		DistrictEntity districtEntity=new DistrictEntity();
		
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql.toString())) {
				while(rs.next()) {
					districtEntity.setName(rs.getString("name"));
				}
				System.out.println("Connected database successfully...");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Connected database failed...");
			}

		return districtEntity;
	}
	
}
