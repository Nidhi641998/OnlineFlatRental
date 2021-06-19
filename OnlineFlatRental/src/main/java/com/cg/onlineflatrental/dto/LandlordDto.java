package com.cg.onlineflatrental.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.cg.onlineflatrental.model.Flat;

public class LandlordDto {
	
	private Integer landlordId;
	private String landlordName;
	private Integer landlordAge;
	private Integer flat;
	public LandlordDto(Integer landlordId, String landlordName, Integer landlordAge, Integer flat) {
		super();
		this.landlordId = landlordId;
		this.landlordName = landlordName;
		this.landlordAge = landlordAge;
		this.flat = flat;
	}
	public LandlordDto() {
		super();
	}
	public Integer getLandlordId() {
		return landlordId;
	}
	public void setLandlordId(Integer landlordId) {
		this.landlordId = landlordId;
	}
	public String getLandlordName() {
		return landlordName;
	}
	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}
	public Integer getLandlordAge() {
		return landlordAge;
	}
	public void setLandlordAge(Integer landlordAge) {
		this.landlordAge = landlordAge;
	}
	public Integer getFlat() {
		return flat;
	}
	public void setFlat(Integer flat) {
		this.flat = flat;
	}
	
	
}
