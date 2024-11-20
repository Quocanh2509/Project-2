package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
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
//	public static boolean checkKey(BuildingSearchBuilder builder,String key) {
//		if(request.get(key)!=null||!request.get(key).equals("")) {
//			return true;
//		}
//		else return false;
//		
//	}
	
	public void joinTable(BuildingSearchBuilder builder,StringBuilder sql) {
		List<String> typeCode=builder.getTypeCode();
		if(typeCode != null) {
			sql.append(" inner join buildingrenttype BR ON BD.id=BR.buildingid ");
			sql.append(" inner join renttype RT ON RT.id=BR.renttypeid ");
		}
		Integer areaFrom=builder.getAreaFrom();
		Integer areaTo=builder.getAreaTo();
		if( areaFrom != null || areaTo != null) {
			sql.append(" inner join rentarea RA on BD.id=RA.buildingid ");
		}
		Integer staffId=builder.getStaffId();
		if(staffId != null) {
			sql.append(" inner join assignmentbuilding AB on AB.buildingid = BD.id ");
		}
		
	}
	
	
	
	
	public void whereTable(BuildingSearchBuilder builder,StringBuilder where) {
//		for(Map.Entry<String, Object> item:request.entrySet()) {
//			if(checkKey(request,"staffid")) {
//				where.append(" AND EXISTS (SELECT 1 FROM assignmentbuilding AB WHERE AB.buildingid = BD.id ");
//				where.append(" AND AB.staffid = " + request.get("staffid") + ")");
//			}
//			if(checkKey(request, item.getKey())&&!item.getKey().equals("typecode")&&!item.getKey().equals("staffid")&&!item.getKey().equals("areaFrom")&&
//					!item.getKey().equals("areaTo")&&!item.getKey().equals("rentPriceFrom")&&!item.getKey().equals("rentPriceTo")) {
//				String value=item.getValue().toString();
//				if(checkValue(value)) {
//					where.append(" AND BD."+item.getKey()+" = "+value);
//				}
//				else {
//					where.append(" AND BD."+item.getKey()+" LIKE '%"+value+"%'");
//				}
//			}
//		}
		try {
			Field[] fields=BuildingSearchBuilder.class.getDeclaredFields();
			
			for(Field it : fields) {
				it.setAccessible(true);
				String fieldName=it.getName();
		
				if(!fieldName.equals("typeCode")&&!fieldName.equals("areaFrom")&&!fieldName.equals("areaTo")
						&&!fieldName.equals("rentPriceFrom")&&!fieldName.equals("rentPriceTo")&&!fieldName.equals("staffId")) {
					Object value = it.get(builder);
					if(value != null) {
						
						if(it.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND BD."+fieldName+" = "+value);
						}
						else if(it.getType().getName().equals("java.lang.String")) {
							where.append(" AND BD."+fieldName+" LIKE '%"+value+"%'");
						}
					}
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	public void typeCode(BuildingSearchBuilder builder,StringBuilder sql) {
		int count=0;
		
		List<String> typeCodes=builder.getTypeCode();
		if(typeCodes!=null) {
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
	
	
	
	public void conditions(BuildingSearchBuilder builder,StringBuilder where) {
		Integer staffId=builder.getStaffId();
		if(staffId != null) {
			where.append(" AND AB.staffid="+staffId);
		}
		Integer areaFrom=builder.getAreaFrom();
		if(areaFrom!=null) {
			where.append(" AND RA.value>="+areaFrom);
		}
		Integer areaTo=builder.getAreaTo();
		if(areaTo != null) {
			where.append(" AND RA.value<="+areaTo);
		}
		Integer rentPriceFrom=builder.getRentPriceFrom();
		if(rentPriceFrom != null) {
			where.append( " AND BD.rentprice>="+rentPriceFrom);
		}
		Integer rentPriceTo=builder.getRentPriceTo();
		if(rentPriceTo != null) {
			where.append(" AND BD.rentprice<="+rentPriceTo);
		}
	}
	
	
	
	
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
		StringBuilder sql=new StringBuilder("SELECT BD.* FROM building BD ");
		joinTable(builder,sql);
		StringBuilder where=new StringBuilder("WHERE 1=1");
		conditions(builder,where);
		whereTable(builder,where);
		typeCode(builder,where);
		sql.append(where);
		sql.append(" GROUP BY BD.id ");
		//System.out.println();
		System.out.println(sql.toString());
		Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
		return query.getResultList();
		
	}
	
	
}
