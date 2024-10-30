package com.javaweb.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;import java.util.concurrent.ConcurrentHashMap.KeySetView;

import javax.swing.plaf.basic.BasicComboBoxUI.ListDataHandler;

import org.springframework.web.bind.annotation.RequestBody;

import com.javaweb.Beans.BuildingDTO;
//import com.javaweb.Beans.BuildingDTO;
import com.javaweb.Beans.ErrorReponseDTO;
import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.Beans.response.RentareaResponseDTO;
import com.javaweb.customexception.FileRequireException;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentareaEntity;
import com.javaweb.service.BuildingService;

import ch.qos.logback.core.joran.action.NewRuleAction;

@RestController
public class BuildingsAPI {

	
	@Autowired
	public BuildingService buildingservice;
	
	
	@GetMapping(value="/api")
	public void in(@RequestParam Map<String,Object> id) {
		System.out.print("success");
	}
	
	
	@GetMapping(value = "/api/buildings")
	public Object postBuilding2(@RequestParam Map<String,Object> request,@RequestParam(required = false) List<String> typecode) {
		List<BuildingResponseDTO> result=buildingservice.findAll(request,typecode);
		return result;
	}

	// @RequestMapping(value = "/api/building/", method = RequestMethod.GET)
	public void validate(BuildingDTO buildingDTO) throws FileRequireException {
		if (buildingDTO.getName() == null || buildingDTO.getName().equals("")) {
			throw new FileRequireException("dữ liệu không hợp lệ");
		}
		// return buildingDTO;
	}

//	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
//	public Object getBuilding(BuildingDTO buildingDTO) {
//		validate(buildingDTO);
//		return buildingDTO;
//	}
//	
}
