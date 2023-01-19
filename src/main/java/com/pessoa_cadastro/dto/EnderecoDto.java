package com.pessoa_cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
@Builder
public class EnderecoDto {
    
	private Long id;
	
	private String logradouro ;
	
	private String cep;
	
	private String numero;
	
	private String cidade;
	
	private boolean principal;
	
}
