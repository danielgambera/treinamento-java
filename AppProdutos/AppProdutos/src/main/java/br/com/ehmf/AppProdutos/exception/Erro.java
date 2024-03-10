package br.com.ehmf.AppProdutos.exception;

import java.time.LocalDateTime;

public class Erro {
	
	private String erro;
	private LocalDateTime data;
	private String sistema;
	
	public Erro() {}
	
	public Erro(String erro, String sistema)
	{
		this.erro = erro;
		this.sistema = sistema;
		this.data = LocalDateTime.now();
	}

	public String getErro() {
		return erro;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getSistema() {
		return sistema;
	}

}
