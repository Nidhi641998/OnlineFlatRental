package com.cg.onlineflatrental.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.transaction.Transactional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineflatrental.dao.ILandlordJpaDao;
import com.cg.onlineflatrental.exception.InvalidLandlordInputException;
import com.cg.onlineflatrental.exception.LandlordNotFoundException;
import com.cg.onlineflatrental.model.Landlord;

@Service
@Transactional
public class ILandlordServiceImpl implements ILandlordService {
	private static final Logger logger = LoggerFactory.getLogger(ILandlordServiceImpl.class);
	@Autowired
	private ILandlordJpaDao ilandlordjpadao;
	
	String landlordIdNotAvailable="landlord with given id was not found";

	@Override
	public Landlord addLandlord(Landlord landlord) throws InvalidLandlordInputException{
		Landlord landlordEntity;
		logger.info("===In Get Service===");
		logger.info("addLandlord() service is initiated");
		if(landlord==null)
		{
			logger.error("landlord details cannot be null");
			throw new InvalidLandlordInputException("landlord details cannot be null");
		}
		else
		{
		
			validateLandLord(landlord);
			landlordEntity=ilandlordjpadao.saveAndFlush(landlord);
		
		}
		logger.info("addLandlord() service has executed");
		return landlordEntity;
	}


	@Override
	public Landlord updateLandlord(Landlord landlord) throws LandlordNotFoundException,InvalidLandlordInputException {
		logger.info("===In Put Service===");
		logger.info("updateLandlord() service is initiated");
		
		Optional<Landlord> optional=ilandlordjpadao.findById(landlord.getLandlordId());
		if(optional.isPresent())
		{
			validateLandLord(landlord);
			Landlord landlord1=optional.get();	
		landlord1.setLandlordName(landlord.getLandlordName());
		landlord1.setLandlordAge(landlord.getLandlordAge());
		landlord1.setFlat(landlord.getFlat());
		
		return ilandlordjpadao.save(landlord1);
		}
		else
		{
			logger.error("landlord with given id was not found");
			 throw new LandlordNotFoundException(landlordIdNotAvailable);
		}


	}

	@Override
	public  boolean deleteLandlordById(int landlordId) throws LandlordNotFoundException {
		logger.info("===In Delete Service===");
		logger.info("deleteLandlordById() service is initiated");
		
		Optional<Landlord> flat=ilandlordjpadao.findById(landlordId);
		if(flat.isPresent())
		{
			
			ilandlordjpadao.deleteById(landlordId);
			logger.info("deleteLandlordById() service has executed");
			return true;
			
		}
		else {
			logger.error("landlord with given id was not found");
			 throw new LandlordNotFoundException(landlordIdNotAvailable);
		}
	}

	@Override
	public Landlord  viewLandlordById(int landlordId) throws LandlordNotFoundException {
		logger.info("===In Get Service===");
		logger.info("viewLandlordById() service is initiated");
		
		Optional<Landlord> optional=ilandlordjpadao.findById(landlordId);  
		if(optional.isPresent())
		{
			Landlord flat=optional.get();
			logger.info("viewLandlordById() service has executed");
			return flat;
		}
		else
		{
			logger.error("landlord with given id was not found");
			throw new LandlordNotFoundException(landlordIdNotAvailable);
		}

	}

		

	@Override
	public List<Landlord>viewAllLandlord() {
		return ilandlordjpadao.findAll();
	}
	
	public static boolean validateLandLord(Landlord landlord) throws InvalidLandlordInputException {
		logger.info("validateLandLord() is initiated");	
		boolean flag=false;
		if(landlord.getFlat().getFlatAddress()==null || landlord.getFlat().getAvailability()==null )
		{
			logger.error("landlord details cannot be null");
			throw new InvalidLandlordInputException("Flat details cannot be null");
		}
		else
		{
			validateFlatCost(landlord.getFlat().getCost());
			validateFlatHouseNo(landlord.getFlat().getFlatAddress().getHouseNo());
			validateFlatStreet(landlord.getFlat().getFlatAddress().getStreet());
			validateFlatCity(landlord.getFlat().getFlatAddress().getCity());
			validateFlatState(landlord.getFlat().getFlatAddress().getState());
			validateFlatPin(landlord.getFlat().getFlatAddress().getPin());
			validateFlatCountry(landlord.getFlat().getFlatAddress().getCountry());
			validateFlatAvailability(landlord.getFlat().getAvailability());
			flag=true;
			logger.info("Validation Successful");	
		}
		logger.info("validateLandLord() has executed");	
		return flag;
	}

	public static boolean validateFlatCost(Float cost) throws InvalidLandlordInputException {
		logger.info("validateFlatCost() is initiated");	
		boolean flag=false;
		if(cost > 0)
		{
			flag=true;
			logger.info("Validation Successful");
		}
		else
		{
			logger.error("Cost cannot be empty or 0 or negative");
			throw new InvalidLandlordInputException("Cost cannot be empty or 0 or negative");
		}
		logger.info("validateFlatCost() has executed");	
		return flag;
	}

	public static boolean validateFlatAvailability(String availability) throws InvalidLandlordInputException {
		logger.info("validateFlatAvailability() is initiated");	
		boolean flag=false;
		if(availability.isEmpty())
		{
			logger.error("Availability cannot be Empty");
			throw new InvalidLandlordInputException("Availability cannot be Empty");	
		}
		if(availability.equals("YES") || availability.equals("Yes") || availability.equals("yes") || availability.equals("Y") || availability.equals("y") || availability.equals("NO") ||availability.equals("No") || availability.equals("no") || availability.equals("N") || availability.equals("n") )
		{
			flag=true;
			logger.info("Validation Successful");
		}
		else
		{
			logger.error("Availability can be only [YES | NO | Yes | No | yes | no | Y | N | y | n]");
			throw new InvalidLandlordInputException("Availability can be only [YES | NO | Yes | No | yes | no | Y | N | y | n]");
		}
		logger.info("validateFlatAvailability() has executed");	
		return flag;
	}

	public static boolean validateFlatHouseNo(Integer houseNo) throws InvalidLandlordInputException {
		logger.info("validateFlatHouseNo() is initiated");	
		boolean flag=false;
		if(houseNo <= 0 || Integer.toString(houseNo).isEmpty() )
		{
			logger.error("House Number cannot be Empty or 0 or Negative");
			throw new InvalidLandlordInputException("House Number cannot be Empty or 0 or Negative");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
			logger.info("validateFlatAvailability() has executed");	
			return flag;
	}

	public static boolean validateFlatStreet(String street) throws InvalidLandlordInputException {
		logger.info("validateFlatStreet() is initiated");	
		boolean flag=false;
		if(street.isEmpty())
		{
			logger.error("Street cannot be Empty");
			throw new InvalidLandlordInputException("Street cannot be Empty");
		}
		else if (!street.matches("^[A-Za-z\\s]+$"))
		{
			logger.error("Street cannot contain Numbers or Special Characters");
			throw new InvalidLandlordInputException("Street cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
		logger.info("validateFlatStreet() has executed");	
		return flag;
	}
	
	public static boolean validateFlatCity(String city) throws InvalidLandlordInputException {
		logger.info("validateFlatCity() is initiated");	
		boolean flag=false;
		if(city.isEmpty())
		{
			logger.error("City cannot be Empty");
			throw new InvalidLandlordInputException("City cannot be Empty");
		}
		else if (!city.matches("^[a-zA-Z \\s]+$"))
		{
			logger.error("City cannot contain Numbers or Special Characters");
			throw new InvalidLandlordInputException("City cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
		logger.info("validateFlatCity() has executed");	
		return flag;	
	}

	public static boolean validateFlatState(String state) throws InvalidLandlordInputException {
		logger.info("validateFlatState() is initiated");	
		boolean flag=false;
		if(state.isEmpty())
		{
			logger.error("State cannot be Empty");
			throw new InvalidLandlordInputException("State cannot be Empty");
		}
		else if (!state.matches("^[a-zA-Z \\s]+$"))
		{
			logger.error("State cannot contain Numbers or Special Characters");
			throw new InvalidLandlordInputException("State cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
		logger.info("validateFlatState() has executed");	
		return flag;	
	}

	public static boolean validateFlatCountry(String country) throws InvalidLandlordInputException {
		logger.info("validateFlatCountry() is initiated");	
		boolean flag=false;
		if(country.isEmpty())
		{
			logger.error("Country cannot be Empty");
			throw new InvalidLandlordInputException("Country cannot be Empty");
		}
		else if (!country.matches("^[a-zA-Z \\s]+$"))
		{
			logger.error("Country cannot contain Numbers or Special Characters");
			throw new InvalidLandlordInputException("Country cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
		logger.info("validateFlatCountry() has executed");	
		return flag;
	}

	public static boolean validateFlatPin(Integer pin) throws InvalidLandlordInputException {
		logger.info("validateFlatPin() is initiated");	
		boolean flag=false;
		if(pin<=0)
		{
			logger.error("PinCode cannot be negative");
			throw new InvalidLandlordInputException("PinCode cannot be negative");	
		}
		else if(Integer.toString(pin).length() != 6)
		{
			logger.error("PinCode should be of length 6");
			throw new InvalidLandlordInputException("PinCode should be of length 6");		
		}
		else if(!Integer.toString(pin).matches("^[0-9]+$"))
		{
			logger.error("PinCode cannot contain Alphabets or Special Characters");
			throw new InvalidLandlordInputException("PinCode cannot contain Alphabets or Special Characters");	
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
		logger.info("validateFlatPin() has executed");	
		return flag;
	}

	

}