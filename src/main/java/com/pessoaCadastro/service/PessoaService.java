package com.pessoaCadastro.service;

import java.util.List;

import javax.validation.Valid;

import com.pessoaCadastro.dto.PessoaDto;
import com.pessoaCadastro.dto.PessoaListaEnderecoDto;
import com.pessoaCadastro.dto.input.PessoaDtoInput;
import com.pessoaCadastro.model.Pessoa;

public interface PessoaService {
	
	PessoaDto save(PessoaDtoInput pessoaDtoInput);
	
	Pessoa findById(Long id);
	
	List<PessoaDto> findAll();
	
	void delete(Long id);
	
	PessoaDto update(Long id, @Valid PessoaDtoInput pessoaDtoInput);

	PessoaListaEnderecoDto findByListaEnderecoPessoa(Long id);
	
	

}
