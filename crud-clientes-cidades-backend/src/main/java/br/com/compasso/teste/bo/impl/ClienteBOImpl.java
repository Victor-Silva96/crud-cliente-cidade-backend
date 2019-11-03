package br.com.compasso.teste.bo.impl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.compasso.teste.bo.ClienteBO;
import br.com.compasso.teste.error.CidadeNotFoundException;
import br.com.compasso.teste.error.ClienteNotFoundException;
import br.com.compasso.teste.error.SexoValidationException;
import br.com.compasso.teste.model.Cidade;
import br.com.compasso.teste.model.Cliente;
import br.com.compasso.teste.repository.CidadeRepository;
import br.com.compasso.teste.repository.ClienteRepository;

@Component
public class ClienteBOImpl implements ClienteBO {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public Cliente cadastrarCliente(Cliente cliente) {
		 Optional<Cidade> cidade = cidadeRepository.findByNome(cliente.getCidade());
		 if(cliente.getSexo().toUpperCase().equals("MASCULINO") || cliente.getSexo().toUpperCase().equals("FEMININO")) {
			if(cidade.isPresent()) {
				cliente.setSexo(cliente.getSexo().toUpperCase());
				return clienteRepository.save(cliente);
			}
			else {
				throw new CidadeNotFoundException(cliente.getCidade());
			}
		 }
		 else {
			 throw new SexoValidationException();
		 }
		}

	@Override
	public ResponseEntity<Cliente> buscaNome(String nome) {
		Optional<Cliente> cliente = clienteRepository.findByNome(nome);
		
		if(!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente.get());
	}

	@Override
	public ResponseEntity<Cliente> buscaId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente.get());
	}

	@Override
	public Cliente atualizarCliente(Cliente novoCliente, Long id) {
		return clienteRepository.findById(id)
				.map(cliente ->{
					cliente.setNome(novoCliente.getNome());
					return clienteRepository.save(cliente);
				
				})
				.orElseThrow(() -> new ClienteNotFoundException(novoCliente.getNome()));
	}

	@Override
	public ResponseEntity<Cliente> deletarCliente(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(cliente.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
		
	}
}


