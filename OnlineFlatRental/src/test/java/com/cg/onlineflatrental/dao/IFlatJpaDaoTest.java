package com.cg.onlineflatrental.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.techjava.ticketbooking.model.Ticket;

@RunWith(SpringRunner.class)
@DataJpaTest
class IFlatJpaDaoTest {

	@Autowired 
	IFlatJpaDao iflatjpadao;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	 @Test
	    public void testNewFlat() throws Exception{
	        Flat flat = getTicket();
	        Flat saveInDb = testEntityManager.persist(ticket);
	        Flat getFromInDb = ticketBookingJpaDao.findById(saveInDb.getTicketId()).get();
	        assertThat(getFromInDb).isEqualTo(saveInDb);
	    }
	

}
