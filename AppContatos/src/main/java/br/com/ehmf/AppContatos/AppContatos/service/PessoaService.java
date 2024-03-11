package br.com.ehmf.AppContatos.AppContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ehmf.AppContatos.AppContatos.exception.BadRequestException;
import br.com.ehmf.AppContatos.AppContatos.exception.ResourceNotFoundException;
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
		if (pessoa.getId() != null)
		{
			throw new BadRequestException("[Criar Pessoa] - id deve ser nulo");
		}
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Pessoa atualizar(Pessoa pessoa) {
		if (pessoa.getId() == null)
		{
			throw new BadRequestException("[Atualizar Pessoa] - id não pode ser nulo");
		}
		Optional<Pessoa> consultaPessoa = pessoaRepository.findById(pessoa.getId());
		if (!consultaPessoa.isPresent())
		{
			throw new ResourceNotFoundException("[Atualizar Pessoa] - pessoa " + pessoa.getId() + " não encontrada");
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
		Optional<Pessoa> excluirPessoa = pessoaRepository.findById(idPessoa);
		if (!excluirPessoa.isPresent())
		{
			throw new ResourceNotFoundException("[Excluir Pessoa] - pessoa " + idPessoa+ " não encontrada");
		}	
		pessoaRepository.deleteById(idPessoa);		
	}

	@Override
	public Optional<Pessoa> consultar(Long idPessoa) {
		Optional<Pessoa> consultarPessoa = pessoaRepository.findById(idPessoa);
		if (!consultarPessoa.isPresent())
		{
			throw new ResourceNotFoundException("[Excluir Pessoa] - pessoa " + idPessoa+ " não encontrada");
		}	
		return pessoaRepository.findById(idPessoa);
	}

	@Override
	public List<Pessoa> consultar() {
		return pessoaRepository.findAll();
	}



}
