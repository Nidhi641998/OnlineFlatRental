package com.cg.onlineflatrental.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.cg.onlineflatrental.exception.FlatNotFoundException;
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
	public Flat viewFlat(@PathVariable Integer flatId) throws FlatNotFoundException
	{
		return iflatservice.viewFlat(flatId);
	}

	@PostMapping("/addFlat")
	public Flat addFlat(@RequestBody Flat flat)
	{
		return iflatservice.addFlat(flat);
	}
	
	@PutMapping("/updateFlat")
	public Flat updateFlat(@RequestBody Flat flat) throws FlatNotFoundException
	{
		return iflatservice.updateFlat(flat);
	}
	
	@DeleteMapping("/deleteFlat/{flatId}")
	public boolean deleteFlatById(@PathVariable Integer flatId) throws FlatNotFoundException
	{
		return iflatservice.deleteFlatById(flatId);
	}
}

//
//addflat,updateflat,deleteflat,viewflat,viewallflat/viewallflatbycost