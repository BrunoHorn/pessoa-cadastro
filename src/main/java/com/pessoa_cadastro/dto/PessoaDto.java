package com.pessoa_cadastro.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class PessoaDto {

    private Long id;
	
	private String nome ;
	
	private LocalDate dataNascimento ;
	
	@JsonIgnore
	private List<EnderecoDto> enderecoDto ;
	
}
