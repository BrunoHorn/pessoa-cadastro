package com.pessoa_cadastro.service;

import com.pessoa_cadastro.dto.EnderecoDto;
import com.pessoa_cadastro.dto.input.EnderecoDtoInput;
import com.pessoa_cadastro.model.Endereco;

public interface EnderecoService {

	EnderecoDto save(EnderecoDtoInput enderecoDtoInput);

	void delete(Long id);

	EnderecoDto updateEnderecoPrincipal(Long id);

	Endereco findById(Long id);
	
	
}
