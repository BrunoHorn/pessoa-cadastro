package com.pessoaCadastro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.pessoaCadastro.dto.PessoaDto;
import com.pessoaCadastro.dto.PessoaListaEnderecoDto;
import com.pessoaCadastro.dto.input.PessoaDtoInput;
import com.pessoaCadastro.mapper.PessoaMapper;
import com.pessoaCadastro.service.PessoaService;

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
    public ResponseEntity<List<PessoaDto>> getListPessoas(){
        
    	return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta pessoa cadastrado pelo ID")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") Long id){            
        pessoaService.delete(id);
       
        return ResponseEntity.status(HttpStatus.OK).body("pessoa apagada com sucesso");
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


