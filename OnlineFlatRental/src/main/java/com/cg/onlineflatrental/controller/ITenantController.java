package com.cg.onlineflatrental.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineflatrental.dao.IFlatAddressJpaDao;
import com.cg.onlineflatrental.dao.ITenantDao;
import com.cg.onlineflatrental.dto.FlatDto;
import com.cg.onlineflatrental.dto.TenantDto;
import com.cg.onlineflatrental.exception.TenantNotFoundException;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.model.Tenant;
import com.cg.onlineflatrental.service.ITenantService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flatbooking")
public class ITenantController {

	final Logger LOGGER= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITenantService tenantService;
	
	@Autowired
	private IFlatAddressJpaDao iflataddressjpadao;
	
	@Autowired
	private ITenantDao itenantdao;
    


	@GetMapping("/viewAllTenants")
	public List<Tenant> viewAllTenants(){
		LOGGER.info("viewAllTenants URL is opened");
		LOGGER.info("viewAllTenant() is initiated");
		LOGGER.info("viewAllTenant() has executed");
		return (List<Tenant>) tenantService.viewAllTenants();
	}

	@GetMapping("/viewTenantById/{tenantId}") 
	public ResponseEntity viewTenantById(@PathVariable int tenantId)throws TenantNotFoundException
	{
		LOGGER.info("viewTenantById URL is opened");
		LOGGER.info("viewTenantById() is initiated");
		Tenant tenant= tenantService.viewTenantById(tenantId);
		LOGGER.info("viewTenantById() has executed");
		return new ResponseEntity(tenant,HttpStatus.OK);
	}   

	@PostMapping("/addTenant")
	public Tenant addTenant(@RequestBody Tenant tenant)throws TenantNotFoundException{

		return tenantService.addTenant(tenant);
	}    

	@PutMapping("/updateTenant")
	public ResponseEntity updateTenant(@RequestBody Tenant tenant)throws TenantNotFoundException{
		LOGGER.info("updateTenant URL is opened");
		LOGGER.info("updateTenant() is initiated");
		Tenant tenant1= tenantService.updateTenant(tenant);
		LOGGER.info("updateTenant() has executed");
		return new ResponseEntity(tenant1,HttpStatus.OK);
	}
	
	/*@PutMapping("/updateFlatAddress")
	public ResponseEntity updateFlatAddress(@RequestBody FlatAddress flatAddress) 
	{
		
		FlatAddress flatAddress1=iflataddressjpadao.findById(flatAddress.getAddressId()).get();

		flatAddress1.setHouseNo(flatAddress.getHouseNo());
		flatAddress1.setStreet(flatAddress.getStreet());
		flatAddress1.setCity(flatAddress.getCity());
		flatAddress1.setState(flatAddress.getState());
		flatAddress1.setPin(flatAddress.getPin());
		flatAddress1.setCountry(flatAddress.getCountry());
		iflataddressjpadao.save(flatAddress1);
		//logger.info("addFlat() controller has executed");
		
		
		return new ResponseEntity(flatAddress1,HttpStatus.OK);
		
	}*/
	
	@PutMapping("/updateTenant1")
	public ResponseEntity updateTenant1(@RequestBody TenantDto tenant)
	{
		Tenant tenant1=itenantdao.findById(tenant.getTenantId()).get();
		tenant1.setTenantName(tenant.getTenantName());
		tenant1.setTenantAge(tenant.getTenantAge());
		
		FlatAddress flatAddress = findByAddressId(tenant.getFlat_address());
		tenant1.setFlatAddress(flatAddress); 
		itenantdao.save(tenant1);
		
		return new ResponseEntity(tenant1,HttpStatus.OK);
	}
	
	public FlatAddress findByAddressId(Integer addressId) {
		FlatAddress address = iflataddressjpadao.findById(addressId).get();
		return address;
	}
	/*@GetMapping("/viewAllFlatAddress/{addressId}")
	public ResponseEntity viewFlatAddress(@PathVariable("addressId") Integer addressId) throws TenantNotFoundException
	{
		
		FlatAddress flatAddress=iflataddressjpadao.findById(addressId).get();
		
		return new ResponseEntity(flatAddress, HttpStatus.OK);
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	@DeleteMapping("/deleteTenant/{tenantId}")
	public ResponseEntity deleteTenant(@PathVariable int tenantId)throws TenantNotFoundException {
		LOGGER.info("deleteTenant URL is opened");
		LOGGER.info("deleteTenant() is initiated");
		tenantService.deleteTenant(tenantId);
		LOGGER.info("deleteTenant() has executed");
		return new ResponseEntity("Tenant deleted successfully",HttpStatus.OK);
	}


//	@Validated
//	public Tenant validateTenant(@RequestBody int tenantId ) {
//		return tenantService.validateTenat(tenantId);
//	}
	@PostMapping("/addTenant1")
    public ResponseEntity<String> addTenant1(@RequestBody TenantDto tenant) 
    {
		LOGGER.info("===In Post Controller===");
		LOGGER.info("addtenant URL is opened");
		LOGGER.info("addTenant1() controller is initiated");
         tenantService.addTenant1(tenant);
         LOGGER.info("addFlat() controller has executed");
        return new ResponseEntity<String>(HttpStatus.OK);

 

        
    }

}	  


