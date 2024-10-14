package com.nisum.controller;

import com.nisum.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testUserRegister() throws Exception {
		Optional<User> optionalUserOld = userRepository.findByEmail("juan@rodriguez.cl");
		optionalUserOld.ifPresent(user -> userRepository.delete(user));

		User user = new User("name","juan@rodriguez.cl","a2asfGfdfdf4", new ArrayList<>());

		mockMvc.perform(
				MockMvcRequestBuilders.post("/user/register")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(user)))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
