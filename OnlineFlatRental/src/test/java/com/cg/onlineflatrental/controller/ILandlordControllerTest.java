package com.cg.onlineflatrental.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;

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
import org.springframework.http.MediaType;
import com.cg.onlineflatrental.model.Landlord;

import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;
import com.cg.onlineflatrental.service.ILandlordService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ILandlordController.class)
class ILandlordControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ILandlordService ilandlordservice;
    
    @Test
    public void testNewLandlord() throws Exception{
    	String URI = "/landlord/addLandlord";
    	Landlord landlord = new Landlord();
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
		
		landlord.setLandlordName("Vijeta Choudhary");
		landlord.setLandlordAge(23);
		landlord.setFlat(flat);
		
		String jsonInput = this.convertToJson(landlord);

        Mockito.when(ilandlordservice.addLandlord(Mockito.any(Landlord.class))).thenReturn(landlord);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
        
       
		 
    }
				
			
	   @Test
	   public void testViewLandlord() throws Exception
	   {
	   String URI= "/landlord/viewAllLandlord";
	   Landlord landlord = new Landlord();
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
		
		landlord.setLandlordId(3);
		landlord.setLandlordName("Vijeta Choudhary");
		landlord.setLandlordAge(23);
		landlord.setFlat(flat);
		String jsonInput = this.convertToJson(landlord);
		
		
		Mockito.when(ilandlordservice.viewLandlordById(Mockito.anyInt())).thenReturn(landlord);
	       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
	       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	       String jsonOutput = mockHttpServletResponse.getContentAsString();

	      // assertThat(jsonInput).isEqualTo(jsonOutput);
	   }
	   
	   @Test
	    public void testViewAllLandlord() throws Exception{
	        String URI = "/viewLandlord/{landlordId}";
	        
	        Landlord landlord1 = new Landlord();
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
			
			landlord1.setLandlordId(1);
			landlord1.setLandlordName("Vijeta Choudhary");
			landlord1.setLandlordAge(23);
			landlord1.setFlat(flat1);
			 
			Landlord landlord2 = new Landlord();
	    	Flat flat2 = new Flat();
	    	FlatAddress flatAddress2=new FlatAddress();
	    	flatAddress2.setHouseNo(10);
			flatAddress2.setCity("Bangalore");
			flatAddress2.setStreet("nagpura");
			flatAddress2.setState("Karnataka");
			flatAddress2.setCountry("India");
			flatAddress2.setPin(560086);
			
			flat2.setCost((float) 2500);
			flat2.setFlatAddress(flatAddress2);
			flat2.setAvailability("Yes");
			
			landlord2.setLandlordId(3);
			landlord2.setLandlordName("Vijeta Choudhary");
			landlord2.setLandlordAge(23);
			landlord2.setFlat(flat2);
			  List<Landlord> landlordList = new ArrayList<>();
		        landlordList.add(landlord1);
		        landlordList.add(landlord2);

		        String jsonInput = this.convertToJson(landlordList);

		        Mockito.when(ilandlordservice.viewAllLandlord()).thenReturn(landlordList);
		        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
		        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		        String jsonOutput = mockHttpServletResponse.getContentAsString();

		       // assertThat(jsonInput).isEqualTo(jsonOutput);
	   }
	   
	   @Test
	    public void testDeleteLandlord() throws Exception{
	        String URI = "/deleteLandlord/{landlordId}";
	        
	 	   Landlord landlord = new Landlord();
			 //landlord.setLandlordId(3);
				landlord.setLandlordName("Vijeta Choudhary");
				landlord.setLandlordAge(23);
				//landlord.setFlat(); {
					Flat flat = new Flat();
				       FlatAddress flatAddress=new FlatAddress();
						 flatAddress.setHouseNo(100);
							flatAddress.setCity("Bangalore");
							flatAddress.setStreet("Kormangala");
							flatAddress.setState("Karnataka");
							flatAddress.setCountry("India");
							flatAddress.setPin(760200);
							
							flat.setCost((float) 2500);
							flat.setFlatAddress(flatAddress);
							flat.setAvailability("Yes");
							String jsonInput = this.convertToJson(landlord);
	 			
	 			 Mockito.when(ilandlordservice.viewLandlordById(Mockito.anyInt())).thenReturn(landlord);
	 		     Mockito.when(ilandlordservice.deleteLandlordById(Mockito.anyInt())).thenReturn(true);
	 		     MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 100).accept(MediaType.APPLICATION_JSON)).andReturn();
	 		     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	 		   // Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	 		   
	 		    // Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	   	   }
	        
	   @Test
	    public void testUpdateFlat() throws Exception{

	        String URI = "/updateLandlord/";
	       
	        Landlord landlord = new Landlord();
			 landlord.setLandlordId(3);
				landlord.setLandlordName("Vijeta Choudhary");
				landlord.setLandlordAge(23);
				landlord.setFlat(); {
					Flat flat = new Flat();
				       FlatAddress flatAddress=new FlatAddress();
						 flatAddress.setHouseNo(100);
							flatAddress.setCity("Bangalore");
							flatAddress.setStreet("Kormangala");
							flatAddress.setState("Karnataka");
							flatAddress.setCountry("India");
							flatAddress.setPin(760200);
							
							flat.setCost((float) 2500);
							flat.setFlatAddress(flatAddress);
							flat.setAvailability("Yes");
				}
	 			String jsonInput = this.convertToJson(landlord);

	 	        Mockito.when(ilandlordservice.updateLandlord(Mockito.any())).thenReturn(landlord);
	 	       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
		                .andReturn();
	 	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	 	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	 	        //assertThat(jsonInput.substring(0,5)).isEqualTo(jsonOutput.substring(0,5));
	   }
	   
	   private String convertToJson(Object landlord) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(landlord);
	    }
	        

}
       

