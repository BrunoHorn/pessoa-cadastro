package com.pessoaCadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class EnderecoDto {
    
	private Long id;
	
	private String logradouro ;
	
	private String cep;
	
	private String numero;
	
	private String cidade;
	
	private boolean principal;
	
}
