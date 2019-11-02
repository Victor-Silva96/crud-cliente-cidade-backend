package br.com.compasso.teste.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.teste.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	
	public Optional<Cidade> findByNome(String nome);
	public List<Cidade> findByEstado(String estado);

}
