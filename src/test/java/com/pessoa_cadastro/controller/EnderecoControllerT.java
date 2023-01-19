package com.pessoa_cadastro.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.pessoa_cadastro.configuration.PostgresContainer;
import com.pessoa_cadastro.dto.EnderecoDto;
import com.pessoa_cadastro.dto.input.EnderecoDtoInput;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class EnderecoControllerT {
	
	protected String convertToJsonString(Object movementRequest) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
	        
		return mapper.writeValueAsString(movementRequest);
	}
	  
	@Container
	private static final PostgresContainer POSTGRES_CONTAINER = PostgresContainer.getInstance();
	
	private static final Integer ENDERECO_DELETE_SUCCESSFUL =-102;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("EnderecoControllerT cria uma Endereço com sucesso")
	void criaEnderecoComSucesso() throws Exception {
		EnderecoDtoInput request =EnderecoDtoInput.builder()
	        		.cep("89110123")
	        		.cidade("Blumenau")
	        		.idPessoa(-1L)
	        		.logradouro("Rua 7")
	        		.numero("12")
	        		.build();

	    ResultActions resultActions = mockMvc.perform(post("/endereco")
	                                .contentType(MediaType.APPLICATION_JSON)
	                                .content(convertToJsonString(request))
	                )
	                .andExpect(status().isCreated());
	        
	       
	         resultActions
            .andExpect(jsonPath("id").exists())
            .andExpect(jsonPath("cep", is(request.getCep())))
            .andExpect(jsonPath("cidade", is(request.getCidade())))
            .andExpect(jsonPath("logradouro", is(request.getLogradouro())))
            .andExpect(jsonPath("numero", is(request.getNumero())))
            .andExpect(jsonPath("principal").exists())
            ;
	    }
	  	
	@Test
	@DisplayName("EnderecoControllerT deleta um endereço pelo ID")
	void deletaEnderecoSucesso() throws Exception {

		mockMvc.perform(delete("/endereco/{id}", ENDERECO_DELETE_SUCCESSFUL))
	        .andExpect(status().isNoContent())
	        ;
	    }
	    
	@Test
	@DisplayName("EnderecoControllerT altera um endereço com sucesso")
	void alteraEnderecoComSucesso() throws Exception {
		EnderecoDto request =EnderecoDto.builder()
		        		.id(-101L)
					    .cep("89110123")
		        		.cidade("Blumenau")
		        		.principal(true)
		        		.logradouro("Rua 7")
		        		.numero("66")
		        		.build();
			   
		ResultActions resultActions = mockMvc.perform( put("/endereco/{id}/principal",-101)
	                    .contentType(MediaType.APPLICATION_JSON)	                              
	                )
	          .andExpect(status().isOk());
	        
	    	  resultActions
              .andExpect(jsonPath("id").exists())
              .andExpect(jsonPath("cep", is(request.getCep())))
              .andExpect(jsonPath("cidade", is(request.getCidade())))
              .andExpect(jsonPath("logradouro", is(request.getLogradouro())))
              .andExpect(jsonPath("principal",is(request.isPrincipal())))
              .andExpect(jsonPath("numero", is(request.getNumero())))  
              ;
	    }
}
