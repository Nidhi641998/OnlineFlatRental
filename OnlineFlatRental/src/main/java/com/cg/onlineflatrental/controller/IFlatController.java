package com.cg.onlineflatrental.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.onlineflatrental.exception.ErrorMessage;
import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.services.IFlatService;

@RestController
@RequestMapping("/flatbooking")
public class IFlatController {
	
	@Autowired
	private IFlatService iflatservice;
	
	@GetMapping("/viewAllFlat")
	public List<Flat> viewAllFlat()
	{
		return (List<Flat>) iflatservice.viewAllFlat();
	}
	
	@GetMapping("/viewAllFlat/{flatId}")
	public ResponseEntity viewFlat(@PathVariable("flatId") Integer flatId) throws FlatNotFoundException
	{
		Flat flat=iflatservice.viewFlat(flatId);
		return new ResponseEntity(flat, HttpStatus.OK);
	}

	@PostMapping("/addFlat")
	public Flat addFlat(@RequestBody Flat flat) throws InvalidFlatInputException
	{
		return iflatservice.addFlat(flat);
	}
	
	@PutMapping("/updateFlat")
	public ResponseEntity updateFlat(@RequestBody Flat flat) throws FlatNotFoundException, InvalidFlatInputException
	{
		Flat flat1= iflatservice.updateFlat(flat);
		return new ResponseEntity(flat1, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteFlat/{flatId}")
	public  ResponseEntity deleteFlatById(@PathVariable Integer flatId) throws FlatNotFoundException
	{
		iflatservice.deleteFlatById(flatId);
		return new ResponseEntity("Flat deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/viewByCost/{cost}/{availability}")
	public List<Flat> viewAllFlatByCost(@PathVariable float cost, @PathVariable String availability) 
	{
		return (List<Flat>)iflatservice.findByCostAndAvailability(cost,availability);
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