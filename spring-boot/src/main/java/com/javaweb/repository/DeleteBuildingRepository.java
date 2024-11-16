package com.javaweb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteBuildingRepository {
	
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public int deleteBuildingById(int id) {
		 String sql = "DELETE FROM building WHERE id = ?";
		 //System.out.println( );
	     return jdbcTemplate.update(sql, id);
	}
}
