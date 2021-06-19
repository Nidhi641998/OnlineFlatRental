package com.cg.onlineflatrental.service;
import java.util.List;

import com.cg.onlineflatrental.model.FlatBooking;
import com.cg.onlineflatrental.dto.FlatBookingDto;
import com.cg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;




public interface IFlatBookingService {
	
	public FlatBooking updateFlatBooking(FlatBooking flatBooking) throws FlatBookingNotFoundException;
	public boolean deleteFlatBookingbyId(int bookingNo)throws FlatBookingNotFoundException;
	public FlatBooking viewFlatBooking(int bookingNo) throws FlatBookingNotFoundException;
	public List<FlatBooking> viewAllFlatBooking();
	public FlatBooking addFlatBooking1(FlatBooking flatBooking) throws InvalidFlatInputException;
	public void addFlatBooking2(FlatBookingDto flatBooking);
	

}
