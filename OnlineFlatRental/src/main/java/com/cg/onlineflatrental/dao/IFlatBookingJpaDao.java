package com.cg.onlineflatrental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cg.onlineflatrental.model.FlatBooking;

@Repository
public interface IFlatBookingJpaDao extends JpaRepository<FlatBooking,Integer>{
	

}
