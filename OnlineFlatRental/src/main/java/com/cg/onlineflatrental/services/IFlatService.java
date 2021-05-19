package com.cg.onlineflatrental.services;

import java.util.List;

import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
import com.cg.onlineflatrental.model.Flat;



public interface IFlatService {
	Flat addFlat(Flat flat) throws InvalidFlatInputException;
	Flat updateFlat(Flat flat) throws FlatNotFoundException;
	boolean deleteFlatById(Integer flatId) throws FlatNotFoundException;
	Flat viewFlat(Integer flatId) throws FlatNotFoundException;
	List<Flat> viewAllFlat();
	public List<Flat> findByCostAndAvailability(float cost,String availability);
}

