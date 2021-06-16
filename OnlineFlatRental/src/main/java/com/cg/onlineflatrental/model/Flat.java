package com.cg.onlineflatrental.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="FlatInfo")
public class Flat {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer flatId;
	
	@Column
	private Float cost;
	@OneToOne(targetEntity=com.cg.onlineflatrental.model.FlatAddress.class, cascade=CascadeType.ALL)
	
	@JoinColumn(name = "flataddressId",referencedColumnName="addressId")
	private FlatAddress flatAddress;
	private String availability;
//	@OneToOne(targetEntity=com.insurance.quote.entity.BusinessSegment.class, cascade=CascadeType.ALL)
//	@JoinColumn(name="bus_seg_id",referencedColumnName="bus_seg_id")
	
	@Override
	public String toString() {
		return "Flat [flatId=" + flatId + ", cost=" + cost + ", flatAddress= " + flatAddress + ", availability="
				+ availability + "]";
	}

	public Flat()
	{

	}
	public Flat(Integer flatId, Float cost, FlatAddress flatAddress, String availability) {
		super();
		this.flatId = flatId;
		this.cost = cost;
		this.flatAddress = flatAddress;
		this.availability = availability;
	}

	public Integer getFlatId() {
		return flatId;
	}

	
	public Flat(Float cost, FlatAddress flatAddress, String availability) {
		super();
		this.cost = cost;
		this.flatAddress = flatAddress;
		this.availability = availability;
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

	public FlatAddress getFlatAddress() {
		return flatAddress;
	}

	public void setFlatAddress(FlatAddress flatAddress) {
		this.flatAddress = flatAddress;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	
	
}