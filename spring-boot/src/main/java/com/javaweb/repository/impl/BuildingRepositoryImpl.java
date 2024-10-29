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
	
	
	
	public static boolean checkvalue(String s) {
		try {
			Integer num=Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			return false;// TODO: handle exception
		}
		return true;
	}
	
	
	public static boolean checkKey(Map<String,Object> request,String key) {
		if(request.containsKey(key)&&request.get(key)!=null && request.get(key)!=null) {
			return true;
		}
		else return false;
	}
	
	public static Integer choose(Map<String,Object> request,String start,String end) {
		if(checkKey(request,start)&&checkKey(request,end)) {
			return 0;// ton tai 2 key
		}
		else if(checkKey(request,start)) {
			return 1;// ton tai 1 key ben trai
		}
		else if(checkKey(request,end)) {
			return 2;// ton tai 1 key ben phai
		}
		return 3;
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
			if(!item.getKey().equals("typecode")&&!item.getKey().equals("staffid")&&!item.getKey().equals("startarea")&&
					!item.getKey().equals("endarea")&&!item.getKey().equals("startprice")&&!item.getKey().equals("endprice")) {
				String value=item.getValue().toString();
				if(checkvalue(value)) {
					where.append(" AND BD."+item.getKey()+" = "+value);
				}
				else {
					where.append(" AND BD."+item.getKey()+" LIKE '%"+value+"%'");
				}
			}
		}
	}
	
	
	
	public void Typecode(Map<String,Object> request,List<String> typecode,StringBuilder sql) {
		int count=0;
		if(typecode==null) {
			return;
		}
		if(!typecode.isEmpty()) {
			for(int i=0;i<typecode.size();i++) {
				if(count==0) {
					sql.append(" AND (RT.code='"+typecode.get(i)+"' ");
					count++;
				}
				else {
					sql.append(" OR RT.code='"+typecode.get(i)+"' ");
				}
			}
			sql.append(")");
		}
	}
	
	@ResponseBody
	public void conditions(Map<String,Object> request,StringBuilder where) {
		Integer num = choose(request,"startarea","endarea");
		if(num==0) {
			where.append(" AND (RA.value>="+request.get("startarea")+" and RA.value<="+request.get("endarea")+")");
		}
		else if(num==1) {
			where.append(" AND RA.value>="+request.get("startarea"));
		}
		else if(num==2) {
			where.append(" AND RA.value<="+request.get("endarea"));
		}
		Integer num2= choose(request,"startprice","endprice");
		if(num2==0) {
			where.append(" AND BD.rentprice>="+request.get("startprice")+" AND BD.rentprice<="+request.get("endprice"));
		}
		else if(num2==1) {
			where.append( " AND BD.rentprice>="+request.get("startprice"));
		}
		else if(num2==2) {
			where.append(" AND BD.rentprice<="+request.get("endprice"));
		}
	}
	
	public void conditions2(Map<String,Object> request,StringBuilder where) {
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
	public List<BuildingEntity> findAll(Map<String,Object> request,List<String> typecode) {
		StringBuilder sql=new StringBuilder("SELECT BD.id,BD.name,BD.districtid,BD.street,BD.ward,BD.numberofbasement,BD.floorarea,BD.rentprice,BD.managername,BD.managerphonenumber,"
				+ "BD.servicefee,BD.brokeragefee"
				+ " FROM building BD ");
		joinTable(request,sql);
		StringBuilder where=new StringBuilder("WHERE 1=1");
		conditions2(request,where);
		whereTable(request,where);
		Typecode(request,typecode,where);
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
