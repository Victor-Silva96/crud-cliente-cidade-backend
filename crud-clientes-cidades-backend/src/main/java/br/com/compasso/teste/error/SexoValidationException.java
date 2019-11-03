package br.com.compasso.teste.error;

public class SexoValidationException extends RuntimeException {
	public SexoValidationException() {
		super("Valor não permitido escreva masculino ou feminino");
	}
}
