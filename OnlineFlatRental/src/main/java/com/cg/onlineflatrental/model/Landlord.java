package com.cg.onlineflatrental.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="landlord")
public class Landlord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer landlordId;
	
	@Column
	private String landlordName;
	private Integer landlordAge;
	 @OneToOne(cascade=CascadeType.ALL)
	@JoinColumn //(name = "flatId")
	private Flat flat;
	

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

	public Flat getflat() {
		return flat;
	}

	public void setflatList(Flat flat) {
		this.flat = flat;
	}
	
	public Landlord() {
		super();

	}
	public Landlord(Integer landlordId, String landlordName, Integer landlordAge, Flat flat) {
		super();
		this.landlordId = landlordId;
		this.landlordName = landlordName;
		this.landlordAge = landlordAge;
		this.flat = flat;
	}
	

	@Override
	public String toString() {
		return "Landlord [landlordId=" + landlordId + ", landlordName=" + landlordName + ", landlordAge=" + landlordAge + ", flat="
				+ flat + "]";
	}

	public void setFlat() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}