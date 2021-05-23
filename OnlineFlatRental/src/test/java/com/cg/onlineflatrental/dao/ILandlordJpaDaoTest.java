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
import com.cg.onlineflatrental.model.Landlord;
@RunWith(SpringRunner.class)
@DataJpaTest

public class ILandlordJpaDaoTest {
	@Autowired 
	ILandlordJpaDao ilandlordjpadao;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Test
	 public void testViewLandlord() throws Exception
	 {
		 Landlord landlord = new Landlord();
	    	Flat flat = new Flat();
	    	FlatAddress flatAddress=new FlatAddress();
	    	
	    	landlord.setLandlordId(3);
	    	landlord.setLandlordName("Vijeta Choudhary");
	    	landlord.setLandlordAge(23);
	    	
	    	
		 flatAddress.setHouseNo(10);
			flatAddress.setCity("Bangalore");
			flatAddress.setStreet("Kormangala");
			flatAddress.setState("Karnataka");
			flatAddress.setCountry("India");
			flatAddress.setPin(560086);
			
			
			
			flat.setCost((float) 2500);
			flat.setFlatAddress(flatAddress);
			flat.setAvailability("Yes");
			
			Landlord saveInDb = testEntityManager.persist(landlord);
		     Landlord getInDb = ilandlordjpadao.findById(landlord.getLandlordId()).get();
		     assertThat(getInDb).isEqualTo(saveInDb);
	 }
	
	@Test
	 public void testViewAllLandlord() throws Exception
	 {
		 Landlord landlord1 = new Landlord();
	    	Flat flat1 = new Flat();
	    	FlatAddress flatAddress1=new FlatAddress();
	    	
	    	landlord1.setLandlordId(3);
	    	landlord1.setLandlordName("Vijeta Choudhary");
	    	landlord1.setLandlordAge(23);
	    	landlord1.setFlat(flat1);
	    	
		 flatAddress1.setHouseNo(100);
		 flatAddress1.setCity("Bangalore");
		 flatAddress1.setStreet("nagpura");
		 flatAddress1.setState("Karnataka");
		 flatAddress1.setCountry("India");
	   	 flatAddress1.setPin(560086);
			
		 flat1.setCost((float) 2500);
		 flat1.setFlatAddress(flatAddress1);
		 flat1.setAvailability("Yes");
		 
		 
		Landlord landlord2 = new Landlord();
		Flat flat2 = new Flat();
    	FlatAddress flatAddress2=new FlatAddress();
		landlord2.setLandlordId(3);
    	landlord2.setLandlordName("Vijeta Choudhary");
    	landlord2.setLandlordAge(23);
    	landlord2.setFlat(flat2);
		 flatAddress2.setHouseNo(10);
		 flatAddress2.setCity("Faridabad");
		 flatAddress2.setStreet("Ballabgarh");
		 flatAddress2.setState("Haryana");
		 flatAddress2.setCountry("India");
	   	 flatAddress2.setPin(360020);
			
		 flat2.setCost((float) 16500);
		 flat2.setFlatAddress(flatAddress2);
		 flat2.setAvailability("Yes");
		 
		 
		  testEntityManager.persist(landlord1);
	        testEntityManager.persist(landlord2);

	        List<Landlord> landlordList = (List<Landlord>) ilandlordjpadao.findAll();

	        Assert.assertEquals(2, landlordList.size());
	 }
	
	@Test
   public void testUpdateLandlord(){
	 Landlord landlord=new Landlord();
	 Flat flat=new Flat();
	 FlatAddress flatAddress=new FlatAddress();
	 
	 landlord.setLandlordId(3);
	 landlord.setLandlordName("Vijeta Choudhary");
	 landlord.setLandlordAge(23);
	 
	 flatAddress.setHouseNo(100);
		flatAddress.setCity("Bangalore");
		flatAddress.setStreet("Kormangala");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560086);
		
		flat.setCost((float) 2500);
		flat.setFlatAddress(flatAddress);
		flat.setAvailability("Yes");
		
	 }
	 
	 
	 
	 
	 @Test
	    public void testAddLandlord()
	    {
		 
		Landlord landlord=new Landlord();
		 Flat flat=new Flat();
		 FlatAddress flatAddress=new FlatAddress();
		 
		 landlord.setLandlordId(3);
	 	 landlord.setLandlordName("Vijeta Choudhary");
	 	 landlord.setLandlordAge(23);
	 	 
		    flatAddress.setHouseNo(100);
			flatAddress.setCity("Bangalore");
			flatAddress.setStreet("Kormangala");
			flatAddress.setState("Karnataka");
			flatAddress.setCountry("India");
			flatAddress.setPin(560086);
			
			flat.setCost((float) 2500);
			flat.setFlatAddress(flatAddress);
			flat.setAvailability("Yes");
			
		
		 
			Landlord saveInDb = testEntityManager.persist(landlord);
			Landlord getInDb = ilandlordjpadao.findById(landlord.getLandlordId()).get();
		     assertThat(getInDb).isEqualTo(saveInDb);
			
	    }


	
	 
	 
	

}
