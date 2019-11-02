package br.com.compasso.teste.bo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.compasso.teste.model.Cidade;


public interface CidadeBO {
	Cidade cadastrarCidade(Cidade cidade);
	ResponseEntity<Cidade> buscaNome(String nome);
	ResponseEntity<List<Cidade>> buscaEstado(String estado);
	
}
