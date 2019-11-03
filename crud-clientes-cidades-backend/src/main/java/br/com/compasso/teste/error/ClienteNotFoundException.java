package br.com.compasso.teste.error;

public class ClienteNotFoundException extends RuntimeException {

	public ClienteNotFoundException(String nome) {
		super("Cliente nao cadastrado: "+nome);
	}
}
