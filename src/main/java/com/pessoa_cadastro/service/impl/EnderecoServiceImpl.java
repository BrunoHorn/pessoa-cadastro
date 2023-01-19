package com.pessoa_cadastro.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoa_cadastro.dto.EnderecoDto;
import com.pessoa_cadastro.dto.input.EnderecoDtoInput;
import com.pessoa_cadastro.exception.EntidadeNaoEncontradaException;
import com.pessoa_cadastro.mapper.EnderecoMapper;
import com.pessoa_cadastro.model.Endereco;
import com.pessoa_cadastro.model.Pessoa;
import com.pessoa_cadastro.repository.EnderecoRepository;
import com.pessoa_cadastro.service.EnderecoService;
import com.pessoa_cadastro.service.PessoaService;
  
@Service
public class EnderecoServiceImpl implements EnderecoService  {

	@Autowired
	private EnderecoMapper enderecoMapper;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaService pessoaService;
		
	@Transactional
	@Override
	public EnderecoDto save(EnderecoDtoInput enderecoDtoInput) {			
		var endereco = enderecoMapper.toEnderecoInput(enderecoDtoInput);		
			endereco.setPessoa( pessoaService.findById(enderecoDtoInput.getIdPessoa()));	
			enderecoRepository.save(endereco);
		
		return enderecoMapper.toEnderecoDto(endereco);
	}
	
	@Override
	public Endereco findById(Long id) {
		var enderecoOptional = enderecoRepository.findById(id);
		if(enderecoOptional.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não há endereços cadastrados com esse ID");
		}
		return enderecoOptional.get();
	}

	@Transactional
	@Override
	public void delete(Long id) {
		enderecoRepository.delete(findById(id));
	}
	
	@Override	
	public EnderecoDto updateEnderecoPrincipal(Long idNovoEnderecoPrincipal) {
		var novoEnderecoPrincipal = findById(idNovoEnderecoPrincipal);
		removeEnderecoPrincipal(novoEnderecoPrincipal.getPessoa()); 
		novoEnderecoPrincipal.setPrincipal(true);	 
		return enderecoMapper.toEnderecoDto(enderecoRepository.save(novoEnderecoPrincipal));
	}

	private void removeEnderecoPrincipal(Pessoa pessoa) {
		var antigoEnderecoPrincipal = enderecoRepository.findByPessoaAndPrincipal(pessoa, true);
		
		if (antigoEnderecoPrincipal.isPresent()) {
			antigoEnderecoPrincipal.get().setPrincipal(false);
			enderecoRepository.save(antigoEnderecoPrincipal.get());
		}
	}

}
