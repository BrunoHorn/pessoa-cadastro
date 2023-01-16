package com.pessoaCadastro.mapper;

import org.springframework.stereotype.Component;

import com.pessoaCadastro.dto.PessoaDto;
import com.pessoaCadastro.dto.input.PessoaDtoInput;
import com.pessoaCadastro.model.Pessoa;


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
	
	public Pessoa toPessoa(PessoaDto pessoaDto) {		
		var pessoa = new Pessoa();
		pessoa.setId(pessoaDto.getId());
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setDataNascimento(pessoaDto.getDataNascimento());
	
		return pessoa;
	}
	
}
