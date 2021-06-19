package com.cg.onlineflatrental.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.onlineflatrental.dao.IFlatAddressJpaDao;
import com.cg.onlineflatrental.dao.IFlatJpaDao;
import com.cg.onlineflatrental.dto.FlatDto;
import com.cg.onlineflatrental.exception.ErrorMessage;
import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.service.IFlatService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flatbooking")
public class IFlatController {
	
	private static final Logger logger = LoggerFactory.getLogger(IFlatController.class);
	@Autowired
	private IFlatService iflatservice;
	
	@Autowired
	private IFlatAddressJpaDao iflataddressjpadao;
	
	@Autowired
	private IFlatJpaDao iflatjpadao;
	
	@GetMapping("/viewAllFlat")
	public List<Flat> viewAllFlat()
	{
		logger.info("===In Get Controller===");
		logger.info("viewAllFlat URL is opened");
		logger.info("viewAllFlat() controller is initiated");
		return (List<Flat>) iflatservice.viewAllFlat();
	}
	
	@GetMapping("/viewAllFlat/{flatId}")
	public ResponseEntity viewFlat(@PathVariable("flatId") Integer flatId) throws FlatNotFoundException
	{
		logger.info("===In Get Controller===");
		logger.info("viewAllFlat/{flatId} URL is opened");
		logger.info("viewFlat() controller is initiated");
		Flat flat=iflatservice.viewFlat(flatId);
		logger.info("viewFlat() controller has executed");
		return new ResponseEntity(flat, HttpStatus.OK);
	}

	@PostMapping("/addFlat")
	public Flat addFlat(@RequestBody Flat flat) throws InvalidFlatInputException
	{
		logger.info("===In Post Controller===");
		logger.info("addFlat URL is opened");
		logger.info("addFlat() controller is initiated");
		Flat flat1= iflatservice.addFlat(flat);
		logger.info("addFlat() controller has executed");
		return flat1;
		
	}
	
	@PutMapping("/updateFlat")
	public ResponseEntity updateFlat(@RequestBody Flat flat) throws FlatNotFoundException, InvalidFlatInputException
	{
		logger.info("===In Put Controller===");
		logger.info("updateFlat URL is opened");
		logger.info("updateFlat() controller is initiated");
		Flat flat1= iflatservice.updateFlat(flat);
		logger.info("updateFlat() controller has executed");
		return new ResponseEntity(flat1, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteFlat/{flatId}")
	public  ResponseEntity deleteFlatById(@PathVariable Integer flatId) throws FlatNotFoundException
	{
		logger.info("===In Delete Controller===");
		logger.info("deleteFlat/{flatId} URL is opened");
		logger.info("deleteFlatById() controller is initiated");
		iflatservice.deleteFlatById(flatId);
		logger.info("deleteFlatById() controller has executed");
		return new ResponseEntity("Flat deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/viewByCost/{cost}/{availability}")
	public List<Flat> findByCostAndAvailability(@PathVariable Float cost, @PathVariable String availability) throws FlatNotFoundException, InvalidFlatInputException 
	{
		logger.info("===In Delete Controller===");
		logger.info("viewByCost/{cost}/{availability} URL is opened");
		logger.info("viewAllFlatByCost() controller is initiated");
		List<Flat> flatList=iflatservice.findByCostAndAvailability(cost,availability);
		logger.info("viewAllFlatByCost() controller has executed");
		return flatList;	
	}
	
	@PostMapping("/addFlatAddress")
	public FlatAddress addFlatAddress(@RequestBody FlatAddress flatAddress) 
	{
		logger.info("===In Post Controller===");
		logger.info("addFlat URL is opened");
		logger.info("addFlat() controller is initiated");
		return iflataddressjpadao.save(flatAddress);
		//logger.info("addFlat() controller has executed");
		
		
	}
	
	@PostMapping("/addFlat1")
	public ResponseEntity addFlat1(@RequestBody FlatDto flat) 
	{
		logger.info("===In Post Controller===");
		logger.info("addFlat URL is opened");
		logger.info("addFlat() controller is initiated");
		 iflatservice.addFlat1(flat);
		logger.info("addFlat() controller has executed");
		return new ResponseEntity(flat,HttpStatus.OK);
	
	}
	
	@PutMapping("/updateFlatAddress")
	public ResponseEntity updateFlatAddress(@RequestBody FlatAddress flatAddress) 
	{
		logger.info("===In Put Controller===");
		logger.info("updateFlatAddress URL is opened");
		logger.info("updateFlatAddress() controller is initiated");
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
		
	}
	
	@PutMapping("/updateFlat1")
	public ResponseEntity updateFlat1(@RequestBody FlatDto flat)
	{
		Flat flat1=iflatjpadao.findById(flat.getFlatId()).get();
		flat1.setCost(flat.getCost());
		flat1.setAvailability(flat.getAvailability());
		
		FlatAddress flatAddress = findByAddressId(flat.getFlat_address());
		flat1.setFlatAddress(flatAddress); 
		iflatjpadao.save(flat1);
		
		return new ResponseEntity(flat1,HttpStatus.OK);
	}
	
	
	public FlatAddress findByAddressId(Integer addressId) {
		FlatAddress address = iflataddressjpadao.findById(addressId).get();
		return address;
	}
	
	@GetMapping("/viewAllFlatAddress/{addressId}")
	public ResponseEntity viewFlatAddress(@PathVariable("addressId") Integer addressId) throws FlatNotFoundException
	{
		logger.info("===In Get Controller===");
		logger.info("viewAllFlatAddress/{addressId} URL is opened");
		logger.info("viewFlatAddress() controller is initiated");
		FlatAddress flatAddress=iflataddressjpadao.findById(addressId).get();
		logger.info("viewFlaAddresst() controller has executed");
		return new ResponseEntity(flatAddress, HttpStatus.OK);
	}
	
//	@ResponseBody
	/*@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {FlatNotFoundException.class})
	  protected String handleConflict2(Exception ex, HttpServletRequest req) {
        String bodyOfResponse = ex.getMessage();// "Country with this id not present";
       
        return   bodyOfResponse; 
    }*/
/*	@ExceptionHandler(FlatNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleFlatNotFoundException(FlatNotFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
		
	}*/

}


//addflat,updateflat,deleteflat,viewflat,viewallflat/viewallflatbycost