package com.cg.onlineflatrental.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineflatrental.controller.ITenantController;
import com.cg.onlineflatrental.dao.IFlatAddressJpaDao;
import com.cg.onlineflatrental.dao.ITenantDao;
import com.cg.onlineflatrental.dto.TenantDto;
import com.cg.onlineflatrental.exception.TenantNotFoundException;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.model.Tenant;


@Service
@Transactional
public class ITenantServiceImpl implements ITenantService {
	static final Logger LOGGER = LoggerFactory.getLogger(ITenantServiceImpl.class);

	@Autowired
	private ITenantDao itenantdao;
    @Autowired
    private IFlatAddressJpaDao iflataddressjpadao;
	String TenantIdNotFound="Tenant with given id was not found";

	@Override
	public Tenant addTenant(Tenant tenant)throws TenantNotFoundException {
		
		LOGGER.info("addTenant() service is initiated");
		validateTenant(tenant);
		LOGGER.info("addTenant() service has executed");
		return itenantdao.save(tenant);
	}

	@Override 
	public List<Tenant> viewAllTenants() {
		
		LOGGER.info("viewAllTenant() service is initiated");
		LOGGER.info("viewAllTenant() service has executed");
		return itenantdao.findAll();
	}
	@Override
	public Tenant viewTenantById(int tenantId)throws TenantNotFoundException {
		
		LOGGER.info("viewTenant() service is initiated");
		Optional<Tenant> optional= itenantdao.findById(tenantId);
		if(optional.isPresent())
		{

			Tenant tenant=optional.get();
			LOGGER.info("viewTenant() service has executed");
			return tenant;

		}
		else
		{
			throw new TenantNotFoundException("Tenant ID not found");
		}
	}

	@Override
	public Tenant updateTenant(Tenant tenant)throws TenantNotFoundException {
		LOGGER.info("updateTenant() service is initiated");
		Optional<Tenant> optional= itenantdao.findById(tenant.getTenantId());
		if(optional.isPresent())
		{
			validateTenant(tenant);
			LOGGER.info("updateTenant() service has executed");
			return itenantdao.save(tenant);

		}
		else
			throw new TenantNotFoundException("Tenant ID not found");
	}


	

	@Override
	public Boolean deleteTenant(int tenantId)throws TenantNotFoundException {
		LOGGER.info("deleteTenant() service is initiated");
		Optional<Tenant> optional=itenantdao.findById(tenantId);
		itenantdao.deleteById(tenantId);

		if(optional.isPresent())
		{
			LOGGER.info("deleteTenant() service has executed");
			return true;
		}

		throw new TenantNotFoundException("Tenant ID not found");

	}
	@Override
	public Tenant validateTenant(int tenantId) {
		return null;
	}


	
	/*@Override
	public Tenant validateTenant(int tenantId)throws TenantNotFoundException {
		Tenant tenant=itenantdao.validateTenant(tenantId);
		if(tenant!=null)
		{
			return tenant;
		}
		else
		{
			throw new TenantNotFoundException("Failed to validate tenant name");
		}
	}*/
	@Override
	public void addTenant1(TenantDto tenant) {
	       
        Tenant tenant1 = new Tenant();
        tenant1.setTenantId(tenant.getTenantId());
        tenant1.setTenantName(tenant.getTenantName());
        tenant1.setTenantAge(tenant.getTenantAge());
        // Business segment object
       
        FlatAddress flatAddress = findByAddressId(tenant.getFlat_address());
        //System.out.println("busi segment = "+segment.getBus_seg_id()+" "+segment.getBus_seg_name());
        tenant1.setFlatAddress(flatAddress);
        itenantdao.save(tenant1);   
       
       
    }
   
   
    public FlatAddress findByAddressId(Integer addressId) {
        FlatAddress address = iflataddressjpadao.findById(addressId).get();
        return address;
    }

	public static void validateTenant(Tenant tenant)throws TenantNotFoundException
	{
		LOGGER.info("validateTenant() is initiated");

		validateTenantId(tenant.getTenantId());
		validateTenantAge(tenant.getTenantAge());
		validateTenantAddressId(tenant.getFlatAddress().getAddressId());
		validateTenantHouseNo(tenant.getFlatAddress().getHouseNo());
		validateTenantStreet(tenant.getFlatAddress().getStreet());
		validateTenantCity(tenant.getFlatAddress().getCity());
		validateTenantState(tenant.getFlatAddress().getState());
		validateTenantCountry(tenant.getFlatAddress().getCountry());
		validateTenantPin(tenant.getFlatAddress().getPin());
		LOGGER.info("validateTenant() has executed");

	}

	public static Boolean validateTenantCountry(String country)throws TenantNotFoundException {

		LOGGER.info("validateTenantCountry() is initiated");
		boolean flag=false;
		if(country.isEmpty())
		{
			LOGGER.error("Country cannot be empty");
			throw new TenantNotFoundException("Country cannot be Empty");
		}
		else if (!country.matches("^[a-zA-Z ][A-Za-z\\s]+$"))
		{
			LOGGER.error("Country cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("Country cannot contain Numbers or Special Characters");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantCounty() has executed");
		return flag;

	}

	private static Boolean validateTenantPin(int pin)throws TenantNotFoundException {

		LOGGER.info("validateTenantPin() is initiated");
		boolean flag=false;
		if(pin<=0)
		{
			LOGGER.error("PinCode cannot be 0 or negative");
			throw new TenantNotFoundException("PinCode cannot be negative");	
		}
		else if(Integer.toString(pin).length() != 6)
		{
			LOGGER.error("PinCode should be length 6");
			throw new TenantNotFoundException("PinCode should be of length 6");		
		}
		else if(!Integer.toString(pin).matches("^[0-9]+$"))
		{
			LOGGER.error("PinCode cannot contain any Characters");
			throw new TenantNotFoundException("PinCode cannot contain Alphabets or Special Characters");	
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantPin() has executed");
		return flag;

	}

	private static Boolean validateTenantState(String state)throws TenantNotFoundException {

		LOGGER.info("validateTenantState() is initiated");
		boolean flag=false;
		if(state.isEmpty())
		{
			LOGGER.error("State cannot be empty");
			throw new TenantNotFoundException("State cannot be Empty");
		}
		else if (!state.matches("^[a-zA-Z ][A-Za-z\\s]+$"))
		{
			LOGGER.error("State cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("State cannot contain Numbers or Special Characters");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantState() has executed");
		return flag;	
	}

	private static Boolean validateTenantCity(String city)throws TenantNotFoundException {

		LOGGER.info("validateTenantCity() is initiated");
		boolean flag=false;
		if(city.isEmpty())
		{
			LOGGER.error("City cannot be empty");
			throw new TenantNotFoundException("City cannot be Empty");
		}
		else if (!city.matches("^[a-zA-Z ][A-Za-z\\s]+$"))
		{
			LOGGER.error("City cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("City cannot contain Numbers or Special Characters");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantCity() has executed");
		return flag;	

	}

	private static Boolean validateTenantStreet(String street)throws TenantNotFoundException {

		LOGGER.info("validateTenantStreet() is initiated");
		boolean flag=false;
		if(street.isEmpty())
		{
			LOGGER.error("Street cannot be empty");
			throw new TenantNotFoundException("Street cannot be Empty");
		}
		else if (!street.matches("^[A-Za-z][A-Za-z\\s]+$"))
		{
			LOGGER.error("Street cannot contain Special Characters or Numbers");
			throw new TenantNotFoundException("Street cannot contain Numbers or Special Characters");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantStreet() has executed");
		return flag;

	}

	private static Boolean validateTenantHouseNo(int houseNo)throws TenantNotFoundException{

		LOGGER.info("validateTenantHouseNo() is initiated");
		boolean flag=false;
		if(houseNo <= 0 || Integer.toString(houseNo).isEmpty() )
		{
			LOGGER.error("House Number cannot be 0 or negative or empty");
			throw new TenantNotFoundException("House Number cannot be Empty or 0 or Negative");
		}
		else if(houseNo>10000)
		{
			LOGGER.error("House Number cannot be greater than 10000");
			throw new TenantNotFoundException("House Number cannot be greatre than 10000");
		}
		else
		
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantHouseNo() has executed");
		return flag;

	}

	private static Boolean validateTenantAddressId(int addressId)throws TenantNotFoundException {

		LOGGER.info("validateTenantAddressId() is initiated");
		boolean flag=false;
		if(addressId > 0 && addressId<1000)
		{
			LOGGER.info("validation Successful");
			flag=true;			
		}
		else
		{
			throw new TenantNotFoundException("Tenant ID cannot be 0 or negative or greater than 1000");
		}
		LOGGER.info("validateTenantAddressId() has executed");
		return flag;
	}


	private static Boolean validateTenantAge(int age)throws TenantNotFoundException {
		LOGGER.info("validateTenantAge() is initiated");
		boolean flag=false;
		if(age<18)
		{
			LOGGER.error("Age less than 18 is not allowed");
			throw new TenantNotFoundException("Tenant age should be greater than 18");
		}
		if(age>100)
		{
			LOGGER.error("Age greater than 100 is not allowed");
			throw new TenantNotFoundException("Tenant age should not be greater than 100");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantAge() has executed");
		return flag;
	}

	private static Boolean validateTenantId(int tenantId)throws TenantNotFoundException {

		LOGGER.info("validateTenantId() is initiated");
		boolean flag=false;
		if(tenantId > 0 && tenantId<1000)
		{
			LOGGER.info("validation Successful");
			flag=true;			
		}
		else
		{
			throw new TenantNotFoundException("Tenant ID cannot be 0 or negative or greater than 1000");
		}
		LOGGER.info("validateTenantId() has executed");
		return flag;

	}

	



	/*static final Logger LOGGER = LoggerFactory.getLogger(ITenantServiceImpl.class);

	@Autowired
	private ITenantDao itenantdao;

	String TenantIdNotFound="flat with given id was not found";

	@Override
	public Tenant addTenant(Tenant tenant)throws TenantNotFoundException {
		// TODO Auto-generated method stub
		LOGGER.info("addTenant() service is initiated");
		validateTenant(tenant);
		LOGGER.info("addTenant() service has executed");
		return itenantdao.save(tenant);
	}

	@Override
	public List<Tenant> viewAllTenants() {
		// TODO Auto-generated method stub
		LOGGER.info("viewAllTenant() service is initiated");
		LOGGER.info("viewAllTenant() service has executed");
		return itenantdao.findAll();
	}
	@Override
	public Tenant viewTenantById(int tenantId)throws TenantNotFoundException {
		// TODO Auto-generated method stub
		LOGGER.info("viewTenant() service is initiated");
		Optional<Tenant> optional= itenantdao.findById(tenantId);
		if(optional.isPresent())
		{

			Tenant tenant=optional.get();
			LOGGER.info("viewTenant() service has executed");
			return tenant;

		}
		else
		{
			throw new TenantNotFoundException("Tenant ID not found");
		}
	}

	@Override
	public Tenant updateTenant(Tenant tenant)throws TenantNotFoundException {
		LOGGER.info("updateTenant() service is initiated");
		Optional<Tenant> optional= itenantdao.findById(tenant.getTenantId());
		if(optional.isPresent())
		{
			validateTenant(tenant);
			LOGGER.info("updateTenant() service has executed");
			return itenantdao.save(tenant);

		}
		else
			throw new TenantNotFoundException("Tenant ID not found");
	}



	@Override
	public Boolean deleteTenant(int tenantId)throws TenantNotFoundException {
		LOGGER.info("deleteTenant() service is initiated");
		Optional<Tenant> optional=itenantdao.findById(tenantId);
		itenantdao.deleteById(tenantId);

		if(optional.isPresent())
		{
			LOGGER.info("deleteTenant() service has executed");
			return true;
		}

		throw new TenantNotFoundException("Tenant ID not found");

	}


	@Override
	public Tenant validateTenat(int tenantId) {
		return null;
	}

	public static void validateTenant(Tenant tenant)throws TenantNotFoundException
	{
		LOGGER.info("validateTenant() is initiated");

		validateTenantId(tenant.getTenantId());
		validateTenantAge(tenant.getTenantAge());
		validateTenantAddressId(tenant.getFlatAddress().getAddressId());
		validateTenantHouseNo(tenant.getFlatAddress().getHouseNo());
		validateTenantStreet(tenant.getFlatAddress().getStreet());
		validateTenantCity(tenant.getFlatAddress().getCity());
		validateTenantState(tenant.getFlatAddress().getState());
		validateTenantCountry(tenant.getFlatAddress().getCountry());
		validateTenantPin(tenant.getFlatAddress().getPin());
		LOGGER.info("validateTenant() has executed");

	}

	public static Boolean validateTenantCountry(String country)throws TenantNotFoundException {

		LOGGER.info("validateTenantCountry() is initiated");
		boolean flag=false;
		if(country.isEmpty())
		{
			LOGGER.error("Country cannot be empty");
			throw new TenantNotFoundException("Country cannot be Empty");
		}
		else if (!country.matches("^[a-zA-Z ][A-Za-z\\s]+$"))
		{
			LOGGER.error("Country cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("Country cannot contain Numbers or Special Characters");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantCounty() has executed");
		return flag;

	}

	private static Boolean validateTenantPin(int pin)throws TenantNotFoundException {

		LOGGER.info("validateTenantPin() is initiated");
		boolean flag=false;
		if(pin<=0)
		{
			LOGGER.error("PinCode cannot be 0 or negative");
			throw new TenantNotFoundException("PinCode cannot be negative");	
		}
		else if(Integer.toString(pin).length() != 6)
		{
			LOGGER.error("PinCode should be length 6");
			throw new TenantNotFoundException("PinCode should be of length 6");		
		}
		else if(!Integer.toString(pin).matches("^[0-9]+$"))
		{
			LOGGER.error("PinCode cannot contain any Characters");
			throw new TenantNotFoundException("PinCode cannot contain Alphabets or Special Characters");	
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantPin() has executed");
		return flag;

	}

	private static Boolean validateTenantState(String state)throws TenantNotFoundException {

		LOGGER.info("validateTenantState() is initiated");
		boolean flag=false;
		if(state.isEmpty())
		{
			LOGGER.error("State cannot be empty");
			throw new TenantNotFoundException("State cannot be Empty");
		}
		else if (!state.matches("^[a-zA-Z ][A-Za-z\\s]+$"))
		{
			LOGGER.error("State cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("State cannot contain Numbers or Special Characters");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantState() has executed");
		return flag;	
	}

	private static Boolean validateTenantCity(String city)throws TenantNotFoundException {

		LOGGER.info("validateTenantCity() is initiated");
		boolean flag=false;
		if(city.isEmpty())
		{
			LOGGER.error("City cannot be empty");
			throw new TenantNotFoundException("City cannot be Empty");
		}
		else if (!city.matches("^[a-zA-Z ][A-Za-z\\s]+$"))
		{
			LOGGER.error("City cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("City cannot contain Numbers or Special Characters");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantCity() has executed");
		return flag;	

	}

	private static Boolean validateTenantStreet(String street)throws TenantNotFoundException {

		LOGGER.info("validateTenantStreet() is initiated");
		boolean flag=false;
		if(street.isEmpty())
		{
			LOGGER.error("Street cannot be empty");
			throw new TenantNotFoundException("Street cannot be Empty");
		}
		else if (!street.matches("^[A-Za-z][A-Za-z\\s]+$"))
		{
			LOGGER.error("Street cannot contain Special Characters or Numbers");
			throw new TenantNotFoundException("Street cannot contain Numbers or Special Characters");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantStreet() has executed");
		return flag;

	}

	private static Boolean validateTenantHouseNo(int houseNo)throws TenantNotFoundException{

		LOGGER.info("validateTenantHouseNo() is initiated");
		boolean flag=false;
		if(houseNo <= 0 || Integer.toString(houseNo).isEmpty() )
		{
			LOGGER.error("House Number cannot be 0 or negative or empty");
			throw new TenantNotFoundException("House Number cannot be Empty or 0 or Negative");
		}
		else if(houseNo>10000)
		{
			LOGGER.error("House Number cannot be greater than 10000");
			throw new TenantNotFoundException("House Number cannot be greatre than 10000");
		}
		else
		
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantHouseNo() has executed");
		return flag;

	}

	private static Boolean validateTenantAddressId(int addressId)throws TenantNotFoundException {

		LOGGER.info("validateTenantAddressId() is initiated");
		boolean flag=false;
		if(addressId > 0 && addressId<1000)
		{
			LOGGER.info("validation Successful");
			flag=true;			
		}
		else
		{
			throw new TenantNotFoundException("Tenant ID cannot be 0 or negative or greater than 1000");
		}
		LOGGER.info("validateTenantAddressId() has executed");
		return flag;
	}


	private static Boolean validateTenantAge(int age)throws TenantNotFoundException {
		LOGGER.info("validateTenantAge() is initiated");
		boolean flag=false;
		if(age<18)
		{
			LOGGER.error("Age less than 18 is not allowed");
			throw new TenantNotFoundException("Tenant age should be greater than 18");
		}
		if(age>100)
		{
			LOGGER.error("Age greater than 100 is not allowed");
			throw new TenantNotFoundException("Tenant age should not be greater than 100");
		}
		else
		{
			LOGGER.info("validation Successful");
			flag=true;
		}
		LOGGER.info("validateTenantAge() has executed");
		return flag;
	}

	private static Boolean validateTenantId(int tenantId)throws TenantNotFoundException {

		LOGGER.info("validateTenantId() is initiated");
		boolean flag=false;
		if(tenantId > 0 && tenantId<1000)
		{
			LOGGER.info("validation Successful");
			flag=true;			
		}
		else
		{
			throw new TenantNotFoundException("Tenant ID cannot be 0 or negative or greater than 1000");
		}
		LOGGER.info("validateTenantId() has executed");
		return flag;

	}*/

}