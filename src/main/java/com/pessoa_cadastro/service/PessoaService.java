package com.pessoa_cadastro.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pessoa_cadastro.dto.PessoaDto;
import com.pessoa_cadastro.dto.PessoaListaEnderecoDto;
import com.pessoa_cadastro.dto.input.PessoaDtoInput;
import com.pessoa_cadastro.model.Pessoa;

public interface PessoaService {
	
	PessoaDto save(PessoaDtoInput pessoaDtoInput);
	
	Pessoa findById(Long id);
		
	void delete(Long id);
	
	PessoaDto update(Long id, @Valid PessoaDtoInput pessoaDtoInput);

	PessoaListaEnderecoDto findByListaEnderecoPessoa(Long id);

	Page<PessoaDto> buscaListaPessoa(Pageable pageable);
	
	

}
