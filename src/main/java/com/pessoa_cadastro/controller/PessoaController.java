package com.pessoa_cadastro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa_cadastro.dto.PessoaDto;
import com.pessoa_cadastro.dto.PessoaListaEnderecoDto;
import com.pessoa_cadastro.dto.input.PessoaDtoInput;
import com.pessoa_cadastro.mapper.PessoaMapper;
import com.pessoa_cadastro.service.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RequestMapping("/pessoa")
@RestController
@Api(value="Cadastro pessoa")
public class PessoaController {
		
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaMapper pessoaMapper;
		
	@PostMapping
	@ApiOperation(value="Cadastrar nova Pessoa")
	public ResponseEntity<PessoaDto> savepessoa(@RequestBody @Valid PessoaDtoInput pessoaDtoInput){		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaDtoInput));
	}
	
    @GetMapping("/{id}")
    @ApiOperation(value="Busca pessoas cadastrado pelo ID")
    public ResponseEntity<PessoaDto> getpessoaId(@PathVariable(value = "id") Long id){	
        
    	return ResponseEntity.status(HttpStatus.OK).body(pessoaMapper.toPessoaDto(pessoaService.findById(id)));
    }
    
    @GetMapping
    @ApiOperation(value="Busca lista de pessoas cadastradas")
    public ResponseEntity<Page<PessoaDto>> getListPessoas(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        
    	return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscaListaPessoa(pageable));
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta pessoa cadastrado pelo ID")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") Long id){            
        pessoaService.delete(id);
       
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("pessoa apagada com sucesso");
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza informações unicas da pessoa ")
    public ResponseEntity<PessoaDto> updatePessoa(@PathVariable(value = "id")Long id,
    		@RequestBody @Valid PessoaDtoInput pessoaDtoInput){
    	 
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.update(id,pessoaDtoInput));
    }
    
    @GetMapping("/{id}/lista-enderecos")
    @ApiOperation(value="Busca lista de endereços da pessoa")
    public ResponseEntity<PessoaListaEnderecoDto> getListaEnderecoPessoa(@PathVariable(value = "id") Long id){	
        
    	return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findByListaEnderecoPessoa(id));
    }
		
}


