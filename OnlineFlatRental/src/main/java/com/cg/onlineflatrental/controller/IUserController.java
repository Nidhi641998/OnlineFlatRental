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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineflatrental.exception.UserNotFoundException;
import com.cg.onlineflatrental.model.*;
import com.cg.onlineflatrental.service.IUserService;

@RestController 
@RequestMapping("/api")
public class IUserController {
	
	@Autowired 
	private IUserService iUserService;
	
	@GetMapping("/viewAllUser")
	public List<User> viewAllUser(){
		return  iUserService.viewAllUser();//(List<User>)
	}
	
	@GetMapping("/viewUser/{userId}")
	public User viewUser(@PathVariable Integer userId) throws UserNotFoundException {
		return iUserService.viewUser(userId);
	}
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User users) {
		return iUserService.addUser(users);
	}
	
	@DeleteMapping("/removeUser/{userId}")
	public void removeUser(@PathVariable Integer userId ) {
		//return 
				iUserService.removeUser(userId);
	}
	
	@PutMapping("/updatePassword/{password:.+}/users/{userId}")
	public User updatePassword(@PathVariable String password ,@PathVariable Integer userId) {
		return iUserService.updatePassword(userId, password);
		
	}
	
	@GetMapping("validateUser/{userName}/{password}")
	public User validateUser(@PathVariable String userName,@PathVariable String password) throws UserNotFoundException {
		return iUserService.validateUser(userName, password);
	}
	
	
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User users) {
		users.setUserId(users.getUserId());
		users.setPassword(users.getPassword());
		users.getUserName();
		users.getUserType();
		return	iUserService.updateUser(users);
		//return iUserService.updateUser(userName,userType);
	}

}
