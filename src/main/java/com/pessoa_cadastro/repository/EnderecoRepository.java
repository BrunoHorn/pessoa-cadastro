package com.pessoa_cadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoa_cadastro.model.Endereco;
import com.pessoa_cadastro.model.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	Optional<Endereco> findByPessoaAndPrincipal(Pessoa pessoa, boolean principal);
	
	List<Endereco> findByPessoa(Pessoa pessoa);
}
