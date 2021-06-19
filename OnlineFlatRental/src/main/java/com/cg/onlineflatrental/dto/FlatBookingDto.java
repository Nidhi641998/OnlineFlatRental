package com.cg.onlineflatrental.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.Tenant;



public class FlatBookingDto {
	
	private int	bookingNo;
	
	private Integer flat;
	
	private Integer tenant;
	
	private LocalDate bookingFromDate;
	private LocalDate bookingToDate;
	public FlatBookingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FlatBookingDto(int bookingNo, Integer flat, Integer tenant, LocalDate bookingFromDate,
			LocalDate bookingToDate) {
		super();
		this.bookingNo = bookingNo;
		this.flat = flat;
		this.tenant = tenant;
		this.bookingFromDate = bookingFromDate;
		this.bookingToDate = bookingToDate;
	}
	public int getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(int bookingNo) {
		this.bookingNo = bookingNo;
	}
	public Integer getFlat() {
		return flat;
	}
	public void setFlat(Integer flat) {
		this.flat = flat;
	}
	public Integer getTenant() {
		return tenant;
	}
	public void setTenant(Integer tenant) {
		this.tenant = tenant;
	}
	public LocalDate getBookingFromDate() {
		return bookingFromDate;
	}
	public void setBookingFromDate(LocalDate bookingFromDate) {
		this.bookingFromDate = bookingFromDate;
	}
	public LocalDate getBookingToDate() {
		return bookingToDate;
	}
	public void setBookingToDate(LocalDate bookingToDate) {
		this.bookingToDate = bookingToDate;
	}
	@Override
	public String toString() {
		return "FlatBookingDto [bookingNo=" + bookingNo + ", flat=" + flat + ", tenant=" + tenant + ", bookingFromDate="
				+ bookingFromDate + ", bookingToDate=" + bookingToDate + "]";
	}
	
	
	
	
	
	
	
	

}
