package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.Model.BuildingRequestDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@Transactional
@RestController
@PropertySource("classpath:application.properties")
public class BuildingAPI {

	@Autowired
	private BuildingService buildingSer;
	
	@Value("${linh.dev}")
	private String data;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping(value = "/api/building")
	public List<BuildingDTO> getbuilding(@RequestParam Map<String, Object> params,@RequestParam(name="typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> result = buildingSer.findAll(params, typeCode);
		return result;
	}
	
	@PostMapping(value = "/api/building/")
	
	public void createBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName(buildingRequestDTO.getName());
		buildingEntity.setWard(buildingRequestDTO.getWard());
		buildingEntity.setRentprice(buildingRequestDTO.getRentPrice() != null ? buildingRequestDTO.getRentPrice().intValue() : null);
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictId());
		buildingEntity.setDistrict(districtEntity);
		
		entityManager.persist(buildingEntity);
		
		System.out.print("OK");
		
	}
	@DeleteMapping(value = "/api/building/{id}")
	public void deleteBuilding(@PathVariable Integer id) {
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
		entityManager.remove(buildingEntity);
		System.out.print(data);
	}
}

