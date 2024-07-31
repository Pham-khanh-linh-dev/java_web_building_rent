package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Model.buildingDTO;
import com.javaweb.service.buildingService;

@RestController
@PropertySource("classpath:application.properties")
public class buildingAPI {

	@Autowired
	private buildingService buildingSer;
	
	@Value("${linh.dev}")
	private String data;
	
	@GetMapping(value = "/api/building")
	public List<buildingDTO> getbuilding(@RequestParam Map<String, Object> params,@RequestParam(name="typeCode", required = false) List<String> typeCode){
		List<buildingDTO> result = buildingSer.findAll(params, typeCode);
		return result;
	}
}

