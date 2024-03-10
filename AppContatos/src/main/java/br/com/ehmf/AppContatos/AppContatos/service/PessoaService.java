package br.com.ehmf.AppContatos.AppContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ehmf.AppContatos.AppContatos.model.Pessoa;
import br.com.ehmf.AppContatos.AppContatos.repository.PessoaRepository;
import br.com.ehmf.AppContatos.AppContatos.service.interfaces.PessoaServiceInterface;

@Service
public class PessoaService implements PessoaServiceInterface {
	
	private PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository)
	{
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public Pessoa criar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Pessoa atualizar(Pessoa pessoa) {
		Optional<Pessoa> consultaPessoa = pessoaRepository.findById(pessoa.getId());
		if (!consultaPessoa.isPresent())
		{
			return null;
		}		
		Pessoa atualizaPessoa = consultaPessoa.get();
		atualizaPessoa.setNome(pessoa.getNome());
		atualizaPessoa.setEndereco(pessoa.getEndereco());
		atualizaPessoa.setCep(pessoa.getCep());
		atualizaPessoa.setCidade(pessoa.getCidade());
		atualizaPessoa.setUf(pessoa.getUf());		
		return pessoaRepository.save(atualizaPessoa);
	}

	@Override
	public void excluir(Long idPessoa) {
		pessoaRepository.deleteById(idPessoa);		
	}

	@Override
	public Optional<Pessoa> consultar(Long idPessoa) {
		return pessoaRepository.findById(idPessoa);
	}

	@Override
	public List<Pessoa> consultar() {
		return pessoaRepository.findAll();
	}



}
