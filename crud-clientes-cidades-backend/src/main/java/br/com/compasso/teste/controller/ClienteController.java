package br.com.compasso.teste.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.teste.bo.ClienteBO;
import br.com.compasso.teste.model.Cidade;
import br.com.compasso.teste.model.Cliente;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteBO clienteBO;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastrarCliente(@Valid @RequestBody Cliente cliente) {
		return clienteBO.cadastrarCliente(cliente);
	}
	
	@GetMapping("nome/{nome}")
	public ResponseEntity<Cliente> buscaNome(@PathVariable String nome){
		return clienteBO.buscaNome(nome);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente> buscaId(@PathVariable Long id){
		return clienteBO.buscaId(id);
		
	}
	
	@PutMapping("{id}")
	public Cliente atualizarCliente (@Valid @RequestBody Cliente novoCliente, @PathVariable Long id) {
		return clienteBO.atualizarCliente(novoCliente,id);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id) {
		return clienteBO.deletarCliente(id);
	}

}
