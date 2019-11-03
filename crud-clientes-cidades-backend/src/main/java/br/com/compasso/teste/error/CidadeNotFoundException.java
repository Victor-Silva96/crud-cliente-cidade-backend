package br.com.compasso.teste.error;

public class CidadeNotFoundException extends RuntimeException {

	public CidadeNotFoundException(String cidade) {
		super("Cidade nao cadastrada: "+cidade);
	}
}
