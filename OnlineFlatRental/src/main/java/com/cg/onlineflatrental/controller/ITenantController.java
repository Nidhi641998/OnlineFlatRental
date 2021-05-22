package com.cg.onlineflatrental.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineflatrental.exception.TenantNotFoundException;
import com.cg.onlineflatrental.model.Tenant;
import com.cg.onlineflatrental.service.ITenantService;


@RestController
@RequestMapping("/Tenant")
public class ITenantController {

	final Logger LOGGER= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITenantService tenantService;


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



	@DeleteMapping("/deleteTenant/{tenantId}")
	public ResponseEntity deleteTenant(@PathVariable int tenantId)throws TenantNotFoundException {
		LOGGER.info("deleteTenant URL is opened");
		LOGGER.info("deleteTenant() is initiated");
		tenantService.deleteTenant(tenantId);
		LOGGER.info("deleteTenant() has executed");
		return new ResponseEntity("Tenant deleted successfully",HttpStatus.OK);
	}


	@Validated
	public Tenant validateTenant(@RequestBody int tenantId ) {
		return tenantService.validateTenat(tenantId);
	}

}	  


