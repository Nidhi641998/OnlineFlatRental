package com.cg.onlineflatrental.service;

import java.util.List;

import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
import com.cg.onlineflatrental.model.Flat;



public interface IFlatService {
	Flat addFlat(Flat flat) throws InvalidFlatInputException;
	Flat updateFlat(Flat flat) throws FlatNotFoundException, InvalidFlatInputException;
	boolean deleteFlatById(Integer flatId) throws FlatNotFoundException;
	Flat viewFlat(Integer flatId) throws FlatNotFoundException;
	List<Flat> viewAllFlat();
	public List<Flat> findByCostAndAvailability(Float cost,String availability) throws FlatNotFoundException, InvalidFlatInputException;
}

