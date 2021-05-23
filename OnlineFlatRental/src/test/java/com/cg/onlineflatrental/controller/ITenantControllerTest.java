package com.cg.onlineflatrental.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cg.onlineflatrental.controller.ITenantController;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.model.Tenant;
import com.cg.onlineflatrental.service.ITenantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ITenantController.class)
class ITenantControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITenantService itenantservice;
    
    
    @Test
	   public void testNewFlat() throws Exception{
	        
	        String URI="/Tenant/addTenant";
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
				tenant.setFlatAddress(flatAddress);
	       
	        String jsonInput = this.convertToJson(tenant);

	        Mockito.when(itenantservice.addTenant(Mockito.any(Tenant.class))).thenReturn(tenant);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        //assertThat(jsonInput).isEqualTo(jsonOutput);
	       // Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	    }
    
    @Test
	public void testViewFlat() throws Exception
	{
		String URI ="/Tenant/viewTenantById/{tenantId}";
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

		String jsonInput = this.convertToJson(tenant);

		Mockito.when(itenantservice.viewTenantById(Mockito.anyInt())).thenReturn(tenant);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	@Test
	public void testViewAllTenants() throws Exception{
		String URI="/Tenant/viewAllTenants";
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
		List<Tenant> tenantList = new ArrayList<>();
		tenantList.add(tenant);
		tenantList.add(tenant2);

		String jsonInput = this.convertToJson(tenantList);
		Mockito.when(itenantservice.viewAllTenants()).thenReturn(tenantList);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);

	}

	@Test
	public void testDeleteTenant() throws Exception
	{
		String URI="/Tenant/deleteTenant/{tenantId}";
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

		Mockito.when(itenantservice.viewTenantById(Mockito.anyInt())).thenReturn(tenant);
		Mockito.when(itenantservice.deleteTenant(Mockito.anyInt())).thenReturn(true);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 100).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}

	

	@Test
	public void testUpdateTenant() throws Exception{

		String URI = "/flatbooking/updateTenant";
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
		String jsonInput=this.convertToJson(tenant);

		Mockito.when(itenantservice.updateTenant(Mockito.any())).thenReturn(tenant);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		//assertThat(jsonInput.substring(0,5)).isEqualTo(jsonOutput.substring(0,5));


	}
	private String convertToJson(Object flat) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(flat);
    }
	
	

	

}
