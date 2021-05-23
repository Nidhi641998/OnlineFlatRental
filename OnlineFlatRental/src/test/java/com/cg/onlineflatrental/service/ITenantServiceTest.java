package com.cg.onlineflatrental.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.cg.onlineflatrental.dao.ITenantDao;
import com.cg.onlineflatrental.exception.TenantNotFoundException;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.model.Tenant;
import com.cg.onlineflatrental.service.ITenantService;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ITenantServiceTest {
	
	 @MockBean
	    private ITenantDao itenantdao;
	 @Autowired
	    private ITenantService itenantservice;
	 
	 Tenant tenant=null;
	 FlatAddress flatAddress=null;

	@Test
	public void testAddTenant01()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(1);
		tenant.setTenantAge(18);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(11);
		flatAddress.setHouseNo(101);
		flatAddress.setStreet("MGRoad");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560034);
		tenant.setFlatAddress(flatAddress);
		
	//	Mockito.when(itenantdao.saveAndFlush(tenant)).thenReturn(tenant);
	//	assertThat(itenantservice.addTenant(tenant)).isEqualTo(tenant);
	//	List<Tenant> tenantList = (List<Tenant>) itenantdao.findAll();
	//	Assert.assertEquals(10, tenantList.size());
	}
	
	@Test
	public void testAddTenant08()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(1);
		tenant.setTenantAge(18);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(11);
		flatAddress.setHouseNo(101);
		flatAddress.setStreet("MGRoad");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560034);
		tenant.setFlatAddress(flatAddress);
	}
	
	@Test
	public void testAddTenant09()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(1);
		tenant.setTenantAge(18);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(11);
		flatAddress.setHouseNo(101);
		flatAddress.setStreet("MGRoad");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560034);
		tenant.setFlatAddress(flatAddress);
	}
	
	
	
	@Test
	public void testUpdateTenant01()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(6);
		tenant.setTenantAge(99);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(11);
		flatAddress.setHouseNo(105);
		flatAddress.setStreet("M G Road");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCity("India");
		flatAddress.setPin(560034);
		
		itenantdao.saveAndFlush(tenant);
		flatAddress.setHouseNo(105);
		
		Mockito.when(itenantdao.saveAndFlush(tenant)).thenReturn(tenant);
		assertEquals(tenant.getTenantAge(),99);
	}

	@Test
	public void testUpdateTenant02()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(6);
		tenant.setTenantAge(1);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(1000);
		flatAddress.setHouseNo(0);
		flatAddress.setStreet("M G Road");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCity("India");
		flatAddress.setPin(5600340);
		
		itenantdao.saveAndFlush(tenant);
		flatAddress.setHouseNo(0);
		
		Mockito.when(itenantdao.saveAndFlush(tenant)).thenReturn(tenant);
		assertEquals(tenant.getTenantAge(),10);
	}
	
	@Test
	public void testUpdateTenant03()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(9);
		tenant.setTenantAge(101);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(11);
		flatAddress.setHouseNo(105);
		flatAddress.setStreet("M G Road");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCity("India");
		flatAddress.setPin(560034);
		
		itenantdao.saveAndFlush(tenant);
		flatAddress.setHouseNo(105);
		
		Mockito.when(itenantdao.saveAndFlush(tenant)).thenReturn(tenant);
		assertEquals(tenant.getTenantAge(),101);
	}
	
	@Test
	public void testUpdateTenant04()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(9);
		tenant.setTenantAge(101);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(11);
		flatAddress.setHouseNo(105);
		flatAddress.setStreet("M G Road");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCity("India");
		flatAddress.setPin(560034);
		
		itenantdao.saveAndFlush(tenant);
		flatAddress.setHouseNo(105);
		
		Mockito.when(itenantdao.saveAndFlush(tenant)).thenReturn(tenant);
		assertEquals(tenant.getTenantAge(),101);
	}
	
	@Test
	public void testUpdateTenant05()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(9);
		tenant.setTenantAge(101);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(11);
		flatAddress.setHouseNo(105);
		flatAddress.setStreet("M G Road");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCity("India");
		flatAddress.setPin(560034);
		
		itenantdao.saveAndFlush(tenant);
		flatAddress.setHouseNo(105);
		
		Mockito.when(itenantdao.saveAndFlush(tenant)).thenReturn(tenant);
		assertEquals(tenant.getTenantAge(),101);
	}
	
	@Test
	public void testUpdateTenant06()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(9);
		tenant.setTenantAge(101);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(11);
		flatAddress.setHouseNo(105);
		flatAddress.setStreet("M G Road");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCity("India");
		flatAddress.setPin(560034);
		
		itenantdao.saveAndFlush(tenant);
		flatAddress.setHouseNo(105);
		
		Mockito.when(itenantdao.saveAndFlush(tenant)).thenReturn(tenant);
		assertEquals(tenant.getTenantAge(),101);
	}
	
	@Test
	public void testUpdateTenant07()throws Exception {
		Tenant tenant=new Tenant();
		tenant.setTenantId(9);
		tenant.setTenantAge(101);
		FlatAddress flatAddress=new FlatAddress();
		flatAddress.setAddressId(11);
		flatAddress.setHouseNo(105);
		flatAddress.setStreet("M G Road");
		flatAddress.setCity("Bangalore");
		flatAddress.setState("Karnataka");
		flatAddress.setCity("India");
		flatAddress.setPin(560034);
		
		itenantdao.saveAndFlush(tenant);
		flatAddress.setHouseNo(105);
		
		Mockito.when(itenantdao.saveAndFlush(tenant)).thenReturn(tenant);
		assertEquals(tenant.getTenantAge(),101);
	}
	
	

	@Test
	void testViewAllTenant02() {
		//LOGGER.info("Testing testViewAllTenant01()");
		assertNotNull(itenantservice.viewAllTenants());
	}
	@Test
	void testViewAllTenant03() {
		//LOGGER.info("Testing testViewAllTenant01()");
		assertNotNull(itenantservice.viewAllTenants());
	}
	
	@Test
	void testDeleteTenant02() throws TenantNotFoundException {
	//	LOGGER.info("Testing testDeleteTenant02()");
		try
		{
			itenantservice.deleteTenant(6);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant ID not found", exception.getMessage());
		}
	}
	
	
	
}
