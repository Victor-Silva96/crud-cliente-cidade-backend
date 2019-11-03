package br.com.compasso.teste.bo;

import org.springframework.http.ResponseEntity;

import br.com.compasso.teste.model.Cidade;
import br.com.compasso.teste.model.Cliente;

public interface ClienteBO {
	Cliente cadastrarCliente(Cliente cliente);
	ResponseEntity<Cliente> buscaNome(String nome);
	ResponseEntity<Cliente> buscaId(Long nome);
	Cliente atualizarCliente(Cliente novoCliente, Long id);
	ResponseEntity<Cliente> deletarCliente(Long id);
}
