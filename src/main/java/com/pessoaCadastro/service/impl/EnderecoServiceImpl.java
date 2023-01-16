package com.pessoaCadastro.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaCadastro.dto.EnderecoDto;
import com.pessoaCadastro.dto.input.EnderecoDtoInput;
import com.pessoaCadastro.exception.EntidadeNaoEncontradaException;
import com.pessoaCadastro.exception.NegocioException;
import com.pessoaCadastro.mapper.EnderecoMapper;
import com.pessoaCadastro.model.Endereco;
import com.pessoaCadastro.model.Pessoa;
import com.pessoaCadastro.repository.EnderecoRepository;
import com.pessoaCadastro.service.EnderecoService;
import com.pessoaCadastro.service.PessoaService;

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
		validaCep(enderecoDtoInput.getCep());
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

	private void validaCep(String cep) {
		   try {
		        Integer.valueOf(cep);
		    } catch (RuntimeException e) {
		    	throw new NegocioException("CEP deve conter apenas numeros!");
		    }
	}

}
