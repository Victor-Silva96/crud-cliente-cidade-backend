package br.com.compasso.teste.error;

public class SexoValidationException extends RuntimeException {
	public SexoValidationException() {
		super("Valor nao permitido escreva masculino ou feminino");
	}
}
