package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	public DistrictEntity findAll(Integer id) {
		String sql="SELECT name from district where id="+id;
		DistrictEntity districtEntity=new DistrictEntity();
		Query query=entityManager.createNativeQuery(sql,DistrictEntity.class);
		//return query.getSingleResult();
//		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//				Statement stm = conn.createStatement();
//				ResultSet rs = stm.executeQuery(sql.toString())) {
//				while(rs.next()) {
//					districtEntity.setName(rs.getString("name"));
//				}
//				//System.out.println("Connected database successfully...");
//			} catch (SQLException e) {
//				e.printStackTrace();
//				System.out.println("Connected database failed...");
//			}
//		System.out.print(districtEntity.getName());
		return districtEntity;
	}
	
}
