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
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{

	
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/estabasic";
	static final String USER = "root";
	static final String PASS = "amfrbghaf123@";
	
	
	
	public static boolean checkValue(String s) {
		try {
			Integer num=Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			return false;// TODO: handle exception
		}
		return true;
	}
	
	// co key co value
	public static boolean checkKey(Map<String,Object> request,String key) {
		if(request.containsKey(key)) {
			if(request.get(key)!=null||!request.get(key).equals("")) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public void joinTable(Map<String,Object> request,StringBuilder sql) {
		if(checkKey(request,"typecode")==true) {
			sql.append(" inner join buildingrenttype BR ON BD.id=BR.buildingid ");
			sql.append(" inner join renttype RT ON RT.id=BR.renttypeid ");
		}
		if(checkKey(request,"startarea")||checkKey(request,"endarea")) {
			sql.append(" inner join rentarea RA on BD.id=RA.buildingid ");
		}
	}
	
	
	
	
	public void whereTable(Map<String,Object> request,StringBuilder where) {
		for(Map.Entry<String, Object> item:request.entrySet()) {
			if(checkKey(request,"staffid")) {
				where.append(" AND EXISTS (SELECT 1 FROM assignmentbuilding AB WHERE AB.buildingid = BD.id ");
				where.append(" AND AB.staffid = " + request.get("staffid") + ")");
			}
			if(checkKey(request, item.getKey())&&!item.getKey().equals("typecode")&&!item.getKey().equals("staffid")&&!item.getKey().equals("startarea")&&
					!item.getKey().equals("endarea")&&!item.getKey().equals("startprice")&&!item.getKey().equals("endprice")) {
				String value=item.getValue().toString();
				if(checkValue(value)) {
					where.append(" AND BD."+item.getKey()+" = "+value);
				}
				else {
					where.append(" AND BD."+item.getKey()+" LIKE '%"+value+"%'");
				}
			}
		}
	}
	
	
	
	public void typeCode(Map<String,Object> request,List<String> typeCodes,StringBuilder sql) {
		int count=0;
		if(typeCodes==null) {
			return;
		}
		if(!typeCodes.isEmpty()) {
			for(int i=0;i<typeCodes.size();i++) {
				if(count==0) {
					sql.append(" AND (RT.code='"+typeCodes.get(i)+"' ");
					count++;
				}
				else {
					sql.append(" OR RT.code='"+typeCodes.get(i)+"' ");
				}
			}
			sql.append(")");
		}
	}
	
	
	
	public void conditions(Map<String,Object> request,StringBuilder where) {
		if(checkKey(request, "startarea")) {
			where.append(" AND RA.value>="+request.get("startarea"));
		}
		if(checkKey(request, "endarea")) {
			where.append(" AND RA.value<="+request.get("endarea"));
		}
		if(checkKey(request,"startprice")) {
			where.append( " AND BD.rentprice>="+request.get("startprice"));
		}
		if(checkKey(request, "endprice")) {
			where.append(" AND BD.rentprice<="+request.get("endprice"));
		}
	}
	
	@Override
	public List<BuildingEntity> findAll(Map<String,Object> request,List<String> typeCode) {
		StringBuilder sql=new StringBuilder("SELECT BD.id,BD.name,BD.districtid,BD.street,BD.ward,BD.numberofbasement,BD.floorarea,BD.rentprice,BD.managername,BD.managerphonenumber,"
				+ "BD.servicefee,BD.brokeragefee"
				+ " FROM building BD ");
		joinTable(request,sql);
		StringBuilder where=new StringBuilder("WHERE 1=1");
		conditions(request,where);
		whereTable(request,where);
		typeCode(request,typeCode,where);
		sql.append(where);
		sql.append(" GROUP BY BD.id ");
		//System.out.println();
		System.out.println(sql.toString());
		List<BuildingEntity> arr=new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql.toString())) {
			while(rs.next()) {
				BuildingEntity building=new BuildingEntity();
				building.setId(rs.getInt("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictId(rs.getInt("districtId"));
				building.setNumberofbasement(rs.getInt("numberofbasement"));
				building.setFloorarea(rs.getInt("floorarea"));
				building.setManagername(rs.getString("managername"));
				building.setManagerphonenumber(rs.getString("managerphonenumber"));
				building.setRentprice(rs.getInt("rentprice"));
				building.setServicefee(rs.getString("servicefee"));
				building.setBrokeragefee(rs.getInt("brokeragefee"));
				arr.add(building);
			}
			//System.out.println("Connected database successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
			//System.out.println("Connected database failed...");
		}

		return arr;
	}
	
	
}
