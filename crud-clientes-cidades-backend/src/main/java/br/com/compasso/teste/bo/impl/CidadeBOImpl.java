package br.com.compasso.teste.bo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.compasso.teste.bo.CidadeBO;
import br.com.compasso.teste.model.Cidade;
import br.com.compasso.teste.repository.CidadeRepository;

@Component
public class CidadeBOImpl implements CidadeBO {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public Cidade cadastrarCidade(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	@Override
	public ResponseEntity<Cidade> buscaNome(String nome) {
		Optional<Cidade> cidade = cidadeRepository.findByNome(nome);
		
		if(!cidade.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cidade.get());
		
	}

	@Override
	public ResponseEntity<List<Cidade>> buscaEstado(String estado) {
		List<Cidade> cidades = cidadeRepository.findByEstado(estado);
		if(cidades.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cidades);
	}

	
	

}
