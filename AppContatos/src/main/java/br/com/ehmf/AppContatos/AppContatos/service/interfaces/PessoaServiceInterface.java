package br.com.ehmf.AppContatos.AppContatos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.ehmf.AppContatos.AppContatos.model.Pessoa;

public interface PessoaServiceInterface {
	
	Pessoa criar(Pessoa pessoa);
	Pessoa atualizar(Pessoa pessoa);
	void excluir(Long idPessoa);
	
	Optional<Pessoa> consultar(Long idPessoa);	
	List<Pessoa> consultar();	
	
}
