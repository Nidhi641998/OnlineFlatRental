package com.cg.onlineflatrental.dao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.model.FlatBooking;
@RunWith(SpringRunner.class)
@DataJpaTest
class IFlatBookingJpaDaoTest {
	@Autowired 
	IFlatBookingJpaDao iflatjpadao;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	
	/*@Test
    public void testNewFlatBooking() throws Exception{
		FlatBooking flatBooking = new FlatBooking();
    	Flat flat = new Flat();
    	FlatAddress flatAddress=new FlatAddress();
    	
    	
    	flatAddress.setHouseNo(10);
		flatAddress.setCity("Bangalore");
		flatAddress.setStreet("nagpura");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560086);
		
		flat.setCost((float) 2500);
		flat.setFlatAdress(flatAddress);
		flat.setAvialibilty("Yes");
		
		flatBooking.setBookingNo(10);
    	FlatBooking saveInDb = testEntityManager.persist(flatBooking);
    	FlatBooking getFromInDb = iflatjpadao.findById(saveInDb.getBookingNo()).get();
        assertThat(getFromInDb).isEqualTo(saveInDb);
        
        
        
    }*/
	 @Test
	 public void testViewFlatBooking() throws Exception
	 {
		 FlatBooking flatBooking = new FlatBooking();
	    	Flat flat = new Flat();
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
			
			flatBooking.setFlat(flat);
			flatBooking.setBookingNo(10);
			
			FlatBooking saveInDb = testEntityManager.persist(flatBooking);
		     FlatBooking getInDb = iflatjpadao.findById(flatBooking.getBookingNo()).get();
		     assertThat(getInDb).isEqualTo(saveInDb);
	 }
	 
	 
	 @Test
	 public void testViewAllFlat() throws Exception
	 {
		 FlatBooking flatBooking1 = new FlatBooking();
	    	Flat flat1 = new Flat();
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
		 
		 flatBooking1.setBookingNo(10);
		 
		 FlatBooking flatBooking2 = new FlatBooking();
	    	Flat flat2 = new Flat();
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
		 
		 flatBooking2.setBookingNo(10);
		 
		 
		  testEntityManager.persist(flatBooking1);
	        testEntityManager.persist(flatBooking2);

	        List<FlatBooking> flatList = (List<FlatBooking>) iflatjpadao.findAll();

	        Assert.assertEquals(2, flatList.size());
	 }
	 
	 
	 @Test
	    public void testUpdateFlat(){
		 //FlatBooking flatBooking1 = new FlatBooking();
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
			
			/*flatBooking1.setBookingNo(10);
			flatBooking1.setBookingFromDate(LocalDate.parse("2021-02-04"));
		 
		 testEntityManager.persist(flatBooking1);

	        FlatBooking getFromDb = iflatjpadao.findById(LocalDate)(flatBooking1.getBookingFromDate()).get();
	        getFromDb.setBookingFromDate(LocalDate.parse("2021-02-04"));
	        testEntityManager.persist(getFromDb);

	        assertThat(getFromDb.setBookingFromDate()).isEqualTo(LocalDate.parse("2021-02-04"));*/
			testEntityManager.persist(flat);

	       // Flat getFromDb = iflatjpadao.findById(flat.getFlatId()).get();
	       // getFromDb.setAvialibilty("Yes");
	     //   testEntityManager.persist(getFromDb);

	     //   assertThat(getFromDb.getAvialibilty()).isEqualTo("Yes");
	 }
	 
	 
	 
	 
	 @Test
	    public void testAddFlat()
	    {
		 
		 FlatBooking flatBooking = new FlatBooking();
	    	Flat flat = new Flat();
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
			
			flatBooking.setBookingNo(10);
		 
		 
			FlatBooking saveToDb = testEntityManager.persist(flatBooking);
			FlatBooking getFromDb = iflatjpadao.findById(saveToDb.getBookingNo()).get();//findById
			assertEquals(getFromDb, saveToDb);
			
	    }

	

}
