package com.pessoa_cadastro.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class PessoaListaEnderecoDto {
   
	private Long id;
	
	private String nome ;
	
	private List<EnderecoDto> listEnderecoDto ;
	
}
