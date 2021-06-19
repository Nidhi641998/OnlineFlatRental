package com.cg.onlineflatrental.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineflatrental.dao.IFlatJpaDao;
import com.cg.onlineflatrental.dao.ILandlordJpaDao;
import com.cg.onlineflatrental.dto.FlatDto;
import com.cg.onlineflatrental.dto.LandlordDto;
import com.cg.onlineflatrental.exception.InvalidLandlordInputException;
import com.cg.onlineflatrental.exception.LandlordNotFoundException;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.model.Landlord;
import com.cg.onlineflatrental.service.ILandlordService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flatbooking")
public class ILandlordController {
	private static final Logger logger = LoggerFactory.getLogger(ILandlordController.class);

	@Autowired
	private ILandlordService ilandlordservice;
	
	@Autowired
	private ILandlordJpaDao ilandlordjpadao;
	
	@Autowired
	private IFlatJpaDao iflatjpadao;

	@GetMapping("/viewAllLandlord") 
	public List<Landlord> viewAllLandlord() 
	{
		logger.info("===In Get Controller===");
		logger.info("/viewAllLandlord URL is opened");
		logger.info("viewAllLandlord() controller is initiated");
		return (List<Landlord>) ilandlordservice.viewAllLandlord();
	}

	@GetMapping("/viewLandlord/{landlordId}")
	public Landlord viewLandlordById(@PathVariable int landlordId) throws LandlordNotFoundException
	{
		logger.info("===In Get Controller===");
		logger.info("/viewLandlord/{landlordId} URL is opened");
		logger.info("viewLandlordById() controller is initiated");
		return ilandlordservice.viewLandlordById(landlordId);
	}

	@PostMapping("/addLandlord")
	public Landlord addLandlord(@RequestBody Landlord landlord) throws LandlordNotFoundException, InvalidLandlordInputException
	{
		logger.info("===In Post Controller===");
		logger.info("/addLandlord URL is opened");
		logger.info("addLandlord() controller is initiated");
		logger.info("addLandlord() controller has executed");
		return ilandlordservice.addLandlord(landlord);
		
	}

	@PutMapping("/updateLandlord")
	public ResponseEntity updateLandlord(@RequestBody Landlord landlord) throws LandlordNotFoundException,InvalidLandlordInputException
	{
		logger.info("===In Put Controller===");
		logger.info("/updateLandlord URL is opened");
		logger.info("updateLandlord() controller is initiated");
		Landlord landlord1= ilandlordservice.updateLandlord(landlord);
		logger.info("updateLandlord() controller has executed");
		return new ResponseEntity(landlord1, HttpStatus.OK);
	}

	@DeleteMapping("/deleteLandlord/{landlordId}")
	public ResponseEntity deleteLandlordById(@PathVariable int landlordId) throws LandlordNotFoundException
	{
		logger.info("===In Delete Controller===");
		logger.info("/deleteLandlord/{landlordId} URL is opened");
		logger.info("deleteLandlordById() controller is initiated");
		ilandlordservice.deleteLandlordById(landlordId);
		logger.info("deleteLandlordById() controller has executed");
		return  new ResponseEntity("Landlord deleted successfully", HttpStatus.OK);
	}
	
	@PostMapping("/addLandlord1")
	public ResponseEntity addLandlord1(@RequestBody LandlordDto landlord) {
		
		Landlord landlord1 = new Landlord();
		landlord1.setLandlordId(landlord.getLandlordId());
		landlord1.setLandlordName(landlord.getLandlordName());
		landlord1.setLandlordAge(landlord.getLandlordAge());
		// Business segment object
		
		Flat flat = findByFlatId(landlord.getFlat());
		//System.out.println("busi segment = "+segment.getBus_seg_id()+" "+segment.getBus_seg_name()); 
		landlord1.setFlat(flat); 
		ilandlordjpadao.save(landlord1);	
		return  new ResponseEntity(landlord1, HttpStatus.OK);
		
		
	}
	
	
	public Flat findByFlatId(Integer flatId) {
		Flat flat = iflatjpadao.findById(flatId).get();
		return flat;
	}
	
	@PutMapping("/updateLandlord1")
	public ResponseEntity updateLandlord1(@RequestBody LandlordDto landlord)
	{
		Landlord landlord1=ilandlordjpadao.findById(landlord.getLandlordId()).get();
		landlord1.setLandlordName(landlord.getLandlordName());
		landlord1.setLandlordAge(landlord.getLandlordAge());
		// Business segment object
		
		Flat flat = findByFlatId(landlord.getFlat());
		//System.out.println("busi segment = "+segment.getBus_seg_id()+" "+segment.getBus_seg_name()); 
		landlord1.setFlat(flat); 
		ilandlordjpadao.save(landlord1);	
		return  new ResponseEntity(landlord1, HttpStatus.OK);
	}
}

//addLandlord,updateLandlord,deleteLandlord,viewLandlord,viewallLandlord 

/*{
  	"landlordId": 10,
"landlordName": "Vijeta Choudhary",
"landlordAge": 22,
"flat": {
    "cost": 10,
    "flatAddress": {
        "houseNo": 100,
        "street": "Kormangala",
        "city": "Bangalore",
        "state": "TamilNadu",
        "pin": 760200,
        "country": "India"
    },
    "availability": "Yes"
}
}*/