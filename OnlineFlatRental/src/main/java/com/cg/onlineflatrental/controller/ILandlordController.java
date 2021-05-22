package com.cg.onlineflatrental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineflatrental.exception.LandlordNotFoundException;
import com.cg.onlineflatrental.model.Landlord;
import com.cg.onlineflatrental.service.ILandlordService;

@RestController
@RequestMapping("/landlord")
public class ILandlordController {

	@Autowired
	private ILandlordService ilandlordservice;

	@GetMapping("/viewAllLandlord") 
	public List<Landlord> viewAllLandlord() 
	{
		return (List<Landlord>) ilandlordservice.viewAllLandlord();
	}

	@GetMapping("/viewLandlord/{landlordId}")
	public Landlord viewLandlordById(@PathVariable int landlordId) throws LandlordNotFoundException
	{
		return ilandlordservice.viewLandlordById(landlordId);
	}

	@PostMapping("/addLandlord")
	public Landlord addLandlord(@RequestBody Landlord landlord)
	{
		return ilandlordservice.addLandlord(landlord);
	}

	@PutMapping("/updateLandlord")
	public ResponseEntity updateLandlord(@RequestBody Landlord landlord) throws LandlordNotFoundException
	{
		Landlord landlord1= ilandlordservice.updateLandlord(landlord);
		return new ResponseEntity(landlord1, HttpStatus.OK);
	}

	@DeleteMapping("/deleteLandlord/{landlordId}")
	public ResponseEntity deleteLandlordById(@PathVariable int landlordId) throws LandlordNotFoundException
	{
		ilandlordservice.deleteLandlordById(landlordId);
		return  new ResponseEntity("Landlord deleted successfully", HttpStatus.OK);
	}
}

//addLandlord,updateLandlord,deleteLandlord,viewLandlord,viewallLandlord 