package com.pessoa_cadastro.controller;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
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
import com.pessoa_cadastro.dto.input.PessoaDtoInput;


@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers

class PessoaControllerIT {
	
	@BeforeAll
	static void localeContextTest() {
		Locale.setDefault(new Locale("pt", "BR"));
	}
	
	protected String convertToJsonString(Object movementRequest) throws JsonProcessingException {
	  ObjectMapper mapper = new ObjectMapper();
	  return mapper.writeValueAsString(movementRequest);
	}
	
	@Container
    private static final PostgresContainer POSTGRES_CONTAINER = PostgresContainer.getInstance();
	private static final Integer PESSOA_GET_ID = -1;
	private static final Integer PESSOA_UPDATE_ID = -3;
	private static final Integer NOT_FOUND_ID = -22;
	private static final Integer PESSOA_DELETE_SUCCESSFUL =-2;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("PessoaControllerIT cria uma pessoa com sucesso")
	void criaPessoaComSucesso() throws Exception {
		var json = "{\"nome\":\"Bruno\",\"dataNascimento\":\"2014-01-01\"}";
	  				
		ResultActions resultActions = mockMvc.perform(post("/pessoa")
	                        .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
	                )
	                .andExpect(status().isCreated());

	    this.matchFields(resultActions, "Bruno", "2014-01-01");
	}

	@Test
	@DisplayName("PessoaControllerIT altera uma pessoa com sucesso")
	void alteraPessoaComSucesso() throws Exception {
		var json = "{\"nome\":\"Maiara da Silva\",\"dataNascimento\":\"1992-03-13\"}";

	    ResultActions resultActions = mockMvc.perform(put("/pessoa/{id}",PESSOA_UPDATE_ID)
	                                .contentType(MediaType.APPLICATION_JSON)
	                                .content(json)
	                )
	                .andExpect(status().isOk());
	                
	    this.matchFields(resultActions, "Maiara da Silva", "1992-03-13");
	}

	@Test
	@DisplayName("PessoaControllerIT busca uma pessoa com o ID")
	void BuscaPorIdComSucesso() throws Exception {
		mockMvc.perform(get("/pessoa/{id}",PESSOA_GET_ID))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("id", is(PESSOA_GET_ID)));
	}

	@Test
	@DisplayName("PessoaControllerIT busca uma pessoa com o ID não encontrada")
	void BuscaPorIdComNotFound() throws Exception {
		mockMvc.perform(get("/pessoa/{id}", NOT_FOUND_ID))
	                .andExpect(status().isNotFound());
	}
	    
	@Test
	@DisplayName("PessoaControllerIT deleta uma pessoa pelo ID")
	void deletaPessoaSucesso() throws Exception {

	        mockMvc.perform(delete("/pessoa/{id}", PESSOA_DELETE_SUCCESSFUL))
	                .andExpect(status().isNoContent());
	}
	    
	@Test
	@DisplayName("PessoaControllerIT busca lista pessoas")
	void buscaListaPessoas() throws Exception {
		PessoaDtoInput request = PessoaDtoInput.builder().build();
	        		

		mockMvc.perform(get("/pessoa")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(convertToJsonString(request))
	                )
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$").isNotEmpty());
	}

	private void matchFields(ResultActions resultActions, String nome, String dataNascimento) throws Exception {
		resultActions
	    	.andExpect(jsonPath("id").exists())
	        .andExpect(jsonPath("nome", is(nome)))
	        .andExpect(jsonPath("dataNascimento", is(dataNascimento)));
	    }
}
