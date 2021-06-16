package com.cg.onlineflatrental.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.cg.onlineflatrental.model.FlatAddress;

public class FlatDto {
	private Integer flatId;
	private Float cost;
	private Integer flat_address;
	private String availability;
	
	public FlatDto() {
		super();
	}

	public FlatDto(Integer flatId, Float cost, Integer flat_address, String availability) {
		super();
		this.flatId = flatId;
		this.cost = cost;
		this.flat_address = flat_address;
		this.availability = availability;
	}

	public Integer getFlatId() {
		return flatId;
	}

	public void setFlatId(Integer flatId) {
		this.flatId = flatId;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Integer getFlat_address() {
		return flat_address;
	}

	public void setFlat_address(Integer flat_address) {
		this.flat_address = flat_address;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	

	
	
}
