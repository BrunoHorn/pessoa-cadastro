package com.pessoa_cadastro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pessoa_cadastro.dto.EnderecoDto;
import com.pessoa_cadastro.dto.input.EnderecoDtoInput;
import com.pessoa_cadastro.model.Endereco;

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
	
	public List<EnderecoDto> toEnderecoDtoList(List<Endereco> listEndereco) {
		List<EnderecoDto> listEnderecoDto = new ArrayList<>();
		for(Endereco endereco : listEndereco ) {									
			listEnderecoDto.add(this.toEnderecoDto(endereco));	
		}
		return listEnderecoDto;
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
