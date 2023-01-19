package com.pessoa_cadastro.mapper;

import org.springframework.stereotype.Component;

import com.pessoa_cadastro.dto.PessoaDto;
import com.pessoa_cadastro.dto.input.PessoaDtoInput;
import com.pessoa_cadastro.model.Pessoa;


@Component
public class PessoaMapper {
	
	public Pessoa toPessoaInput(PessoaDtoInput pessoaDtoInput) {		
		var pessoa = new Pessoa();		
		pessoa.setNome(pessoaDtoInput.getNome());
		pessoa.setDataNascimento(pessoaDtoInput.getDataNascimento());
		
		return pessoa;
	}
	

	public PessoaDto toPessoaDto(Pessoa pessoa) {		
		var pessoaDto = new PessoaDto();
		pessoaDto.setId(pessoa.getId());
		pessoaDto.setNome(pessoa.getNome());
		pessoaDto.setDataNascimento(pessoa.getDataNascimento());
	
		return pessoaDto;
	}
	
	
}
