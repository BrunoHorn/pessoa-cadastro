package com.pessoaCadastro.service;

import com.pessoaCadastro.dto.EnderecoDto;
import com.pessoaCadastro.dto.input.EnderecoDtoInput;
import com.pessoaCadastro.model.Endereco;

public interface EnderecoService {

	EnderecoDto save(EnderecoDtoInput enderecoDtoInput);

	void delete(Long id);

	EnderecoDto updateEnderecoPrincipal(Long id);

	Endereco findById(Long id);
	
	
}
