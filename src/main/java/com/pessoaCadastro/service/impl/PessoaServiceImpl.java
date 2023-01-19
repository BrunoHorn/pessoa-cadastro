package com.pessoaCadastro.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaCadastro.dto.EnderecoDto;
import com.pessoaCadastro.dto.PessoaDto;
import com.pessoaCadastro.dto.PessoaListaEnderecoDto;
import com.pessoaCadastro.dto.input.PessoaDtoInput;
import com.pessoaCadastro.exception.EntidadeNaoEncontradaException;
import com.pessoaCadastro.mapper.EnderecoMapper;
import com.pessoaCadastro.mapper.PessoaMapper;
import com.pessoaCadastro.model.Endereco;
import com.pessoaCadastro.model.Pessoa;
import com.pessoaCadastro.repository.EnderecoRepository;
import com.pessoaCadastro.repository.PessoaRepository;
import com.pessoaCadastro.service.PessoaService;


@Service
public class PessoaServiceImp  implements PessoaService {
	
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
			
		return  pessoaMapper.toPessoaDto(pessoaRepository.save(pessoaMapper.toPessoaInput(pessoaDtoInput)));
	}

	@Override
	public Pessoa findById(Long id) {
			var pessoaOptional = pessoaRepository.findById(id);
			if(pessoaOptional.isEmpty()) {
				throw new EntidadeNaoEncontradaException("Não a pessoa cadastrada com esse ID");
			}

			return pessoaOptional.get();
	}

	@Override
	public List<PessoaDto> findAll() {
		List<Pessoa> listPessoa = pessoaRepository.findAll();
		List<PessoaDto> listPessoaDto = new ArrayList<>();
		for(Pessoa pessoa :listPessoa ) {	
			var pessoaDto = pessoaMapper.toPessoaDto(pessoa);
			listPessoaDto.add(pessoaDto);
		}
		
		return listPessoaDto;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		List <Endereco> listEndereco = enderecoRepository.findByPessoa(findById(id));
		for(Endereco endereco : listEndereco ) {									
			enderecoRepository.delete(endereco);	
			}	
		pessoaRepository.delete(findById(id));
	}

	@Override
	public PessoaDto update(Long id, @Valid PessoaDtoInput pessoaDtoInput) {
		var pessoaOptional = pessoaRepository.findById(id);
		if(pessoaOptional.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não a pessoa cadastrada com esse ID");
		}
		var pessoaDto = pessoaMapper.toPessoaDto((pessoaOptional.get()));		
		pessoaDto.setNome(pessoaDtoInput.getNome());
		pessoaDto.setDataNascimento(pessoaDtoInput.getDataNascimento());

		return pessoaDto;
	}

	@Override
	public PessoaListaEnderecoDto findByListaEnderecoPessoa(Long id) {
		PessoaListaEnderecoDto pessoaListaEnderecoDto = new PessoaListaEnderecoDto();
		var pessoa= findById(id);
		pessoaListaEnderecoDto.setNome(pessoa.getNome());
		pessoaListaEnderecoDto.setId(id);
		List <Endereco> listEndereco = enderecoRepository.findByPessoa(pessoa);
		List<EnderecoDto> listEnderecoDto = new ArrayList<>();
		for(Endereco endereco : listEndereco ) {									
				listEnderecoDto.add(enderecoMapper.toEnderecoDto(endereco));	
			}
		pessoaListaEnderecoDto.setListEnderecoDto(listEnderecoDto);
		
		return pessoaListaEnderecoDto;	
	}



	

}