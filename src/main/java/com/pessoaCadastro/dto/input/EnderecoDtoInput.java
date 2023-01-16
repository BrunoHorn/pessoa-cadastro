package com.pessoaCadastro.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class EnderecoDtoInput {
	
	@NotBlank
	@Size(min =2 ,max =150)
	private String logradouro ;
	
	@NotBlank
	@Size(min =8 ,max =8)
	private String cep; 
	
	@Size(max =10)
	private String numero; 
	
	@NotBlank
	@Size(min =2 ,max =150)
	private String cidade;		
	
	@NotNull
	private Long idPessoa;

}
