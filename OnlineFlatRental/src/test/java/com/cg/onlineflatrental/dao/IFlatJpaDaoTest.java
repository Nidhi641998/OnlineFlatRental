package com.cg.onlineflatrental.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;




@RunWith(SpringRunner.class)
@DataJpaTest
class IFlatJpaDaoTest {

	@Autowired 
	IFlatJpaDao iflatjpadao;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	 @Test
	 public void testViewFlat() throws Exception
	 {
		 Flat flat=new Flat();
		 FlatAddress flatAddress=new FlatAddress();
		 flatAddress.setHouseNo(10);
			flatAddress.setCity("Bangalore");
			flatAddress.setStreet("nagpura");
			flatAddress.setState("Karnataka");
			flatAddress.setCountry("India");
			flatAddress.setPin(560086);
			
			flat.setCost((float) 2500);
			flat.setFlatAddress(flatAddress);
			flat.setAvailability("Yes");
			
			 Flat saveInDb = testEntityManager.persist(flat);
		     Flat getInDb = iflatjpadao.findById(flat.getFlatId()).get();
		     assertThat(getInDb).isEqualTo(saveInDb);
	 }
	 
	 @Test
	 public void testViewAllFlat() throws Exception
	 {
		 Flat flat1=new Flat();
		 FlatAddress flatAddress1=new FlatAddress();
		 flatAddress1.setHouseNo(10);
		 flatAddress1.setCity("Bangalore");
		 flatAddress1.setStreet("nagpura");
		 flatAddress1.setState("Karnataka");
		 flatAddress1.setCountry("India");
	   	 flatAddress1.setPin(560086);
			
		 flat1.setCost((float) 2500);
		 flat1.setFlatAddress(flatAddress1);
		 flat1.setAvailability("Yes");
		 
		 Flat flat2=new Flat();
		 FlatAddress flatAddress2=new FlatAddress();
		 flatAddress2.setHouseNo(150);
		 flatAddress2.setCity("Mysore");
		 flatAddress2.setStreet("Nandi Layout");
		 flatAddress2.setState("Kerala");
		 flatAddress2.setCountry("India");
	   	 flatAddress2.setPin(460020);
			
		 flat2.setCost((float) 16500);
		 flat2.setFlatAddress(flatAddress2);
		 flat2.setAvailability("No");
		 
		 
		  testEntityManager.persist(flat1);
	        testEntityManager.persist(flat2);

	        List<Flat> flatList = (List<Flat>) iflatjpadao.findAll();

	        Assert.assertEquals(2, flatList.size());
	 }
	 
	 @Test
	    public void testDeleteFlatById() throws Exception
	 {
		 Flat flat1=new Flat();
		 FlatAddress flatAddress1=new FlatAddress();
		 flatAddress1.setHouseNo(10);
		 flatAddress1.setCity("Bangalore");
		 flatAddress1.setStreet("nagpura");
		 flatAddress1.setState("Karnataka");
		 flatAddress1.setCountry("India");
	   	 flatAddress1.setPin(560086);
			
		 flat1.setCost((float) 2500);
		 flat1.setFlatAddress(flatAddress1);
		 flat1.setAvailability("Yes");
		 
		 Flat flat2=new Flat();
		 FlatAddress flatAddress2=new FlatAddress();
		 flatAddress2.setHouseNo(150);
		 flatAddress2.setCity("Mysore");
		 flatAddress2.setStreet("Nandi Layout");
		 flatAddress2.setState("Kerala");
		 flatAddress2.setCountry("India");
	   	 flatAddress2.setPin(460020);
			
		 flat2.setCost((float) 16500);
		 flat2.setFlatAddress(flatAddress2);
		 flat2.setAvailability("No");
		 
		 
		 Flat flat = testEntityManager.persist(flat1);
	     testEntityManager.persist(flat2);

	     testEntityManager.remove(flat);

	        List<Flat> flatList = (List<Flat>) iflatjpadao.findAll();
	        Assert.assertEquals(flatList.size(), 1);
	 }
	 
	 @Test
	    public void testUpdateFlat(){
		 
		 Flat flat=new Flat();
		 FlatAddress flatAddress=new FlatAddress();
		 flatAddress.setHouseNo(10);
			flatAddress.setCity("Bangalore");
			flatAddress.setStreet("nagpura");
			flatAddress.setState("Karnataka");
			flatAddress.setCountry("India");
			flatAddress.setPin(560086);
			
			flat.setCost((float) 2500);
			flat.setFlatAddress(flatAddress);
			flat.setAvailability("Yes");
		 
		 testEntityManager.persist(flat);

	        Flat getFromDb = iflatjpadao.findById(flat.getFlatId()).get();
	        getFromDb.setAvailability("Yes");
	        testEntityManager.persist(getFromDb);

	        assertThat(getFromDb.getAvailability()).isEqualTo("Yes");
	 }
	 
	 @Test
	    public void testAddFlat()
	    {
		 
		 Flat flat=new Flat();
		 FlatAddress flatAddress=new FlatAddress();
		 flatAddress.setHouseNo(10);
			flatAddress.setCity("Bangalore");
			flatAddress.setStreet("nagpura");
			flatAddress.setState("Karnataka");
			flatAddress.setCountry("India");
			flatAddress.setPin(560086);
			
			flat.setCost((float) 2500);
			flat.setFlatAddress(flatAddress);
			flat.setAvailability("Yes");
		 
		 
		 Flat saveToDb = testEntityManager.persist(flat);
			Flat getFromDb = iflatjpadao.findById(saveToDb.getFlatId()).get();
			assertEquals(getFromDb, saveToDb);
	    }
	 
	

}
