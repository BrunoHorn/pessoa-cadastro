package com.pessoa_cadastro.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.pessoa_cadastro.config.ModelMapperConfig;
import com.pessoa_cadastro.dto.EnderecoDto;
import com.pessoa_cadastro.dto.input.EnderecoDtoInput;
import com.pessoa_cadastro.mapper.EnderecoMapper;
import com.pessoa_cadastro.model.Endereco;
import com.pessoa_cadastro.model.Pessoa;
import com.pessoa_cadastro.repository.EnderecoRepository;
import com.pessoa_cadastro.service.impl.EnderecoServiceImpl;
import com.pessoa_cadastro.service.impl.PessoaServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(classes = {ModelMapperConfig.class})
class EnderecoServiceTeste {
	
	@InjectMocks
    private EnderecoServiceImpl enderecoService;

	@Mock
	EnderecoMapper enderecoMapper;
	
	@Mock
	PessoaServiceImpl pessoaService;
	
	@Mock
	EnderecoRepository enderecoRepository;
	
	@Test
    @DisplayName("EnderecoServiceTeste Cria pessoa com sucesso")
    void testCreate() {
		var pessoa =Pessoa.builder().id(5L).nome("Bruno").dataNascimento(LocalDate.now()).build();
		var endereco =Endereco.builder().cidade("Blumenau").cep("12345678").logradouro("rua").numero("2b").build();
		when(enderecoMapper.toEnderecoInput(any())).thenReturn(endereco);
        when(pessoaService.findById(any())).thenReturn(pessoa);
        when(enderecoRepository.save(any())).thenReturn(Endereco.builder().pessoa(pessoa).cidade("Blumenau").cep("12345678").logradouro("rua").numero("2b").id(1l).build());
        when(enderecoMapper.toEnderecoDto(any())).thenReturn(EnderecoDto.builder().id(1L).cidade("Blumenau").cep("12345678").logradouro("rua").numero("2b").build());
        
        var enderecoSavo = enderecoService.save(EnderecoDtoInput.builder().cidade("Blumenau").cep("12345678").logradouro("rua").numero("2b").build());
        
        assertNotNull(enderecoSavo);
        assertEquals(enderecoSavo.getId(),1L);
        assertEquals(enderecoSavo.getCep(),"12345678");
        assertEquals(enderecoSavo.getCidade(), "Blumenau");
        assertEquals(enderecoSavo.getLogradouro(), "rua");
        assertEquals(enderecoSavo.getNumero(),"2b");
    }

	@Test
	@DisplayName("EnderecoServiceTeste busca por findby id ")
	void testaFindById() {
		Optional<Endereco> enderecoOptinal = java.util.Optional.of(Endereco.builder().id(1L).cidade("Blumenau").cep("12345678").logradouro("rua").numero("2b").build());	
		
		when(enderecoRepository.findById(any())).thenReturn(enderecoOptinal);
        
        var enderecoBuscada = enderecoService.findById(1L);
        
        assertNotNull(enderecoBuscada);
        assertEquals(enderecoBuscada.getId(),1l);
        assertEquals(enderecoBuscada.getCep(),"12345678");
        assertEquals(enderecoBuscada.getCidade(), "Blumenau");
        assertEquals(enderecoBuscada.getLogradouro(), "rua");
        assertEquals(enderecoBuscada.getNumero(),"2b");
	}
	
	 @Test
	 @DisplayName("EnderecoServiceTeste deleta com sucesso")
	 void testDelete()  {
		 Optional<Endereco> enderecoOptinal = java.util.Optional.of(Endereco.builder().id(1L).cidade("Blumenau").cep("12345678").logradouro("rua").numero("2b").build());	

		 when(enderecoRepository.findById(any())).thenReturn(enderecoOptinal);

		 enderecoService.delete(1L);	 
		 
		 verify(enderecoRepository, Mockito.times(1)).delete(any());

	 }

}
