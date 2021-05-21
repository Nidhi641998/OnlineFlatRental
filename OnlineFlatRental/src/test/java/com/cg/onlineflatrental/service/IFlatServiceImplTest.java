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
import com.cg.onlineflatrental.dao.IFlatJpaDao;
import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;



//@RunWith(SpringRunner.class)
@SpringBootTest
public class IFlatServiceImplTest {
	
	@MockBean
	IFlatJpaDao iflatjpadao;
	
	@Autowired
	IFlatJpaDao iflatjpadao1;
	
	@Autowired
	IFlatService iflatservice;
	
	Flat flat=null;
	FlatAddress flatAddress=null;
	
	@Test
	void testAddFlat01() throws FlatNotFoundException , InvalidFlatInputException
	{
		flatAddress=new FlatAddress(10,"Rajajinagar","Bangalore","Karnataka",560086,"India");
		flat=new Flat(78, (float) 20000, flatAddress,"Yes");
		Mockito.when(iflatjpadao.saveAndFlush(flat)).thenReturn(flat);
        assertThat(iflatservice.addFlat(flat)).isEqualTo(flat);
		//Flat flat1= (iflatservice.addFlat(flat));
		//assertNotNull(flat1);
	}
	
	
	
	
	@Test
	void testAddFlat02() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 3456.0f, flatAddress, "");
		try {
			iflatservice.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Availability cannot be Empty", exception.getMessage());
		}
	}

	
	@Test
	public void testAddFlat03() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(30,"Kormangala","Tumkur","Kerala",460010,"India");
		Flat flat=new Flat(45, (float) 30000, flatAddress,"Available");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Availability can be only [YES | NO | Yes | No | yes | no | Y | N | y | n]", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat04() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(40,"Jalahalli","Tumkur","Tamil Nadu",360010,"India");
		Flat flat=new Flat(16, (float) 0, flatAddress,"No");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Cost cannot be empty or 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat05() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(-5,"Jalahalli","Mangalore","Delhi",260010,"India");
		Flat flat=new Flat( 10 , (float) 6050, flatAddress,"yes");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("House Number cannot be Empty or 0 or Negative", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat06() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(60,"","Bangalore","Karnataka",560780,"India");
		Flat flat=new Flat(150, (float) 6050, flatAddress,"No");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Street cannot be Empty", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat07() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(70,"Vidyaran@ya Pura","Gokarna","Maharashtra",720780,"India");
		Flat flat=new Flat(50, (float) 22890, flatAddress,"no");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Street cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat08() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(8,"Vidyaranyapura","","Karnataka",560780,"India");
		Flat flat=new Flat(10, (float) 10050, flatAddress,"yes");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("City cannot be Empty", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat09() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(19,"Kormangala","Bangal1ore","Andra Pradesh",860700,"India");
		Flat flat=new Flat(25, (float) 32000, flatAddress,"Y");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat10() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(100,"NandiniLayout","Bangalore","",526700,"India");
		Flat flat=new Flat(63, (float) 7500, flatAddress,"n");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("State cannot be Empty", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat11() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(11,"Nagpura","Bangalore","Karna*taka",560780,"India");
		Flat flat=new Flat(54, (float) 10600, flatAddress,"n");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat12() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(32,"Baswangudi","Mysore","Karnataka",560780,"");
		Flat flat=new Flat(32, (float) 29600, flatAddress,"yes");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Country cannot be Empty", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat13() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(3,"Baswangudi","Mysore","Uttar Pradesh",460780,"Ind9ia");
		Flat flat=new Flat(89, (float) 2800, flatAddress,"no");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat14() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(32,"Magadi","Gokarna","Goa",-960780,"India");
		Flat flat=new Flat(45, (float) 9600, flatAddress,"yes");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("PinCode cannot be negative", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat15() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(32,"Magadi","Gokarna","Goa",60780,"India");
		Flat flat=new Flat(32, (float) 29600, flatAddress,"Yes");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("PinCode should be of length 6", exception.getMessage());
		}
	}
	
/*	@Test
	public void testAddFlat16() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(3,"Baswangudi","Sirsi","Kashmir",56780,"India");
		Flat flat=new Flat(15, (float) 2600, flatAddress,"no");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("PinCode cannot contain Alphabets or Special Characters", exception.getMessage());
		}
	}*/
	
	@Test
	public void testUpdateFlat01() throws FlatNotFoundException, InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(32,"Magadi","Gokarna","Goa",60780,"India");
		Flat flat=new Flat(32, (float) 29600, flatAddress,"Yes");
		try
		{
			Flat flat1=iflatservice.updateFlat(flat);
			assertEquals("Magadi", flat1.getFlatAddress().getStreet());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	
	@Test
	void testUpdateFlat18()  {
		Flat flat=new Flat();
		FlatAddress flatAddress=new FlatAddress();
		flat.getFlatId();
		flatAddress.setHouseNo(10);
		flatAddress.setCity("Bangalore");
		flatAddress.setStreet("nagpura");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560086);
		
		flat.setCost((float) 2500);
		flat.setFlatAddress(flatAddress);
		flat.setAvailability("Yes");
        iflatjpadao.save(flat);

       flatAddress.setPin(56008);
        Mockito.when(iflatjpadao.save(flat)).thenReturn(flat);
		assertEquals(flat.getFlatAddress().getPin(), 56008);
    
	}
	@Test
	void testUpdateFlat19() throws InvalidFlatInputException, FlatNotFoundException {
		Flat flat=new Flat();
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setHouseNo(10);
		flatAddress.setCity("Bangalore");
		flatAddress.setStreet("nagpura");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560086);
		flat.getFlatId();
		flat.setCost((float) 2500);
		flat.setFlatAddress(flatAddress);
		flat.setAvailability("Yes");
        iflatjpadao.save(flat);

        
        Mockito.when(iflatjpadao.save(flat)).thenReturn(flat);
		assertEquals(flat.getAvailability(), "Yes");
    
		
		
	}
	
	  @Test
	    public void testViewFlat() throws Exception{
		  
		  Flat flat = new Flat();
	       FlatAddress flatAddress=new FlatAddress();
	            flat.setFlatId(1);
	            flatAddress.setHouseNo(10);
				flatAddress.setCity("Bangalore");
				flatAddress.setStreet("nagpura");
				flatAddress.setState("Karnataka");
				flatAddress.setCountry("India");
				flatAddress.setPin(560086);
				
			//	flat.setFlatId(100);
				flat.setCost((float) 2500);
				flat.setFlatAddress(flatAddress);
				flat.setAvailability("Yes");
				
				Mockito.when(iflatjpadao.save(flat)).thenReturn(flat);
				assertEquals(flat.getFlatId(),1);
	    }
		
	  
	  @Test
	    public void testViewAllFlat() throws Exception{
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

	        List<Flat> ticketList = new ArrayList<>();
	        ticketList.add(flat1);
	        ticketList.add(flat2);

	        Mockito.when(iflatjpadao.findAll()).thenReturn(ticketList);
	        assertThat(iflatjpadao.findAll()).isEqualTo(ticketList);
	    }
	  
	  @Test
	    public void testDeleteFlat() throws Exception{
		  Flat flat = new Flat();
	       FlatAddress flatAddress=new FlatAddress();
	       		flat.setFlatId(3); 
	       		flat.getFlatId();
	       		flatAddress.setHouseNo(10);
				flatAddress.setCity("Bangalore");
				flatAddress.setStreet("nagpura");
				flatAddress.setState("Karnataka");
				flatAddress.setCountry("India");
				flatAddress.setPin(560086);
				
				//flat.setFlatId(100);
				flat.setCost((float) 2500);
				flat.setFlatAddress(flatAddress);
				flat.setAvailability("Yes");

				Mockito.when(iflatjpadao.save(flat)).thenReturn(flat);
				iflatjpadao.deleteById(flat.getFlatId());
				assertNotEquals(flat, new Flat());
	    }
	  
	  @Test
		void testViewAllFlatByCost01() throws InvalidFlatInputException, FlatNotFoundException {

			try {
				iflatservice.findByCostAndAvailability((float)0,"Yes");
			} catch (InvalidFlatInputException exception) {
				assertEquals("Cost cannot be empty or 0 or negative", exception.getMessage());
			}
		}
	  
	  @Test
		void testViewAllFlatByCost02() throws InvalidFlatInputException, FlatNotFoundException {

			try {
				iflatservice.findByCostAndAvailability((float)3200, "available");
			} catch (InvalidFlatInputException exception) {
				assertEquals("Availability can be only [YES | NO | Yes | No | yes | no | Y | N | y | n]", exception.getMessage());
			}
		}

		@Test
		void testViewAllFlatByCost03() throws InvalidFlatInputException, FlatNotFoundException {

			try {
				iflatservice.findByCostAndAvailability((float)3200, "");
			} catch (InvalidFlatInputException exception) {
				assertEquals("Availability cannot be Empty", exception.getMessage());
			}
		}

		@Test
		void testViewAllFlatByCost04() throws InvalidFlatInputException, FlatNotFoundException {

			try {

				assertNotNull(iflatservice.findByCostAndAvailability((float)3200, "Yes"));
			} catch (FlatNotFoundException exception) {
				assertEquals("No Flat available for given cost", exception.getMessage());
			}
		}

		@Test
		void testViewAllFlat1() {
			try {
				assertNull(iflatservice.viewAllFlat());
			} catch (AssertionFailedError exception) {
				assertNotNull(iflatservice.viewAllFlat());
			}

		}
		
		@Test
		void testDeleteFlat1() throws FlatNotFoundException {
			try {
				assertNull(iflatservice.deleteFlatById(26));
			} catch (FlatNotFoundException exception) {
				assertEquals("flat with given id was not found", exception.getMessage());
			}
		}

		@Test
		void testViewFlat01() throws FlatNotFoundException {
			try {
				assertEquals(1200, iflatservice.viewFlat(26).getCost());
			} catch (FlatNotFoundException exception) {
				assertEquals("flat with given id was not found", exception.getMessage());
			}
		}

		/*
		 * @Test
	void testUpdateFlat02() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 3456.0f, flatAddress, "");
		try {
			iflatservice.updateFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Availability cannot be Empty", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
		
	}

	
	@Test
	public void testUpdateFlat03() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(30,"Kormangala","Tumkur","Kerala",460010,"India");
		Flat flat=new Flat(45, (float) 30000, flatAddress,"Available");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Availability can be only [YES | NO | Yes | No | yes | no | Y | N | y | n]", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat04() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(40,"Jalahalli","Tumkur","Tamil Nadu",360010,"India");
		Flat flat=new Flat(16, (float) 0, flatAddress,"No");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Cost cannot be empty or 0 or negative", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat05() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(-5,"Jalahalli","Mangalore","Delhi",260010,"India");
		Flat flat=new Flat( 10 , (float) 6050, flatAddress,"yes");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("House Number cannot be Empty or 0 or Negative", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat06() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(60,"","Bangalore","Karnataka",560780,"India");
		Flat flat=new Flat(150, (float) 6050, flatAddress,"No");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Street cannot be Empty", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat07() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(70,"Vidyaran@ya Pura","Gokarna","Maharashtra",720780,"India");
		Flat flat=new Flat(50, (float) 22890, flatAddress,"no");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Street cannot contain Numbers or Special Characters", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat08() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(8,"Vidyaranyapura","","Karnataka",560780,"India");
		Flat flat=new Flat(10, (float) 10050, flatAddress,"yes");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("City cannot be Empty", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat09() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(19,"Kormangala","Bangal1ore","Andra Pradesh",860700,"India");
		Flat flat=new Flat(25, (float) 32000, flatAddress,"Y");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat10() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(100,"NandiniLayout","Bangalore","",526700,"India");
		Flat flat=new Flat(63, (float) 7500, flatAddress,"n");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("State cannot be Empty", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat11() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(11,"Nagpura","Bangalore","Karna*taka",560780,"India");
		Flat flat=new Flat(54, (float) 10600, flatAddress,"n");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat12() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(32,"Baswangudi","Mysore","Karnataka",560780,"");
		Flat flat=new Flat(32, (float) 29600, flatAddress,"yes");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Country cannot be Empty", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat13() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(3,"Baswangudi","Mysore","Uttar Pradesh",460780,"Ind9ia");
		Flat flat=new Flat(89, (float) 2800, flatAddress,"no");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat14() throws InvalidFlatInputException, FlatNotFoundException
	{
		FlatAddress flatAddress=new FlatAddress(32,"Magadi","Gokarna","Goa",-960780,"India");
		Flat flat=new Flat(45, (float) 9600, flatAddress,"yes");
		try
		{
		iflatservice.updateFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("PinCode cannot be negative", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
	
	@Test
	public void testUpdateFlat15() throws InvalidFlatInputException, FlatNotFoundException
	{
		flatAddress=new FlatAddress(10,"Rajajinagar","Bangalore","Karnataka",560086,"India");
		flat=new Flat(78, (float) 20000, flatAddress,"Yes");
		iflatservice.addFlat(flat);
		FlatAddress flatAddress1=new FlatAddress(32,"Magadi","Gokarna","Goa",6070,"India");
		Flat flat1=new Flat(flat.getFlatId() , (float) 29600, flatAddress1,"Yes");
		try
		{
		iflatservice.updateFlat(flat1);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("PinCode should be of length 6", exception.getMessage());
		}
		catch(FlatNotFoundException exception)
		{
			assertEquals("flat with given id was not found", exception.getMessage());
		}
	}
		 */


}


