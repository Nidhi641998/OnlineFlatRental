package com.cg.onlineflatrental.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineflatrental.dao.IFlatAddressJpaDao;
import com.cg.onlineflatrental.dao.IFlatJpaDao;
import com.cg.onlineflatrental.dto.FlatDto;
import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;

@Service
@Transactional
public class IFlatServiceImpl implements IFlatService  {

	private static final Logger logger = LoggerFactory.getLogger(IFlatServiceImpl.class);
	
	@Autowired
	private IFlatJpaDao iflatjpadao;
	
	@Autowired
	private IFlatAddressJpaDao iflataddressjpadao;
	
	String flatIdNotAvailable="flat with given id was not found";
	@Override
	public Flat addFlat(Flat flat) throws InvalidFlatInputException {
		Flat flatEntity;
		logger.info("===In Get Service===");
		logger.info("addFlat() service is initiated");
		if(flat==null)
		{
			logger.error("Flat details cannot be null");
			throw new InvalidFlatInputException("Flat details cannot be null");
		}
		else
		{
		validateFlat(flat);
		flatEntity=iflatjpadao.saveAndFlush(flat);
		logger.info("addFlat() service has executed");
		return flatEntity;
		}
		
	}



	@Override
	public Flat updateFlat(Flat flat) throws FlatNotFoundException, InvalidFlatInputException {
		logger.info("===In Put Service===");
		logger.info("updateFlat() service is initiated");
		Optional<Flat> optional=iflatjpadao.findById(flat.getFlatId());
		if(optional.isPresent())
		{
		validateFlat(flat);
		Flat flat1=optional.get();
		flat1.setAvailability(flat.getAvailability());
		flat1.setCost(flat.getCost());
		flat1.setFlatAddress(flat.getFlatAddress());
		logger.info("updateFlat() service has executed");
		return iflatjpadao.save(flat1);
		}
		else
		{
			logger.error("flat with given id was not found");
			throw new FlatNotFoundException(flatIdNotAvailable);
		}
	}

	@Override
	public boolean deleteFlatById(Integer flatId) throws FlatNotFoundException {
		logger.info("===In Delete Service===");
		logger.info("deleteFlatById() service is initiated");
		Optional<Flat> flat=iflatjpadao.findById(flatId);
		if(flat.isPresent())
		{
			iflatjpadao.deleteById(flatId);
			logger.info("deleteFlatById() service has executed");
			return true;
			
		}
		else {
			logger.error("flat with given id was not found");
			 throw new FlatNotFoundException(flatIdNotAvailable);
		}
		
	}

	@Override
	public Flat viewFlat(Integer flatId) throws FlatNotFoundException {
		logger.info("===In Get Service===");
		logger.info("viewFlat() service is initiated");
		Optional<Flat> optional=iflatjpadao.findById(flatId);  
		if(optional.isPresent())
		{
			Flat flat=optional.get();
			logger.info("viewFlat() service has executed");
			return flat;
		}
		else
		{
			logger.error("flat with given id was not found");
			throw new FlatNotFoundException(flatIdNotAvailable);

		}
		
	}

	@Override
	public List<Flat> viewAllFlat() {
		logger.info("===In Get Service===");
		logger.info("viewAllFlat() service is initiated");
		logger.info("viewAllFlat() service has executed");
		return iflatjpadao.findAll();
	}

	@Override
	public List<Flat> findByCostAndAvailability(Float cost, String availability) throws FlatNotFoundException, InvalidFlatInputException {
		// TODO Auto-generated method stub
		logger.info("===In Get Service===");
		logger.info(" findByCostAndAvailability service is initiated");	
		List<Flat> flatlist = null;
		if(validateFlatCost(cost) && validateFlatAvailability(availability))
			{
			flatlist=iflatjpadao.findByCostAndAvailability(cost,availability);
			
			if(flatlist.isEmpty())
			{
			logger.error("No Flat available for given cost");
			throw new FlatNotFoundException("No Flat available for given cost");
			}
			}
		logger.info("viewAllFlat() service has executed");
		return flatlist;
	}
	
	public void addFlat1(FlatDto flat) {
		
		Flat flat1 = new Flat();
		flat1.setFlatId(flat.getFlatId());
		flat1.setAvailability(flat.getAvailability());
		flat1.setCost(flat.getCost());
		// Business segment object
		
		FlatAddress flatAddress = findByAddressId(flat.getFlat_address());
		//System.out.println("busi segment = "+segment.getBus_seg_id()+" "+segment.getBus_seg_name()); 
		flat1.setFlatAddress(flatAddress); 
		iflatjpadao.save(flat1);	
		
		
	}
	
	
	public FlatAddress findByAddressId(Integer addressId) {
		FlatAddress address = iflataddressjpadao.findById(addressId).get();
		return address;
	}

	
	public static boolean validateFlat(Flat flat) throws InvalidFlatInputException {
		logger.info("validateFlat() is initiated");	
		boolean flag=false;
		System.out.println(flat);
		if(flat.getFlatAddress()==null || flat.getAvailability()==null )
		{
			logger.error("Flat details cannot be null");
			throw new InvalidFlatInputException("Flat details cannot be null");
		}
		else
		{
			validateFlatCost(flat.getCost());
			validateFlatHouseNo(flat.getFlatAddress().getHouseNo());
			validateFlatStreet(flat.getFlatAddress().getStreet());
			validateFlatCity(flat.getFlatAddress().getCity());
			validateFlatState(flat.getFlatAddress().getState());
			validateFlatPin(flat.getFlatAddress().getPin());
			validateFlatCountry(flat.getFlatAddress().getCountry());
			validateFlatAvailability(flat.getAvailability());
			flag=true;
			logger.info("Validation Successful");	
		}
		logger.info("validateFlat() has executed");	
		return flag;
	}

	public static boolean validateFlatCost(Float cost) throws InvalidFlatInputException {
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
			throw new InvalidFlatInputException("Cost cannot be empty or 0 or negative");
		}
		logger.info("validateFlatCost() has executed");	
		return flag;
	}

	public static boolean validateFlatAvailability(String availability) throws InvalidFlatInputException {
		logger.info("validateFlatAvailability() is initiated");	
		boolean flag=false;
		if(availability.isEmpty())
		{
			logger.error("Availability cannot be Empty");
			throw new InvalidFlatInputException("Availability cannot be Empty");	
		}
		if(availability.equals("YES") || availability.equals("Yes") || availability.equals("yes") || availability.equals("Y") || availability.equals("y") || availability.equals("NO") ||availability.equals("No") || availability.equals("no") || availability.equals("N") || availability.equals("n") )
		{
			flag=true;
			logger.info("Validation Successful");
		}
		else
		{
			logger.error("Availability can be only [YES | NO | Yes | No | yes | no | Y | N | y | n]");
			throw new InvalidFlatInputException("Availability can be only [YES | NO | Yes | No | yes | no | Y | N | y | n]");
		}
		logger.info("validateFlatAvailability() has executed");	
		return flag;
	}

	public static boolean validateFlatHouseNo(Integer houseNo) throws InvalidFlatInputException {
		logger.info("validateFlatHouseNo() is initiated");	
		boolean flag=false;
		if(houseNo <= 0 || Integer.toString(houseNo).isEmpty() )
		{
			logger.error("House Number cannot be Empty or 0 or Negative");
			throw new InvalidFlatInputException("House Number cannot be Empty or 0 or Negative");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
			logger.info("validateFlatAvailability() has executed");	
			return flag;
	}

	public static boolean validateFlatStreet(String street) throws InvalidFlatInputException {
		logger.info("validateFlatStreet() is initiated");	
		boolean flag=false;
		if(street.isEmpty())
		{
			logger.error("Street cannot be Empty");
			throw new InvalidFlatInputException("Street cannot be Empty");
		}
		else if (!street.matches("^[A-Za-z\\s]+$"))
		{
			logger.error("Street cannot contain Numbers or Special Characters");
			throw new InvalidFlatInputException("Street cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
		logger.info("validateFlatStreet() has executed");	
		return flag;
	}
	
	public static boolean validateFlatCity(String city) throws InvalidFlatInputException {
		logger.info("validateFlatCity() is initiated");	
		boolean flag=false;
		if(city.isEmpty())
		{
			logger.error("City cannot be Empty");
			throw new InvalidFlatInputException("City cannot be Empty");
		}
		else if (!city.matches("^[a-zA-Z \\s]+$"))
		{
			logger.error("City cannot contain Numbers or Special Characters");
			throw new InvalidFlatInputException("City cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
		logger.info("validateFlatCity() has executed");	
		return flag;	
	}

	public static boolean validateFlatState(String state) throws InvalidFlatInputException {
		logger.info("validateFlatState() is initiated");	
		boolean flag=false;
		if(state.isEmpty())
		{
			logger.error("State cannot be Empty");
			throw new InvalidFlatInputException("State cannot be Empty");
		}
		else if (!state.matches("^[a-zA-Z \\s]+$"))
		{
			logger.error("State cannot contain Numbers or Special Characters");
			throw new InvalidFlatInputException("State cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
		logger.info("validateFlatState() has executed");	
		return flag;	
	}

	public static boolean validateFlatCountry(String country) throws InvalidFlatInputException {
		logger.info("validateFlatCountry() is initiated");	
		boolean flag=false;
		if(country.isEmpty())
		{
			logger.error("Country cannot be Empty");
			throw new InvalidFlatInputException("Country cannot be Empty");
		}
		else if (!country.matches("^[a-zA-Z \\s]+$"))
		{
			logger.error("Country cannot contain Numbers or Special Characters");
			throw new InvalidFlatInputException("Country cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
			logger.info("Validation Successful");
		}
		logger.info("validateFlatCountry() has executed");	
		return flag;
	}

	public static boolean validateFlatPin(Integer pin) throws InvalidFlatInputException {
		logger.info("validateFlatPin() is initiated");	
		boolean flag=false;
		if(pin<=0)
		{
			logger.error("PinCode cannot be negative");
			throw new InvalidFlatInputException("PinCode cannot be negative");	
		}
		else if(Integer.toString(pin).length() != 6)
		{
			logger.error("PinCode should be of length 6");
			throw new InvalidFlatInputException("PinCode should be of length 6");		
		}
		else if(!Integer.toString(pin).matches("^[0-9]+$"))
		{
			logger.error("PinCode cannot contain Alphabets or Special Characters");
			throw new InvalidFlatInputException("PinCode cannot contain Alphabets or Special Characters");	
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
