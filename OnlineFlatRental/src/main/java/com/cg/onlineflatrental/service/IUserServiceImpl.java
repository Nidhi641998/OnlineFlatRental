package com.cg.onlineflatrental.service;

import java.util.List;
import java.util.Optional;

//import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.*;
//import org.slf4j.ILoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.cg.onlineflatrental.OnlineFlatRentalApp;
import com.cg.onlineflatrental.controller.IUserController;
import com.cg.onlineflatrental.dao.IUserJpaDao;
import com.cg.onlineflatrental.exception.UserNotFoundException;
import com.cg.onlineflatrental.model.User;

@Service
@Transactional
public class IUserServiceImpl implements IUserService{
	
	Logger logger=LoggerFactory.getLogger(IUserServiceImpl.class);

			
	@Autowired
	private IUserJpaDao onlineFlatRentalJpaDao; 
	
	@Override
	public User viewUser(int id) throws UserNotFoundException {
		Optional<User> optional=onlineFlatRentalJpaDao.findById(id);
		logger.info("In Service....viewUser() started...");
		if(optional.isPresent()) {
		return onlineFlatRentalJpaDao.findById(id).get();}
		else {
			throw new UserNotFoundException("NO SUCH USER FOUND");
		} 
	}

	@Override
	public List<User> viewAllUser() {
		logger.info("In Service....viewAllUser() started...");
		return onlineFlatRentalJpaDao.findAll();
		
	}

	@Override
	public User validateUser(String username, String password) throws UserNotFoundException {
	/*	Optional<User> optional=Optional.of(onlineFlatRentalJpaDao.validateUser(username,password));
		User user1=new User();
	//	user1.getUserName()== userName ;
		if(optional.isPresent()) {
			user1.setUserName(username);
			user1.setPassword(password); 
		}
		return onlineFlatRentalJpaDao.save(user1);	*/
		logger.info("In Service....validateUser() started...");
		User user1=onlineFlatRentalJpaDao.validateUser(username,password);
		if(user1!=null) {
			return user1;
		}
		else {
			throw new UserNotFoundException("Failed to validate the user!");
		}
	}

	@Override
	public User addUser(User user) {
		logger.info("In Service....addUser() started...");
		return onlineFlatRentalJpaDao.save(user);
	}

	@Override
	public User updateUser(User user) {
/*		Optional<User> optional=onlineFlatRentalJpaDao.findById(user.getUserId());
		User user1=new User();
		if(optional.isPresent()) {
			user1.setUserId(user.getUserId());
			user1.setUserName(user.getUserName());
			user1.setUserType(user.getUserType());
			user1.setPassword(user.getPassword());
		}
		return onlineFlatRentalJpaDao.save(user1);
	*/	Optional<User> optional=onlineFlatRentalJpaDao.findById(user.getUserId());
		logger.info("In Service....updateUser() started...");
		User user1=optional.get();
		if(user.getUserName()!=null) {
		user1.setUserName(user.getUserName());
		}
		if(user.getUserType()!=null) {
		user1.setUserType(user.getUserType());
		}
		return onlineFlatRentalJpaDao.save(user1);
	}		

	/*@Override
	public User updatePassword(User user, String newpass) {
		// TODO Auto-generated method stub
		return null;
	}*/
	public User updatePassword(Integer userId, String newpass) {
		User use=onlineFlatRentalJpaDao.findById(userId).get();
		use.setPassword(newpass);
		logger.info("In Service....updatePassword() started...");
		return onlineFlatRentalJpaDao.save(use);
	}

	
	
/*	@Override
	public void removeUser(Integer userId) {
	//	User deletedUser=user;
		logger.info("In Service....removeUser() started...");
		 onlineFlatRentalJpaDao.deleteById(userId);;
	//	return deletedUser;
		 
	}	*/
	
	@Override
	public boolean removeUser(Integer userId) throws UserNotFoundException {
		Optional<User> user=onlineFlatRentalJpaDao.findById(userId);
		if(user.isPresent())
		{
			onlineFlatRentalJpaDao.deleteById(userId);
			return true;
			
		}
		else {
			 throw new UserNotFoundException("NO SUCH USER FOUND");
		}
		
	}
	
	}
	


