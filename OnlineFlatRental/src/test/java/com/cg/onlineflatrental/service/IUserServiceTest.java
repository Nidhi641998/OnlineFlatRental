package com.cg.onlineflatrental.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.onlineflatrental.dao.IUserJpaDao;
import com.cg.onlineflatrental.model.*;
import com.cg.onlineflatrental.service.IUserService;

@SpringBootTest
public class IUserServiceTest { 
	@MockBean
	IUserJpaDao onlineFlatRentalJpaDao;
	
	@Autowired
	IUserJpaDao onlineFlatRentalJpaDao1;
	
	@Autowired
	IUserService iUserService;
	
	User user=null;
	
	
	@Test
	void testAddUser() {
		user=new User(11,"asp","aps","owner");
		Mockito.when(onlineFlatRentalJpaDao.save(user)).thenReturn(user);
		assertThat(iUserService.addUser(user)).isEqualTo(user);
	}
	
	@Test
	void testUpdateUser() {
		User user=new User();
		user.getUserId();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("owner");
		onlineFlatRentalJpaDao.save(user);
		user.setUserType("landlord");
		Mockito.when(onlineFlatRentalJpaDao.save(user)).thenReturn(user);
		assertEquals( "landlord",user.getUserType());
		
	}
	
	@Test
	void testViewUser() {
		User user=new User();
		user.setUserId(5);
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		Mockito.when(onlineFlatRentalJpaDao.save(user)).thenReturn(user);
		assertEquals(5,user.getUserId()); 
	} 
	
	@Test
	void testViewAllUser() {
		User user1=new User();
		user1.setUserName("aps");
		user1.setPassword("asp");
		user1.setUserType("landlord");
		
		User user2=new User();
		user2.setUserName("xyz");
		user2.setPassword("yxz");
		user2.setUserType("student");
		
		List<User> userList=new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		
		Mockito.when(onlineFlatRentalJpaDao.findAll()).thenReturn(userList);
		assertThat(onlineFlatRentalJpaDao.findAll()).isEqualTo(userList);
		
	}
	
	@Test
	void testRemoveUser() {
		User user=new User();
		user.setUserId(5);
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		
		Mockito.when(onlineFlatRentalJpaDao.save(user)).thenReturn(user);
		onlineFlatRentalJpaDao.deleteById(user.getUserId());
		assertNotEquals(user,new User());
	} 
	
	@Test
	void testValidateUser() {
		User user=new User();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		Mockito.when(onlineFlatRentalJpaDao.save(user)).thenReturn(user);
		assertThat(user.getUserName()).isEqualTo("aps");
		assertThat(user.getPassword()).isEqualTo("asp");

	} 
	
	@Test
	void testUpdatePassword() {
		User user=new User();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		onlineFlatRentalJpaDao.save(user);
		user.setPassword("xyz");
		Mockito.when(onlineFlatRentalJpaDao.save(user)).thenReturn(user);
		assertThat(user.getPassword()).isEqualTo("xyz");
	} 
	

}
