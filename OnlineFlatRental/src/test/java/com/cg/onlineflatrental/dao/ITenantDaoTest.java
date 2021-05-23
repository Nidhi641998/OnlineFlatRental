package com.cg.onlineflatrental.dao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.cg.onlineflatrental.dao.ITenantDao;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.model.Tenant;



@RunWith(SpringRunner.class)
@DataJpaTest
public class ITenantDaoTest {

	@Autowired 
	ITenantDao itenantdao;

	@Autowired
	TestEntityManager testEntityManager;

	@Test
	public void testViewTenant() throws Exception
	{
		Tenant tenant=new Tenant();
		FlatAddress flatAddress=new FlatAddress();

		flatAddress.setHouseNo(10);
		flatAddress.setCity("Bangalore");
		flatAddress.setStreet("nagpura");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India"); 
		flatAddress.setPin(560086);
		tenant.setTenantId(19);
		tenant.setTenantAge(20);

		Tenant saveInDb = testEntityManager.persist(tenant);
		Tenant getInDb = itenantdao.findById(tenant.getTenantId()).get();
		assertThat(getInDb).isEqualTo(saveInDb);

	}
	@Test
	public void testViewAllTenant() throws Exception
	{
		Tenant tenant=new Tenant();
		FlatAddress flatAddress=new FlatAddress();

		flatAddress.setHouseNo(10);
		flatAddress.setCity("Bangalore");
		flatAddress.setStreet("nagpura");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560086);
		tenant.setTenantId(19);
		tenant.setTenantAge(20);

		Tenant tenant2=new Tenant();
		FlatAddress flatAddress2=new FlatAddress();
		flatAddress2.setHouseNo(150);
		flatAddress2.setCity("Mysore");
		flatAddress2.setStreet("Nandi Layout");
		flatAddress2.setState("Kerala");
		flatAddress2.setCountry("India");
		flatAddress2.setPin(460020);
		tenant2.setTenantId(22);
		tenant2.setTenantAge(30);

		testEntityManager.persist(tenant);
		testEntityManager.persist(tenant2);

		List<Tenant> tenantList = (List<Tenant>) itenantdao.findAll();

		Assert.assertEquals(2, tenantList.size());

	}

	@Test
	public void testDeleteTenantById() throws Exception
	{
		Tenant tenant=new Tenant();
		FlatAddress flatAddress=new FlatAddress();

		flatAddress.setHouseNo(10);
		flatAddress.setCity("Bangalore");
		flatAddress.setStreet("nagpura");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560086);
		tenant.setTenantId(19);
		tenant.setTenantAge(20);

		Tenant tenant2=new Tenant();
		FlatAddress flatAddress2=new FlatAddress();
		flatAddress2.setHouseNo(150);
		flatAddress2.setCity("Mysore");
		flatAddress2.setStreet("Nandi Layout");
		flatAddress2.setState("Kerala");
		flatAddress2.setCountry("India");
		flatAddress2.setPin(460020);
		tenant2.setTenantId(22);
		tenant2.setTenantAge(30);

		Tenant tenant1 = testEntityManager.persist(tenant);
		testEntityManager.persist(tenant2);

		testEntityManager.remove(tenant1);

		List<Tenant> tenantList = (List<Tenant>) itenantdao.findAll();
		Assert.assertEquals(tenantList.size(), 1);

	}
	@Test
	public void testUpdateTenant(){
		Tenant tenant=new Tenant();
		FlatAddress flatAddress=new FlatAddress();

		flatAddress.setHouseNo(10);
		flatAddress.setCity("Bangalore");
		flatAddress.setStreet("nagpura");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560086);
		tenant.setTenantId(19);
		tenant.setTenantAge(20);

		testEntityManager.persist(tenant);

		Tenant getFromDb = itenantdao.findById(tenant.getTenantId()).get();
		getFromDb.setTenantId(19);
		testEntityManager.persist(getFromDb);

		assertThat(getFromDb.getTenantId()).isEqualTo(19);



	}

	@Test
	public void testAddTenant()
	{
		Tenant tenant=new Tenant();
		FlatAddress flatAddress=new FlatAddress();

		flatAddress.setHouseNo(10);
		flatAddress.setCity("Bangalore");
		flatAddress.setStreet("nagpura");
		flatAddress.setState("Karnataka");
		flatAddress.setCountry("India");
		flatAddress.setPin(560086);
		tenant.setTenantId(19);
		tenant.setTenantAge(20);
		Tenant saveToDb = testEntityManager.persist(tenant);
		Tenant getFromDb = itenantdao.findById(saveToDb.getTenantId()).get();
		assertEquals(getFromDb, saveToDb);

	}


}
