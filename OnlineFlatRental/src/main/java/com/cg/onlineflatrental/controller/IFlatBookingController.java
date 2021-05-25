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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cg.onlineflatrental.model.FlatBooking;
import com.cg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;

import com.cg.onlineflatrental.service.IFlatBookingService;

@RestController
@RequestMapping("/flatbooking")
public class IFlatBookingController {
	private static final Logger logger = LoggerFactory.getLogger(IFlatBookingController.class);
	

	@Autowired
	private IFlatBookingService flatBookingService;
	

	@PostMapping("/addFlatBooking")
    public FlatBooking addFlatBooking1(@RequestBody FlatBooking flatBooking) throws InvalidFlatInputException
    {
		logger.info("===In Post Controller===");
		logger.info("addFlat1 URL is opened");
		logger.info("addFlatBooking1() controller is initiated");
        return flatBookingService.addFlatBooking1(flatBooking);
    }
	
	@PutMapping("/updateFlatBooking")
	public ResponseEntity updateFlat(@RequestBody FlatBooking flatbooking) throws FlatBookingNotFoundException
	{
		logger.info("===In Put Controller===");
		logger.info("updateFlat URL is opened");
		logger.info("updateFlatBooking() controller is initiated");
		FlatBooking flat1= flatBookingService.updateFlatBooking(flatbooking);
		logger.info("updateFlat() controller has executed");
		return new ResponseEntity(flat1, HttpStatus.OK);
	}

	@DeleteMapping("/deleteFlatBooking/{bookingNo}")
	public  ResponseEntity deleteFlatById(@PathVariable Integer bookingNo) throws FlatBookingNotFoundException
	{
		logger.info("===In Delete Controller===");
		logger.info("deleteFlat/{bookingNo} URL is opened");
		logger.info("deleteFlatBookingbyId() controller is initiated");
		flatBookingService.deleteFlatBookingbyId(bookingNo);
		logger.info("deleteFlatBookingbyId() controller has executed");
		return new ResponseEntity("Flat deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/viewAllFlatBooking/{bookingNo}")
	public ResponseEntity viewFlat(@PathVariable("bookingNo") int bookingNo) throws FlatBookingNotFoundException
	{
		logger.info("===In Get Controller===");
		logger.info("/viewAllFlat/{bookingNo} URL is opened");
		logger.info("viewFlat() controller is initiated");
		FlatBooking flatbooking=flatBookingService.viewFlatBooking(bookingNo);
		logger.info("viewFlat() controller has executed");
		return new ResponseEntity(flatbooking, HttpStatus.OK);
	}
	@GetMapping("/viewAllFlatBooking")
	public List<FlatBooking> viewAllFlatBooking() {
		logger.info("===In Get Controller===");
		logger.info("viewAllFlatBooking URL is opened");
		logger.info("viewAllFlatBooking() controller is initiated");
		return (List<FlatBooking>) flatBookingService.viewAllFlatBooking();
	}
	

	    
}

	
	/*
	  
	 
	 FlatBooking:
	   {
        "bookingNo": 20,
        "flat": {
            "flatId": 100,
            "cost": 325555,
            "flatAddress": {
                "id": 21,
                "houseNo": 14,
                "street": "RamaPuraRoad",
                "city": "DeogharA",
                "state": "Jharkhand",
                "pin": 110002,
                "country": "India"
            },
            "availability": "yes"
        },
        "tenant": {
            "tenantId": 21,
            "tenantName": "AmitKumarGupta",
            "tenantAge": 22,
            "flatAddress": {
                "id": 15,
                "houseNo": 16,
                "street": "RKMVRoadA",
                "city": "Deoghar",
                "state": "Jharkahnd",
                "pin": 841460,
                "country": "India"
            }
        },
        "bookingFromDate": "2016-11-01",
        "bookingToDate": "2016-11-06"
    }
    
    
    Flat:
    {
        "flatId": 6,
        "cost": 25600,
        "flatAddress": {
            "addressId": 5,
            "houseNo": 100,
            "street": "Kormangala",
            "city": "Bangalore",
            "state": "Karnataka",
            "pin": 560200,
            "country": "India"
        },
        "availability": "Yes"
    }
    
    
    Tenant:
    { "tenantId":11,
		"tenantName": "Sourabha",
		"tenantAge":19,
		"flatAddress": {
            "addressId":11,
            "houseNo": 102,
            "street": "Main Road",
            "city": "Mumbai",
            "state": "Maharastra",
            "pin": 645872,
            "country": "India"
        }
        }
        
        User:
        {
  "userName":"iamakash",
  "password":"mypass",
  "userType":"landlord"
}

		Landlord:
		 {
        "landlordId": 8,
        "landlordName": "Nidhi",
        "landlordAge": 23,
        "flat": {
            "flatId": 2,
            "cost": 325555,
            "flatAddress": {
                "addressId": 1,
                "houseNo": 14,
                "street": "RamaPuraRoad",
                "city": "DeogharA",
                "state": "Karnataka",
                "pin": 110002,
                "country": "India"
            },
            "availability": "No"
        }
    }
    */	
	 
	
	
	
	
	
	
