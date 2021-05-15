package com.cg.onlineflatrental.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.cg.onlineflatrental.model.Flat;

@Repository
public interface IFlatJpaDao extends JpaRepository<Flat, Integer>{

}
