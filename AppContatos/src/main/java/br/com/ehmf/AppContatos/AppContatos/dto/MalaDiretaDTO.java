package br.com.ehmf.AppContatos.AppContatos.dto;

import java.util.Objects;

public class MalaDiretaDTO {
	
	Long id;
	String nome;
	String malaDireta;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMalaDireta() {
		return malaDireta;
	}

	public void setMalaDireta(String malaDireta) {
		this.malaDireta = malaDireta;
	}

	public MalaDiretaDTO() {}

	public MalaDiretaDTO(Long id, String nome, String malaDireta) {
		super();
		this.id = id;
		this.nome = nome;
		this.malaDireta = malaDireta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MalaDiretaDTO other = (MalaDiretaDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
