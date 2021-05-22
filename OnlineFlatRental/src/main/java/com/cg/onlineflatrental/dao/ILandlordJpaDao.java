package com.cg.onlineflatrental.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineflatrental.model.Landlord;

@Repository
public interface ILandlordJpaDao extends JpaRepository<Landlord,Integer>{
  
}
