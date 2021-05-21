package com.cg.onlineflatrental.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlineflatrental.dao.IFlatJpaDao;
import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;

@RunWith(SpringRunner.class)
@SpringBootTest
class IFlatServiceTest {
	@MockBean
	IFlatJpaDao iflatjpadao;
	
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
		//Flat flat1=iflatservice.addFlat(flat);
        assertThat(iflatservice.addFlat(flat)).isEqualTo(flat);
		//Flat flat1= (iflatservice.addFlat(flat));
		//assertNotNull(flat1);
//  Mockito.when(ticketBookingJpaDao.save(ticket)).thenReturn(ticket);
//        assertThat(ticketBookingService.createTicket(ticket)).isEqualTo(ticket);
	}
}
