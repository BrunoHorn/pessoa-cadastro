package com.pessoa_cadastro.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import com.pessoa_cadastro.dto.PessoaDto;
import com.pessoa_cadastro.dto.input.PessoaDtoInput;
import com.pessoa_cadastro.mapper.EnderecoMapper;
import com.pessoa_cadastro.mapper.PessoaMapper;
import com.pessoa_cadastro.model.Endereco;
import com.pessoa_cadastro.model.Pessoa;
import com.pessoa_cadastro.repository.EnderecoRepository;
import com.pessoa_cadastro.repository.PessoaRepository;
import com.pessoa_cadastro.service.impl.PessoaServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(classes = {ModelMapperConfig.class})
class PessoaServiceTest {

	@InjectMocks
    private PessoaServiceImpl pessoaService;

	@Mock
	private PessoaMapper pessoaMapper;

	@Mock
	private PessoaRepository pessoaRepository;
	
	@Mock
	private EnderecoRepository enderecoRepository;
	
	@Mock
	private EnderecoMapper enderecoMapper;
	
	@Test
    @DisplayName("PessoaServiceTest Cria pessoa com sucesso")
    void testCreate() {
        when(pessoaMapper.toPessoaInput(any())).thenReturn(Pessoa.builder().nome("Bruno").dataNascimento(LocalDate.now()).build());
        when(pessoaRepository.save(any())).thenReturn(Pessoa.builder().id(1L).nome("Bruno").dataNascimento(LocalDate.now()).build());
        when(pessoaMapper.toPessoaDto(any())).thenReturn(PessoaDto.builder().id(1L).nome("Bruno").dataNascimento(LocalDate.now()).build());

        var pessoaSalva = pessoaService.save(PessoaDtoInput.builder().nome("Bruno").dataNascimento(LocalDate.now()).build());

        assertNotNull(pessoaSalva);
        assertEquals(pessoaSalva.getId(), 1L);
        assertEquals(pessoaSalva.getNome(), "Bruno");
        assertEquals(pessoaSalva.getDataNascimento(), LocalDate.now());
    }

	@Test
    @DisplayName("PessoaServiceTest altera pessoa com sucesso")
    void testUpdate() {   
	    Optional<Pessoa> pessoaOptinal = java.util.Optional.of(Pessoa.builder().id(1L).nome("Bruno").dataNascimento(LocalDate.now()).build());	
		
		when(pessoaRepository.findById(any())).thenReturn(pessoaOptinal);
        when(pessoaRepository.save(any())).thenReturn(Pessoa.builder().id(1L).nome("Bruno H").dataNascimento(LocalDate.now()).build());
        when(pessoaMapper.toPessoaDto(any())).thenReturn(PessoaDto.builder().id(1L).nome("Bruno H").dataNascimento(LocalDate.now()).build());
   
        var pessoaSalva = pessoaService.update(1L,PessoaDtoInput.builder().nome("Bruno H").dataNascimento(LocalDate.now()).build());

        assertNotNull(pessoaSalva);
        assertEquals(pessoaSalva.getId(), 1L);
        assertEquals(pessoaSalva.getNome(), "Bruno H");
        assertEquals(pessoaSalva.getDataNascimento(), LocalDate.now());
    }
	
	@Test
	@DisplayName("PessoaServiceTest busca por findby id ")
	void testaFindById() {
		Optional<Pessoa> pessoaOptinal = java.util.Optional.of(Pessoa.builder().id(1L).nome("Bruno").dataNascimento(LocalDate.now()).build());	
		when(pessoaRepository.findById(any())).thenReturn(pessoaOptinal);
        
        var pessoaBuscada = pessoaService.findById(1L);
        
        assertNotNull(pessoaBuscada);
        assertEquals(pessoaBuscada.getId(), 1L);
        assertEquals(pessoaBuscada.getNome(), "Bruno");
        assertEquals(pessoaBuscada.getDataNascimento(), LocalDate.now());
	}

	
	 @Test
	 @DisplayName("PessoaServiceTest deleta com sucesso")
	 void testDelete()  {
		 var pessoa =Pessoa.builder().id(5L).nome("Bruno").dataNascimento(LocalDate.now()).build();
		 var endereco =Endereco.builder().cep("12345678").logradouro("rua").numero("2b").principal(true).id(1l).pessoa(pessoa).build();
		 var endereco2 =Endereco.builder().cep("12345678").logradouro("rua").numero("1b").principal(false).id(1l).pessoa(pessoa).build();
		 List<Endereco> enderecos = new ArrayList<>();
		 enderecos.add(endereco);
		 enderecos.add(endereco2);
		 Optional<Pessoa> pessoaOptinal = java.util.Optional.of(Pessoa.builder().id(5L).nome("Bruno").dataNascimento(LocalDate.now()).build());			
		 when(pessoaRepository.findById(any())).thenReturn(pessoaOptinal);
		 when(enderecoRepository.findByPessoa(any())).thenReturn(enderecos);	
		 
		 pessoaService.delete(5L);	 
		 
		 verify(enderecoRepository, Mockito.times(2)).delete(any());
		 verify(pessoaRepository, Mockito.times(1)).delete(any());
	 }
	
		@Test
		@DisplayName("PessoaServiceTestbus busca lista de endereços de pessoa")
		void testfindByListaEnderecoPessoa() {
		    Optional<Pessoa> pessoaOptinal = java.util.Optional.of(Pessoa.builder().id(5L).nome("Bruno").dataNascimento(LocalDate.now()).build());	
		    var pessoa =Pessoa.builder().id(5L).nome("Bruno").dataNascimento(LocalDate.now()).build();
		    var endereco =Endereco.builder().cep("12345678").logradouro("rua").numero("2b").principal(true).id(1l).pessoa(pessoa).build();
			var endereco2 =Endereco.builder().cep("12345678").logradouro("rua").numero("1b").principal(false).id(1l).pessoa(pessoa).build();
			List<Endereco> enderecos = new ArrayList<>();
			enderecos.add(endereco);
			enderecos.add(endereco2);
		    var enderecoDto =EnderecoDto.builder().cep("12345678").logradouro("rua").numero("2b").principal(true).id(1l).build();
			var enderecoDto2 =EnderecoDto.builder().cep("12345678").logradouro("rua").numero("1b").principal(false).id(1l).build();	
			List<EnderecoDto> enderecosDto = new ArrayList<>();
			enderecosDto.add(enderecoDto);
			enderecosDto.add(enderecoDto2);		 
			when(pessoaRepository.findById(any())).thenReturn(pessoaOptinal);
			when(enderecoRepository.findByPessoa(any())).thenReturn(enderecos);
			when(enderecoMapper.toEnderecoDtoList(any())).thenReturn(enderecosDto);
		
			var pessoaListaEndereco = pessoaService.findByListaEnderecoPessoa(5l);
						
	        assertNotNull(pessoaListaEndereco);
	        assertEquals(pessoaListaEndereco.getId(), 5L);
	        assertEquals(pessoaListaEndereco.getNome(), "Bruno");
	        verify(enderecoMapper, Mockito.times(1)).toEnderecoDtoList(any());			
		}
}
