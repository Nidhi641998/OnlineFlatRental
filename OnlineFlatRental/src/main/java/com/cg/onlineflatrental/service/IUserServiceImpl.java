package com.cg.onlineflatrental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineflatrental.dao.IUserJpaDao;
import com.cg.onlineflatrental.exception.UserNotFoundException;
import com.cg.onlineflatrental.model.User;

@Service
@Transactional
public class IUserServiceImpl implements IUserService{
	
	@Autowired
	private IUserJpaDao onlineFlatRentalJpaDao; 
	
	@Override
	public User viewUser(int id) throws UserNotFoundException {
		Optional<User> optional=onlineFlatRentalJpaDao.findById(id);
		if(optional.isPresent()) {
		return onlineFlatRentalJpaDao.findById(id).get();}
		else {
			throw new UserNotFoundException("NO SUCH USER");
		}
	}

	@Override
	public List<User> viewAllUser() {
		// TODO Auto-generated method stub
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
		User user1=validateUser(username,password);
		if(user1!=null) {
			return user1;
		}
		else {
			throw new UserNotFoundException("NO SUCH USER FOUND");
		}
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return onlineFlatRentalJpaDao.save(user);
	}

	@Override
	public User updateUser(User user) {
		Optional<User> optional=onlineFlatRentalJpaDao.findById(user.getUserId());
		User user1=new User();
		if(optional.isPresent()) {
			user1.setUserId(user.getUserId());
			user1.setUserName(user.getUserName());
			user1.setUserType(user.getUserType());
			user1.setPassword(user.getPassword());
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
		return onlineFlatRentalJpaDao.save(use);
	}
	
	@Override
	public void removeUser(Integer userId) {
	//	User deletedUser=user;
		 onlineFlatRentalJpaDao.deleteById(userId);;
	//	return deletedUser;
		 
	}
	

}
