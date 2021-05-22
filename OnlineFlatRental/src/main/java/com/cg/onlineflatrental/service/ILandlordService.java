package com.cg.onlineflatrental.service;

import java.util.List;

import com.cg.onlineflatrental.exception.LandlordNotFoundException;
import com.cg.onlineflatrental.model.Landlord;


public interface ILandlordService {
	Landlord addLandlord(Landlord landlord);
    Landlord  updateLandlord(Landlord landlord) throws LandlordNotFoundException;
	boolean  deleteLandlordById(int landlordId) throws LandlordNotFoundException;
	Landlord viewLandlordById(int landlordId) throws LandlordNotFoundException;
	List<Landlord> viewAllLandlord();
}
