package br.com.ehmf.AppContatos.AppContatos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.ehmf.AppContatos.AppContatos.model.Contato;

public interface ContatoServiceInterface {

	Contato criar(Contato contato);
	Contato atualizar(Contato contato);
	void excluir(Long idContato);
	
	Optional<Contato> consultar(Long idContato);	
	List<Contato> consultar();	
	
}
