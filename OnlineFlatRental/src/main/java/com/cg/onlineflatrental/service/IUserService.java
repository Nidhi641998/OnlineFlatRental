package com.cg.onlineflatrental.service;


import java.util.List;

import com.cg.onlineflatrental.exception.UserNotFoundException;
import com.cg.onlineflatrental.model.User;

public interface IUserService {
	public User viewUser(int id) throws UserNotFoundException;  //
	public List<User> viewAllUser();							//
	public User validateUser(String username,String password) throws UserNotFoundException;
	public User addUser(User user);								//
	//public User updateUser(User user);
//	public User updatePassword(User user, String newpass) ;

	public User updatePassword(Integer userId,String newpass);

//	public void removeUser(Integer userId);						//
	public boolean removeUser(Integer userId) throws UserNotFoundException;
	
}
