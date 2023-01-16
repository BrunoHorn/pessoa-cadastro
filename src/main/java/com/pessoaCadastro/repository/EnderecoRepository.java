package com.pessoaCadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaCadastro.model.Endereco;
import com.pessoaCadastro.model.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	Optional<Endereco> findByPessoaAndPrincipal(Pessoa pessoa, boolean principal);
	
	List<Endereco> findByPessoa(Pessoa pessoa);
}
