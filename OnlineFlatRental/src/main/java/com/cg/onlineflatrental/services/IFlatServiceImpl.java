package com.cg.onlineflatrental.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineflatrental.dao.IFlatJpaDao;
import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
import com.cg.onlineflatrental.model.Flat;

@Service
@Transactional
public class IFlatServiceImpl implements IFlatService  {

	@Autowired
	private IFlatJpaDao iflatjpadao;
	
	String flatIdNotAvailable="flat with given id was not found";
	@Override
	public Flat addFlat(Flat flat) throws InvalidFlatInputException {
		Flat flatEntity;
		if(flat==null)
		{
			flatEntity=null;
		}
		else
		{
		validateFlat(flat);
		flatEntity=iflatjpadao.saveAndFlush(flat);
		}
		return flatEntity;
	}



	@Override
	public Flat updateFlat(Flat flat) throws FlatNotFoundException {
		
		Optional<Flat> optional=iflatjpadao.findById(flat.getFlatId());
		if(optional.isPresent())
		{
		Flat flat1=optional.get();
		flat1.setAvailability(flat.getAvailability());
		flat1.setCost(flat.getCost());
		flat1.setFlatAddress(flat.getFlatAddress());	
		return iflatjpadao.save(flat1);
		}
		else
		{
			 throw new FlatNotFoundException(flatIdNotAvailable);
		}
	}

	@Override
	public boolean deleteFlatById(Integer flatId) throws FlatNotFoundException {
		
		Optional<Flat> flat=iflatjpadao.findById(flatId);
		if(flat.isPresent())
		{
			
			iflatjpadao.deleteById(flatId);
			return true;
			
		}
		else {
			 throw new FlatNotFoundException(flatIdNotAvailable);
		}
		
	}

	@Override
	public Flat viewFlat(Integer flatId) throws FlatNotFoundException {
		
		Optional<Flat> optional=iflatjpadao.findById(flatId);  
		if(optional.isPresent())
		{
			Flat flat=optional.get();
			return flat;
		}
		else
		{
			throw new FlatNotFoundException(flatIdNotAvailable);

		}
		
	}

	@Override
	public List<Flat> viewAllFlat() {
		
		return iflatjpadao.findAll();
	}

	@Override
	public List<Flat> findByCostAndAvailability(float cost, String availability) {
		// TODO Auto-generated method stub
		List<Flat> flist=iflatjpadao.findByCostAndAvailability(cost,availability);
		return flist;
	}
	
	public static boolean validateFlat(Flat flat) throws InvalidFlatInputException {
		
		boolean flag=false;
		if(flat==null)
		{
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
		}
		return flag;
	}

	public static boolean validateFlatCost(float cost) throws InvalidFlatInputException {
		
		boolean flag=false;
		if(cost > 0)
		{
			flag=true;			
		}
		else
		{
			throw new InvalidFlatInputException("Cost cannot be 0 or negative");
		}
		return flag;
	}

	public static boolean validateFlatAvailability(String availability) throws InvalidFlatInputException {

		boolean flag=false;
		if(availability.isEmpty())
		{
			throw new InvalidFlatInputException("Availability cannot be Empty");	
		}
		if(availability.equals("YES") || availability.equals("Yes") || availability.equals("yes") || availability.equals("Y") || availability.equals("y") || availability.equals("NO") ||availability.equals("No") || availability.equals("no") || availability.equals("N") || availability.equals("n") )
		{
			flag=true;
		}
		else
		{
			throw new InvalidFlatInputException("Availability can be only [YES | NO | Yes | No | yes | no | Y | N | y | n");
		}
		return flag;
	}

	public static boolean validateFlatHouseNo(int houseNo) throws InvalidFlatInputException {
	
		boolean flag=false;
		if(houseNo <= 0 || Integer.toString(houseNo).isEmpty() )
		{
			throw new InvalidFlatInputException("House Number cannot be Empty or 0 or Negative");
		}
		else
		{
			flag=true;
		}
			return flag;
	}

	public static boolean validateFlatStreet(String street) throws InvalidFlatInputException {
		
		boolean flag=false;
		if(street.isEmpty())
		{
			throw new InvalidFlatInputException("Street cannot be Empty");
		}
		else if (!street.matches("^[A-Za-z]+$"))
		{
			throw new InvalidFlatInputException("Country cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
		}
		return flag;
	}
	
	public static boolean validateFlatCity(String city) throws InvalidFlatInputException {
		
		boolean flag=false;
		if(city.isEmpty())
		{
			throw new InvalidFlatInputException("City cannot be Empty");
		}
		else if (!city.matches("^[a-zA-Z ]+$"))
		{
			throw new InvalidFlatInputException("Country cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
		}
		return flag;	
	}

	public static boolean validateFlatState(String state) throws InvalidFlatInputException {
		
		boolean flag=false;
		if(state.isEmpty())
		{
			throw new InvalidFlatInputException("State cannot be Empty");
		}
		else if (!state.matches("^[a-zA-Z ]+$"))
		{
			throw new InvalidFlatInputException("Country cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
		}
		return flag;	
	}

	public static boolean validateFlatCountry(String country) throws InvalidFlatInputException {
		
		boolean flag=false;
		if(country.isEmpty())
		{
			throw new InvalidFlatInputException("Country cannot be Empty");
		}
		else if (!country.matches("^[a-zA-Z ]+$"))
		{
			throw new InvalidFlatInputException("Country cannot contain Numbers or Special Characters");
		}
		else
		{
			flag=true;
		}
		return flag;
	}

	public static boolean validateFlatPin(int pin) throws InvalidFlatInputException {
		
		boolean flag=false;
		if(pin<=0)
		{
			throw new InvalidFlatInputException("PinCode cannot be negative");	
		}
		else if(Integer.toString(pin).length() != 6)
		{
			throw new InvalidFlatInputException("PinCode should be of length 6");		
		}
		else if(!Integer.toString(pin).matches("^[0-9]+$"))
		{
			throw new InvalidFlatInputException("PinCode cannot contain Alphabets or Special Characters");	
		}
		else
		{
			flag=true;
		}
		return flag;
	}

}
