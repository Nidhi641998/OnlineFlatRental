package com.cg.onlineflatrental.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="FlatInfo")
public class Flat {

	@Id
	private Integer flatId;
	
	@Column
	private float cost;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "addressId")
	private FlatAdress flatAdress;
	private String avialibilty;
	
	
	@Override
	public String toString() {
		return "Flat [flatId=" + flatId + ", cost=" + cost + ", flatAdress=" + flatAdress + ", avialibilty="
				+ avialibilty + "]";
	}

	public Flat()
	{

	}
	public Flat(Integer flatId, float cost, FlatAdress flatAdress, String avialibilty) {
		super();
		this.flatId = flatId;
		this.cost = cost;
		this.flatAdress = flatAdress;
		this.avialibilty = avialibilty;
	}

	public Integer getFlatId() {
		return flatId;
	}

	public void setFlatId(Integer flatId) {
		this.flatId = flatId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public FlatAdress getFlatAdress() {
		return flatAdress;
	}

	public void setFlatAdress(FlatAdress flatAdress) {
		this.flatAdress = flatAdress;
	}

	public String getAvialibilty() {
		return avialibilty;
	}

	public void setAvialibilty(String avialibilty) {
		this.avialibilty = avialibilty;
	}
	
	
	
}


