package com.pessoaCadastro.mapper;

import org.springframework.stereotype.Component;

import com.pessoaCadastro.dto.EnderecoDto;
import com.pessoaCadastro.dto.input.EnderecoDtoInput;
import com.pessoaCadastro.model.Endereco;

@Component
public class EnderecoMapper {

	public Endereco toEnderecoInput(EnderecoDtoInput enderecoDtoInput) {		
		var endereco = new Endereco();				
		endereco.setCep(enderecoDtoInput.getCep());
		endereco.setCidade(enderecoDtoInput.getCidade());
		endereco.setLogradouro(enderecoDtoInput.getLogradouro());
		endereco.setNumero(enderecoDtoInput.getNumero());
	
		return endereco;
	}

	public EnderecoDto toEnderecoDto(Endereco endereco) {
		var enderecoDto = new EnderecoDto();	
		enderecoDto.setId(endereco.getId());
		enderecoDto.setCep(endereco.getCep());
		enderecoDto.setCidade(endereco.getCidade());
		enderecoDto.setLogradouro(endereco.getLogradouro());
		enderecoDto.setNumero(endereco.getNumero());		
		enderecoDto.setPrincipal(endereco.isPrincipal());
		
		return enderecoDto;
	}

	public Endereco toEndereco(EnderecoDto enderecoDto) {
		var endereco = new Endereco();		
		endereco.setCep(enderecoDto.getCep());
		endereco.setCidade(enderecoDto.getCidade());
		endereco.setLogradouro(enderecoDto.getLogradouro());
		endereco.setNumero(enderecoDto.getNumero());
		endereco.setPrincipal(enderecoDto.isPrincipal());
	
		return endereco;
	}
	
}
