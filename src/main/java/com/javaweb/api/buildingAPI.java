package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Model.buildingDTO;
import com.javaweb.service.buildingService;

@RestController
public class buildingAPI {

	@Autowired
	private buildingService buildingSer;
	@GetMapping(value = "/api/building")
	public List<buildingDTO> getbuilding(@RequestParam Map<String, Object> params,@RequestParam List<String> typeCode){
		List<buildingDTO> result = buildingSer.findAll(params, typeCode);
		return result;
	}
}
