package com.cg.onlineflatrental.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.Assert;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlineflatrental.model.*;
import com.cg.onlineflatrental.model.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class IUserJpaDaoTest {
	@Autowired 
	IUserJpaDao onlineFlatRentalJpaDao;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	
	@Test
	 void testViewUser() {
		User user=new User();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		
		User saveDb=testEntityManager.persist(user);
		User getDb=onlineFlatRentalJpaDao.findById(user.getUserId()).get();
		assertThat(getDb).isEqualTo(saveDb);
	} 
	
	@Test
	void testViewAllUser() {
		User user1=new User();
		user1.setUserName("aps");
		user1.setPassword("asp");
		user1.setUserType("landlord");
		
		User user2=new User();
		user2.setUserName("xyz");
		user2.setPassword("xzy");
		user2.setUserType("landlord");
		
		testEntityManager.persist(user1);
		testEntityManager.persist(user2);
		
		List<User> userList= onlineFlatRentalJpaDao.findAll();
		 Assert.assertEquals(2, userList.size());
		
	}
	
	@Test
	void testRemoveUser() {
		User user1=new User();
		user1.setUserName("aps");
		user1.setPassword("asp");
		user1.setUserType("landlord");
		
		User user2=new User();
		user2.setUserName("xyz");
		user2.setPassword("xzy");
		user2.setUserType("landlord");
		
		User user=testEntityManager.persist(user1);
		testEntityManager.persist(user2);
		testEntityManager.remove(user);
		
		List<User> userList=onlineFlatRentalJpaDao.findAll();
		Assert.assertEquals(1,userList.size());
	}
	
	@Test
	void testUpdateUser() {
		User user=new User();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		
		testEntityManager.persist(user);
		User getDb=onlineFlatRentalJpaDao.findById(user.getUserId()).get();
		getDb.setUserName("pqr");
		testEntityManager.persist(getDb);
		assertThat(getDb.getUserName()).isEqualTo("pqr");
	}
	
	@Test
	void testAddUser() {
		User user=new User();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		
		User addDb=testEntityManager.persist(user);
		User getDb=onlineFlatRentalJpaDao.findById(user.getUserId()).get();
		assertEquals(addDb,getDb);
	}
	
	@Test
	void testUpdatePassword() {
		User user=new User();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		testEntityManager.persist(user);
		
		User updateDb=onlineFlatRentalJpaDao.findById(user.getUserId()).get();
		updateDb.setPassword("newpass");
		testEntityManager.persist(updateDb);
		assertThat(updateDb.getPassword()).isEqualTo("newpass");
	
	}  
	
	@Test
	void testValidateUser() {
		User user=new User();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		testEntityManager.persist(user);
		
		User getDb=onlineFlatRentalJpaDao.findById(user.getUserId()).get();
		assertThat(getDb.getUserName()).isEqualTo("aps");
		assertThat(getDb.getPassword()).isEqualTo("asp");

	}
	

}
