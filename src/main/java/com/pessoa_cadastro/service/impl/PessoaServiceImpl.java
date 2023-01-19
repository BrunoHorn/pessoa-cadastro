package com.pessoa_cadastro.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pessoa_cadastro.dto.PessoaDto;
import com.pessoa_cadastro.dto.PessoaListaEnderecoDto;
import com.pessoa_cadastro.dto.input.PessoaDtoInput;
import com.pessoa_cadastro.exception.EntidadeNaoEncontradaException;
import com.pessoa_cadastro.mapper.EnderecoMapper;
import com.pessoa_cadastro.mapper.PessoaMapper;
import com.pessoa_cadastro.model.Endereco;
import com.pessoa_cadastro.model.Pessoa;
import com.pessoa_cadastro.repository.EnderecoRepository;
import com.pessoa_cadastro.repository.PessoaRepository;
import com.pessoa_cadastro.service.PessoaService;


@Service
public class PessoaServiceImpl  implements PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaMapper pessoaMapper;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	EnderecoMapper enderecoMapper;
	
	@Transactional
	@Override
	public PessoaDto save(PessoaDtoInput pessoaDtoInput) {		
		var pessoa = pessoaMapper.toPessoaInput(pessoaDtoInput);	
		var pessoaSalva = pessoaRepository.save(pessoa);
		
		return pessoaMapper.toPessoaDto(pessoaSalva);
	}

	@Override
	public Pessoa findById(Long id) {
			var pessoaOptional = pessoaRepository.findById(id);
			if(pessoaOptional.isEmpty()) {
				throw new EntidadeNaoEncontradaException("Não existe pessoa cadastrada com esse ID");
			}

			return pessoaOptional.get();
	}

	@Transactional
	@Override
	public void delete(Long id) {
		var pessoa = findById(id);
		List <Endereco> listEndereco = enderecoRepository.findByPessoa(pessoa);
		for(Endereco endereco : listEndereco ) {									
			enderecoRepository.delete(endereco);	
			}	
		
		pessoaRepository.delete(pessoa);
	}

	@Override
	public PessoaDto update(Long id, @Valid PessoaDtoInput pessoaDtoInput) {
		var pessoaOptional = pessoaRepository.findById(id);
		if(pessoaOptional.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não existe pessoa cadastrada com esse ID");
		}
		
		var pessoa = pessoaOptional.get();
		pessoa.setNome(pessoaDtoInput.getNome());
		pessoa.setDataNascimento(pessoaDtoInput.getDataNascimento());
		
		return pessoaMapper.toPessoaDto(pessoaRepository.save(pessoa));
	}

	@Override
	public PessoaListaEnderecoDto findByListaEnderecoPessoa(Long id) {
		PessoaListaEnderecoDto pessoaListaEnderecoDto = new PessoaListaEnderecoDto();
		var pessoa= findById(id);
		pessoaListaEnderecoDto.setNome(pessoa.getNome());
		pessoaListaEnderecoDto.setId(id);
		var listEnderecoDto = enderecoMapper.toEnderecoDtoList(enderecoRepository.findByPessoa(pessoa));
		pessoaListaEnderecoDto.setListEnderecoDto(listEnderecoDto);
			
		return pessoaListaEnderecoDto;	
		}

	@Override
	public Page<PessoaDto> buscaListaPessoa(Pageable pageable) {
   	 Page<Pessoa> page = pessoaRepository.findAll(pageable);
   	 
   	 return page.map(pagedto -> pessoaMapper.toPessoaDto(pagedto));
	}



	

}