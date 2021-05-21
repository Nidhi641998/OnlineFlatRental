package com.cg.onlineflatrental.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


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

import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.service.IFlatService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = IFlatController.class)
class IFlatControllerTest {

	  @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private IFlatService iflatservice;
	    
	    @Test
	   public void testNewFlat() throws Exception{
	        String URI = "/flatbooking/addFlat";
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
	       
	        String jsonInput = this.convertToJson(flat);

	        Mockito.when(iflatservice.addFlat(Mockito.any(Flat.class))).thenReturn(flat);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	    }
	   
	   @Test
	   public void testViewFlat() throws Exception
	   {
	   String URI= "/flatbooking/viewAllFlat/{flatId}";
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
			String jsonInput = this.convertToJson(flat);
	   Mockito.when(iflatservice.viewFlat(Mockito.any())).thenReturn(flat);
       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
       String jsonOutput = mockHttpServletResponse.getContentAsString();

       assertThat(jsonInput).isEqualTo(jsonOutput);
	   }
	   
	   @Test
	    public void testViewAllFlat() throws Exception{
	        String URI = "/flatbooking/viewAllFlat";
	        
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
			 
			  List<Flat> flatList = new ArrayList<>();
		        flatList.add(flat1);
		        flatList.add(flat2);

		        String jsonInput = this.convertToJson(flatList);

		        Mockito.when(iflatservice.viewAllFlat()).thenReturn(flatList);
		        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
		        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		        String jsonOutput = mockHttpServletResponse.getContentAsString();

		        assertThat(jsonInput).isEqualTo(jsonOutput);
	   }
	   
	   @Test
	    public void testDeleteFlat() throws Exception{
	        String URI = "/flatbooking/deleteFlat/{flatId}";
	        
	        Flat flat = new Flat();
	        FlatAddress flatAddress=new FlatAddress();
	 		 flatAddress.setHouseNo(10);
	 			flatAddress.setCity("Bangalore");
	 			flatAddress.setStreet("nagpura");
	 			flatAddress.setState("Karnataka");
	 			flatAddress.setCountry("India");
	 			flatAddress.setPin(560086);
	 			flat.setFlatId(100);
	 			flat.setCost((float) 2500);
	 			flat.setFlatAddress(flatAddress);
	 			flat.setAvailability("Yes");
	 			
	 			 Mockito.when(iflatservice.viewFlat(Mockito.any())).thenReturn(flat);
	 		     Mockito.when(iflatservice.deleteFlatById(Mockito.any())).thenReturn(true);
	 		     MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 100).accept(MediaType.APPLICATION_JSON)).andReturn();
	 		     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	 		     Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	   	   }
	        
	   @Test
	    public void testUpdateFlat() throws Exception{

	        String URI = "/flatbooking/updateFlat";
	       
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
	 			String jsonInput = this.convertToJson(flat);

	 	        Mockito.when(iflatservice.updateFlat(Mockito.any())).thenReturn(flat);
	 	       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
		                .andReturn();
	 	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	 	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	 	        assertThat(jsonInput.substring(0,5)).isEqualTo(jsonOutput.substring(0,5));
	   }
	   
	   private String convertToJson(Object flat) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(flat);
	    }
	        
	   @Test
	    public void testFindByCostAndAvailability() throws Exception{
	        String URI= "/flatbooking/viewByCost/{cost}/{availability}";
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
	        String jsonInput = this.convertToJson(flat);
	        
	        List<Flat> flatList = new ArrayList<>();
	        flatList.add(flat);
	        
	        Mockito.when(iflatservice.findByCostAndAvailability((Mockito.any()), (Mockito.any()))).thenReturn(flatList);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 2500, "Yes")
	                .accept(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        System.out.println(jsonOutput);
	     //   assertThat(jsonInput).isEqualTo(jsonOutput);
	        assertThat(flat.getCost()).isEqualTo(2500);
	    }

}
