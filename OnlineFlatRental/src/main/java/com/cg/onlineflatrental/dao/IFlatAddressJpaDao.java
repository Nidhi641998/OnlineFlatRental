package com.cg.onlineflatrental.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineflatrental.model.FlatAddress;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface IFlatAddressJpaDao extends JpaRepository<FlatAddress, Integer> {

}
