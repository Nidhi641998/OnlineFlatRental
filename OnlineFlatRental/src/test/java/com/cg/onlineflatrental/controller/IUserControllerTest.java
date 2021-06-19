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

import com.cg.onlineflatrental.exception.UserNotFoundException;
import com.cg.onlineflatrental.model.*;
import com.cg.onlineflatrental.service.*;
import com.cg.onlineflatrental.service.IUserService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@WebMvcTest(value = IUserController.class)
class IUserControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	 @MockBean
	 private IUserService iUserService;

	@Test
	void testAddUser() throws Exception {
		String URI="/flatbooking/addUser";
		User user=new User();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		
		String jsonInput = this.convertToJson(user);
		Mockito.when(iUserService.addUser(Mockito.any(User.class))).thenReturn(user);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}
	
	@Test
	void testViewUser() throws Exception {
		String URI="/flatbooking/viewUser/{userId}";
		User user=new User();
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		
		String jsonInput = this.convertToJson(user);
		 Mockito.when(iUserService.viewUser(Mockito.anyInt())).thenReturn(user);
	       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 11).accept(MediaType.APPLICATION_JSON)).andReturn();
	       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	       String jsonOutput = mockHttpServletResponse.getContentAsString();
	       assertThat(jsonInput).isEqualTo(jsonOutput);
		}
	
	@Test
	void testViewAllUser() throws Exception {
		String URI="/flatbooking/viewAllUser";
		
		User user1=new User();
		user1.setUserName("aps");
		user1.setPassword("asp");
		user1.setUserType("landlord");
		
		User user2=new User();
		user2.setUserName("xyz");
		user2.setPassword("xzy");
		user2.setUserType("landlord");
		
		List<User> userList=new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		String jsonInput = this.convertToJson(userList);
		
		 Mockito.when(iUserService.viewAllUser()).thenReturn(userList);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	@Test
	void testRemoveUser() throws Exception {
		String URI="/flatbooking/removeUser/{userId}";
		User user=new User();
		user.setUserId(11);
		user.setUserName("aps");
		user.setPassword("asp");
		user.setUserType("landlord");
		
		 Mockito.when(iUserService.viewUser(Mockito.anyInt())).thenReturn(user);
		 Mockito.when(iUserService.removeUser(Mockito.anyInt())).thenReturn(true);
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 11).accept(MediaType.APPLICATION_JSON)).andReturn();
		 MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		 Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

		
	}
	
//	@Test
//	void testUpdateUser() throws Exception {
//		String URI="/flatbooking/updateUser";
//		User user=new User();
//		user.setUserName("apsaps");
//		user.setPassword("aspasp");
//		user.setUserType("landlord");
//		String jsonInput = this.convertToJson(user);
//		Mockito.when(iUserService.updateUser(Mockito.any())).thenReturn(user);
//		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
//	        String jsonOutput = mockHttpServletResponse.getContentAsString();
//
//	        assertThat(jsonInput.substring(0,3)).isEqualTo(jsonOutput.substring(0,3));
//
//	}
	
	@Test
	void testValidateUser() throws Exception {
		String URI="/flatbooking/validateUser/{userName}/{password}";
		User user=new User();
		//user.setUserId(11);
		user.setUserName("apsaps");
		user.setPassword("aspasp");
		user.setUserType("landlord");
		String jsonInput = this.convertToJson(user);
		Mockito.when(iUserService.validateUser(Mockito.anyString(),Mockito.anyString())).thenReturn(user);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI,"apsaps","aspasp").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	  //      String jsonOutput = mockHttpServletResponse.getContentAsString();
			 Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}
	
	 private String convertToJson(Object flat) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(flat);
	    } 

}
