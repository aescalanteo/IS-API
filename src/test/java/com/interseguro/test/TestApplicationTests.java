package com.interseguro.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void matrixRotationWorks() throws Exception {

		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		mockMvc.perform(post("/matrix/rotate")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(matrix)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0][0]").value(3))
				.andExpect(jsonPath("$[0][1]").value(6))
				.andExpect(jsonPath("$[0][2]").value(9))
				.andExpect(jsonPath("$[1][0]").value(2))
				.andExpect(jsonPath("$[1][1]").value(5))
				.andExpect(jsonPath("$[1][2]").value(8))
				.andExpect(jsonPath("$[2][0]").value(1))
				.andExpect(jsonPath("$[2][1]").value(4))
				.andExpect(jsonPath("$[2][2]").value(7));

	}

	@Test
	void matrixValidationWorks() throws Exception {

		int[][] matrix = { { 1, 2, 3 }, { 4, 6 }, { 7, 8, 9 } };

		mockMvc.perform(post("/matrix/rotate")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(matrix)))
				.andExpect(status().isUnprocessableEntity());

	}

}
