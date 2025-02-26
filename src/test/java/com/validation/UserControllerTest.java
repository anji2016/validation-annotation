package com.validation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.validation.dto.UserDTO;

@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;


	@Test
	void testRegisterUser_ValidInput() throws Exception {
		// Initialize MockMvc manually
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.getDispatcherServlet().setEnableLoggingRequestDetails(true);

		// Given - Invalid UserDTO
		UserDTO userDTO = new UserDTO("abcd", "invalid@gmail.com", "Passw@1");

		// When & Then
		mockMvc.perform(post("/users/register")
				.content(new ObjectMapper().writeValueAsString(userDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	void testRegisterUser_InvalidInput() throws Exception {
		// Initialize MockMvc manually
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.getDispatcherServlet().setEnableLoggingRequestDetails(true);

		// Given - Invalid UserDTO
		UserDTO userDTO = new UserDTO("abcd", "", "Passw@1");

		// When & Then
		mockMvc.perform(post("/users/register")
				.content(new ObjectMapper().writeValueAsString(userDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print());
	}
}
