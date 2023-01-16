package com.pessoaCadastro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaCadastro.dto.EnderecoDto;
import com.pessoaCadastro.dto.input.EnderecoDtoInput;
import com.pessoaCadastro.service.EnderecoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RequestMapping("/endereco")
@RestController
@Api(value="Cadastro pessoa")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@PostMapping
	@ApiOperation(value="Cadastrar novo Endereco")
	public ResponseEntity<EnderecoDto> saveEndereco(@RequestBody @Valid EnderecoDtoInput enderecoDtoInput){				
		
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(enderecoDtoInput));			
	}

    @PutMapping("/{id}/principal")
    @ApiOperation(value="Informa Indereço principal")
    public ResponseEntity<EnderecoDto> updatePessoa(@PathVariable(value = "id")Long id){   	 
        
    	return ResponseEntity.status(HttpStatus.OK).body(enderecoService.updateEnderecoPrincipal(id));
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta endereço cadastrado pelo ID")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") Long id){            
       enderecoService.delete(id);
        
       return ResponseEntity.status(HttpStatus.OK).body("pessoa apagada com sucesso");
    }

}
