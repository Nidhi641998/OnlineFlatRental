package com.cg.onlineflatrental.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.cg.onlineflatrental.model.Flat;

@Repository
public interface IFlatJpaDao extends JpaRepository<Flat, Integer>{
	@Query("select f from Flat f where f.cost < ?1 AND f.availability = ?2")
	List<Flat> findByCostAndAvailability(float cost, String availability);
	
	//@Query("select f from Flat f where f.flatCost = ?1 and f.flatAvailability = ?2")
	
}
