package com.pessoaCadastro.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="endereco")
public class Endereco {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String logradouro ;
	
	private String cep;
	
	private String numero;
	
	private String cidade;
	
	@Column(columnDefinition = "false")
	private boolean principal; 

	@ManyToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;
	
}

