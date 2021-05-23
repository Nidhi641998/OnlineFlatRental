package com.cg.onlineflatrental.service;


import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.onlineflatrental.dao.ILandlordJpaDao;
import com.cg.onlineflatrental.exception.InvalidLandlordInputException;
import com.cg.onlineflatrental.exception.LandlordNotFoundException;
import com.cg.onlineflatrental.model.Landlord;
import com.cg.onlineflatrental.service.ILandlordService;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;



//@RunWith(SpringRunner.class)
@SpringBootTest
public class ILandlordServiceImplTest {
	
	@MockBean
	ILandlordJpaDao ilandlordjpadao;
	
	@Autowired
	ILandlordJpaDao ilandlordjpadao1;
	
	@Autowired
	ILandlordService ilandlordservice;
	
	Landlord landlord=null;
	Flat flat=null;
	FlatAddress flatAddress=null;
	
	@Test
	void testAddLandlord01() throws InvalidLandlordInputException 
	{
		
		FlatAddress flatAddress=new FlatAddress(10,"BataRoad","Gurgaon","Haryana",11038,"India");
		Flat flat=new Flat(78, (float) 20000, flatAddress,"Yes");
		Landlord landlord=new Landlord(10,"Ankur",11,flat);
		
		
		Mockito.when(ilandlordjpadao.saveAndFlush(landlord)).thenReturn(landlord);
        assertThat(ilandlordservice.addLandlord(landlord)).isEqualTo(landlord);
	}
	@Test
	void testAddLandlord02() throws InvalidLandlordInputException 
	{
		
		FlatAddress flatAddress=new FlatAddress(11,"EnglishBazar","Malda","West Bengal",741289,"India");
		Flat flat=new Flat(78, (float) 205555, flatAddress,"Yes");
		Landlord landlord=new Landlord(10,"Ankita",11,flat);
		
		
		Mockito.when(ilandlordjpadao.saveAndFlush(landlord)).thenReturn(landlord);
        assertThat(ilandlordservice.addLandlord(landlord)).isEqualTo(landlord);
	}
	@Test
	void testAddLandlord03() throws InvalidLandlordInputException 
	{
		
		FlatAddress flatAddress=new FlatAddress(11,"BTRoad","Gurgaon","Haryana",11038,"India");
		Flat flat=new Flat(78, (float) 20000, flatAddress,"Yes");
		Landlord landlord=new Landlord(10,"Aman",11,flat);
		
		
		Mockito.when(ilandlordjpadao.saveAndFlush(landlord)).thenReturn(landlord);
        assertThat(ilandlordservice.addLandlord(landlord)).isEqualTo(landlord);
	}
	@Test
    void testUpdateLandlord10() throws LandlordNotFoundException ,InvalidLandlordInputException{
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600013, "country");



        flat=new Flat(488, 1000f, flatAddress, "Y");
        landlord = new Landlord(245, "Name", 24, flat);
        assertNotNull(ilandlordservice.updateLandlord(landlord));
    }
	@Test
	void testUpdateLandlord11() throws LandlordNotFoundException,InvalidLandlordInputException{
		flatAddress = new FlatAddress(1, "street", "city", "state", 600013, "country");

		flat=new Flat(488, 1000f, flatAddress, "Y");
		landlord = new Landlord(6, "Name", 24, flat);
		try {
			ilandlordservice.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("No Landlord found in given ID", exception.getMessage());
		}
	}


	@Test
	void testViewAllLandlord25() {
		assertNotNull(ilandlordservice.viewAllLandlord());
	}
	@Test
	void testViewLandlord01() throws LandlordNotFoundException {
		try {
			assertEquals(25, ilandlordservice.viewLandlordById(26).getLandlordAge());
		} catch (LandlordNotFoundException exception) {
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	@Test
    public void testDeleteFlat01() throws Exception{
		FlatAddress flatAddress=new FlatAddress(10,"BataRoad","Gurgaon","Haryana",11038,"India");
		Flat flat=new Flat(78, (float) 20000, flatAddress,"Yes");
		Landlord landlord=new Landlord(10,"Ansh",11,flat);
		
	  

			Mockito.when(ilandlordjpadao.saveAndFlush(landlord)).thenReturn(landlord);
			ilandlordjpadao.deleteById(landlord.getLandlordId());
			assertNotEquals(landlord, new Landlord());
    }
	@Test
    public void testDeleteFlat02() throws Exception{
		FlatAddress flatAddress=new FlatAddress(11,"BareilyRoad","Kanpur","Uttar Pradesh",11038,"India");
		Flat flat=new Flat(78, (float) 30000, flatAddress,"Yes");
		Landlord landlord=new Landlord(10,"Ashu",11,flat);
		
	  

			Mockito.when(ilandlordjpadao.saveAndFlush(landlord)).thenReturn(landlord);
			ilandlordjpadao.deleteById(landlord.getLandlordId());
			assertNotEquals(landlord, new Landlord());
    }
	@Test
    public void testDeleteFlat03() throws Exception{
		FlatAddress flatAddress=new FlatAddress(11,"MainRoad","Deoghar","Jharkahnd",11038,"India");
		Flat flat=new Flat(78, (float) 32000, flatAddress,"Yes");
		Landlord landlord=new Landlord(10,"Anshika",11,flat);
		
	  

			Mockito.when(ilandlordjpadao.saveAndFlush(landlord)).thenReturn(landlord);
			ilandlordjpadao.deleteById(landlord.getLandlordId());
			assertNotEquals(landlord, new Landlord());
    }
	
	
	
	
	

}