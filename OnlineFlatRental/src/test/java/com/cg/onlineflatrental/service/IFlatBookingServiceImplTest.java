package com.cg.onlineflatrental.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.opentest4j.AssertionFailedError;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;

import com.cg.onlineflatrental.dao.IFlatBookingJpaDao;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.model.FlatBooking;
import com.cg.onlineflatrental.model.Tenant;
import com.cg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
@SpringBootTest
public class IFlatBookingServiceImplTest {
	@MockBean
	IFlatBookingJpaDao iflatjpadao;
	
	@Autowired
	IFlatBookingService iflatservice;
	
	FlatBooking flatBooking=null;
	Flat flat=null;
	FlatAddress flatAddress=null;
	Tenant tenant=null;
	
	@Test
	void testAddFlat01() throws FlatBookingNotFoundException , InvalidFlatInputException
	{
		flatAddress=new FlatAddress(10,"BtRoad","KalyaniA","West Bengal",741235,"India");
		flat=new Flat(78, (float) 30000, flatAddress,"Yes");
		tenant=new Tenant(10,"Ankur",10,flatAddress);
		flatBooking=new FlatBooking(10,flat,tenant,LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		
		Mockito.when(iflatjpadao.saveAndFlush(flatBooking)).thenReturn(flatBooking);
		//Flat flat1=iflatservice.addFlat(flat);
        assertThat(iflatservice.addFlatBooking1(flatBooking)).isEqualTo(flatBooking);
	}
	
	@Test
	void testAddFlat02() throws FlatBookingNotFoundException , InvalidFlatInputException{
		flatAddress=new FlatAddress(11,"Kankarbagh","Patna","Bihar",841428,"India");
		flat=new Flat(79, (float) 20000, flatAddress,"Yes");
		tenant=new Tenant(10,"Amit",11,flatAddress);
		flatBooking=new FlatBooking(11,flat,tenant,LocalDate.parse("2021-05-07"), LocalDate.parse("2022-05-07"));
		Mockito.when(iflatjpadao.saveAndFlush(flatBooking)).thenReturn(flatBooking);
		//Flat flat1=iflatservice.addFlat(flat);
        assertThat(iflatservice.addFlatBooking1(flatBooking)).isEqualTo(flatBooking);
		
		
		
	}
	@Test
	void testAddFlat03() throws FlatBookingNotFoundException , InvalidFlatInputException{
		flatAddress=new FlatAddress(12,"RKMVStreet","Deoghar","Jharkhand",841428,"India");
		flat=new Flat(80, (float) 40000, flatAddress,"Yes");
		tenant=new Tenant(12,"Adarsh",11,flatAddress);
		flatBooking=new FlatBooking(11,flat,tenant,LocalDate.parse("2021-03-07"), LocalDate.parse("2022-03-07"));
		Mockito.when(iflatjpadao.saveAndFlush(flatBooking)).thenReturn(flatBooking);
		//Flat flat1=iflatservice.addFlat(flat);
        assertThat(iflatservice.addFlatBooking1(flatBooking)).isEqualTo(flatBooking);
		
	}
	@Test
	void testAddFlat04() throws FlatBookingNotFoundException , InvalidFlatInputException{
		flatAddress=new FlatAddress(13,"Talwandi","Kota","Rajasthan",841428,"India");
		flat=new Flat(82, (float) 400500, flatAddress,"Yes");
		tenant=new Tenant(13,"Abhishek",11,flatAddress);
		flatBooking=new FlatBooking(11,flat,tenant,LocalDate.parse("2021-05-10"), LocalDate.parse("2022-05-16"));
		Mockito.when(iflatjpadao.saveAndFlush(flatBooking)).thenReturn(flatBooking);
		//Flat flat1=iflatservice.addFlat(flat);
        assertThat(iflatservice.addFlatBooking1(flatBooking)).isEqualTo(flatBooking);
		
	}
	@Test
	void testAddFlat05() throws FlatBookingNotFoundException , InvalidFlatInputException{
		flatAddress=new FlatAddress(14,"GorakhNathRoad","Gorakhpur","Uttar Pradesh",841428,"India");
		flat=new Flat(83, (float) 300500, flatAddress,"No");
		tenant=new Tenant(14,"Ankush",11,flatAddress);
		flatBooking=new FlatBooking(14,flat,tenant,LocalDate.parse("2021-04-10"), LocalDate.parse("2022-04-16"));
		Mockito.when(iflatjpadao.saveAndFlush(flatBooking)).thenReturn(flatBooking);
		//Flat flat1=iflatservice.addFlat(flat);
        assertThat(iflatservice.addFlatBooking1(flatBooking)).isEqualTo(flatBooking);
		
	}
	@Test
	void testAddFlat06() throws FlatBookingNotFoundException , InvalidFlatInputException{
		flatAddress=new FlatAddress(16,"Garia","Kolkata","West Bengal",741236,"India");
		flat=new Flat(86, (float) 300555, flatAddress,"No");
		tenant=new Tenant(16,"Amartya",11,flatAddress);
		flatBooking=new FlatBooking(16,flat,tenant,LocalDate.parse("2021-03-10"), LocalDate.parse("2022-03-16"));
		Mockito.when(iflatjpadao.saveAndFlush(flatBooking)).thenReturn(flatBooking);
		//Flat flat1=iflatservice.addFlat(flat);
        assertThat(iflatservice.addFlatBooking1(flatBooking)).isEqualTo(flatBooking);
		
	}
	
	@Test
	void testAddFlat07() throws FlatBookingNotFoundException , InvalidFlatInputException{
		flatAddress=new FlatAddress(16,"ThanaRoad","Motihari","Bihar",741236,"India");
		flat=new Flat(87, (float) 300555, flatAddress,"No");
		tenant=new Tenant(17,"Ansh",11,flatAddress);
		flatBooking=new FlatBooking(17,flat,tenant,LocalDate.parse("2021-05-11"), LocalDate.parse("2022-05-18"));
		Mockito.when(iflatjpadao.saveAndFlush(flatBooking)).thenReturn(flatBooking);
		//Flat flat1=iflatservice.addFlat(flat);
        assertThat(iflatservice.addFlatBooking1(flatBooking)).isEqualTo(flatBooking);
		
	}
	
	
	
	@Test
	void testViewAllFlatBooking01() {
		assertNotNull(iflatservice.viewAllFlatBooking());
	}

	private void assertNotNull(List<FlatBooking> viewAllFlatBooking) {
		// TODO Auto-generated method stub

	}

	private void assertNull(List<FlatBooking> viewAllFlatBooking) {
		// TODO Auto-generated method stub
	}
	@AfterAll
	public static void end() {
		//LOGGER.info("FlatBooking Testing Terminated");
	}
	@Test
	void testViewAllFlatBooking02() throws FlatBookingNotFoundException {
		try {
			assertNull(iflatservice.viewAllFlatBooking());
		} catch (AssertionFailedError exception) {
			assertNotNull(iflatservice.viewAllFlatBooking());
		}
	}
	
	

}
