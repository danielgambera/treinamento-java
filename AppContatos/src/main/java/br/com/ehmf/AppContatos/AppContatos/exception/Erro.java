package br.com.ehmf.AppContatos.AppContatos.exception;

import java.time.LocalDateTime;

public class Erro {

	private String erro;
	private LocalDateTime data;
	private String modulo;
	
	public Erro() {}
	
	public Erro(String erro, String modulo)
	{
		this.erro = erro;
		this.modulo = modulo;
		this.data = LocalDateTime.now();
	}

	public String getErro() {
		return erro;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getSistema() {
		return modulo;
	}
	
}
