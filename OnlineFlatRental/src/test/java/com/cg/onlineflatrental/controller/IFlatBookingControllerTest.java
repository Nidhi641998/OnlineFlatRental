package com.cg.onlineflatrental.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.model.FlatBooking;
import com.cg.onlineflatrental.service.IFlatBookingService;
@RunWith(SpringRunner.class)
@WebMvcTest(value = IFlatBookingController.class)
class IFlatBookingControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private IFlatBookingService iflatservice;
	    
	    @Test
		   public void testNewFlatBooking() throws Exception{
	    	String URI = "/api/ofr/addFlat1";
	    	FlatBooking flatBooking = new FlatBooking();
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
			
			flatBooking.setBookingNo(10);
			
			String jsonInput = this.convertToJson(flatBooking);

	        Mockito.when(iflatservice.addFlatBooking1(Mockito.any(FlatBooking.class))).thenReturn(flatBooking);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	        
	        
	        
	       
	    	
	    }
	    
	    
	    @Test
		   public void testViewFlatBooking() throws Exception
		   {
	    	String URI="/api/ofr/viewAllFlat/{bookingNo}";
	    	FlatBooking flatBooking = new FlatBooking();
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
			
			flatBooking.setBookingNo(10);
			
			String jsonInput = this.convertToJson(flatBooking);
			
			
			Mockito.when(iflatservice.viewFlatBooking(Mockito.anyInt())).thenReturn(flatBooking);
		       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
		       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		       String jsonOutput = mockHttpServletResponse.getContentAsString();

		       assertThat(jsonInput).isEqualTo(jsonOutput);
	    	
	    	
	    	
	    	
	    	
		   }
	    @Test
	    public void testUpdateFlat() throws Exception{

	        String URI = "/api/ofr/updateFlat";
	       
	        FlatBooking flatBooking = new FlatBooking();
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
				
				flatBooking.setBookingNo(10);
				
	 			String jsonInput = this.convertToJson(flatBooking);

	 	        Mockito.when(iflatservice.updateFlatBooking(Mockito.any())).thenReturn(flatBooking);
	 	       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
		                .andReturn();
	 	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	 	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	 	        assertThat(jsonInput.substring(0,5)).isEqualTo(jsonOutput.substring(0,5));
	   }
	    @Test
	    public void testDeleteFlat() throws Exception{
	    	String URI="/api/ofr/deleteFlat/{bookingNo}";
	    	
	    	FlatBooking flatBooking = new FlatBooking();
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
				
				flatBooking.setBookingNo(10);
				
	 			String jsonInput = this.convertToJson(flatBooking);
	 			
	 			Mockito.when(iflatservice.viewFlatBooking(Mockito.anyInt())).thenReturn(flatBooking);
	 		     Mockito.when(iflatservice.deleteFlatBookingbyId(Mockito.anyInt())).thenReturn(true);
	 		     MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 100).accept(MediaType.APPLICATION_JSON)).andReturn();
	 		     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	 		     Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	    	
	    }
	    
	    
	    @Test
	    public void testViewAllFlat() throws Exception{
	        String URI = "/api/ofr/view-all-flatBookings";
	        
	        FlatBooking flatBooking1 = new FlatBooking();
	    	Flat flat1 = new Flat();
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
			 
			 flatBooking1.setBookingNo(10);
			 
			 FlatBooking flatBooking2 = new FlatBooking();
		    	Flat flat2 = new Flat();
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
			 
			 flatBooking2.setBookingNo(10);
			 
			  List<FlatBooking> flatList = new ArrayList<>();
		        flatList.add(flatBooking1);
		        flatList.add(flatBooking2);

		        String jsonInput = this.convertToJson(flatList);

		        Mockito.when(iflatservice.viewAllFlatBooking()).thenReturn(flatList);
		        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
		        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		        String jsonOutput = mockHttpServletResponse.getContentAsString();

		        assertThat(jsonInput).isEqualTo(jsonOutput);
	   }
	    
	    
	    
	    
	    
	    
	    private String convertToJson(Object flatBooking) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(flatBooking);
	    }

	

}
