package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class buildingSearchBuilder {

	private String name;
	private String street;
	private String ward;
	private Integer districtid;
	private Integer numberofbasement;
	private Integer floorarea;
	private String level;
	private Integer rentpricefrom;
	private Integer rentpriceto;
	private Integer rentareafrom;
	private Integer rentareato;
	private Long brokeragefee;
	private String managername;
	private String managerphonenumber;
	private Integer staffid;
	private List<String> typeBuilding = new ArrayList();
	private buildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.street = builder.street;
		this.ward = builder.ward;
		this.districtid = builder.districtid;
		this.numberofbasement = builder.numberofbasement;
		this.floorarea = builder.floorarea;
		this.level = builder.level;
		this.rentareafrom = builder.rentareafrom;
		this.rentareato = builder.rentareato;
		this.rentpricefrom = builder.rentpricefrom;
		this.rentpriceto = builder.rentpriceto;
		this.brokeragefee = builder.brokeragefee;
		this.managername = builder.managername;
		this.managerphonenumber = builder.managerphonenumber;
		this.staffid = builder.staffid;
		this.typeBuilding = builder.typeBuilding;
	}
	
	public String getName() {
		return name;
	}
	public String getStreet() {
		return street;
	}
	public String getWard() {
		return ward;
	}
	public Integer getDistrictid() {
		return districtid;
	}
	public Integer getNumberofbasement() {
		return numberofbasement;
	}
	public Integer getFloorarea() {
		return floorarea;
	}
	public String getLevel() {
		return level;
	}
	public Integer getRentpricefrom() {
		return rentpricefrom;
	}
	public Integer getRentpriceto() {
		return rentpriceto;
	}
	public Integer getRentareafrom() {
		return rentareafrom;
	}
	public Integer getRentareato() {
		return rentareato;
	}
	public Long getBrokeragefee() {
		return brokeragefee;
	}
	public String getManagername() {
		return managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public Integer getStaffid() {
		return staffid;
	}
	public List<String> getTypeBuilding() {
		return typeBuilding;
	}
	
	
//	builder
	public class Builder{
		private String name;
		private String street;
		private String ward;
		private Integer districtid;
		private Integer numberofbasement;
		private Integer floorarea;
		private String level;
		private Integer rentpricefrom;
		private Integer rentpriceto;
		private Integer rentareafrom;
		private Integer rentareato;
		private Long brokeragefee;
		private String managername;
		private String managerphonenumber;
		private Integer staffid;
		private List<String> typeBuilding = new ArrayList();
		public void setName(String name) {
			this.name = name;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public void setWard(String ward) {
			this.ward = ward;
		}
		public void setDistrictid(Integer districtid) {
			this.districtid = districtid;
		}
		public void setNumberofbasement(Integer numberofbasement) {
			this.numberofbasement = numberofbasement;
		}
		public void setFloorarea(Integer floorarea) {
			this.floorarea = floorarea;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public void setRentpricefrom(Integer rentpricefrom) {
			this.rentpricefrom = rentpricefrom;
		}
		public void setRentpriceto(Integer rentpriceto) {
			this.rentpriceto = rentpriceto;
		}
		public void setRentareafrom(Integer rentareafrom) {
			this.rentareafrom = rentareafrom;
		}
		public void setRentareato(Integer rentareato) {
			this.rentareato = rentareato;
		}
		public void setBrokeragefee(Long brokeragefee) {
			this.brokeragefee = brokeragefee;
		}
		public void setManagername(String managername) {
			this.managername = managername;
		}
		public void setManagerphonenumber(String managerphonenumber) {
			this.managerphonenumber = managerphonenumber;
		}
		public void setStaffid(Integer staffid) {
			this.staffid = staffid;
		}
		public void setTypeBuilding(List<String> typeBuilding) {
			this.typeBuilding = typeBuilding;
		}
		
		public buildingSearchBuilder build() {
			return new buildingSearchBuilder(this);
		}
	}
	
}
