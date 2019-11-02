package br.com.compasso.teste.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.teste.bo.CidadeBO;
import br.com.compasso.teste.model.Cidade;

@RestController
@RequestMapping("/cidade")
public class CidadeController {
	
	@Autowired 
	private CidadeBO cidadeBO;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade cadastrarCidade(@Valid @RequestBody Cidade cidade) {
		return cidadeBO.cadastrarCidade(cidade);
	}
	
	@GetMapping("nome/{nome}")
	public ResponseEntity<Cidade> buscaNome(@PathVariable String nome){
		return cidadeBO.buscaNome(nome);
		
	}
	
	@GetMapping("estado/{estado}")
	public ResponseEntity<List<Cidade>> buscaEstado(@PathVariable String estado){
		return cidadeBO.buscaEstado(estado);
	}
	
}
