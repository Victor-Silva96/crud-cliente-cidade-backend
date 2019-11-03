package br.com.compasso.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.teste.model.Cidade;
import br.com.compasso.teste.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	public Optional<Cliente> findByNome(String nome);
}
