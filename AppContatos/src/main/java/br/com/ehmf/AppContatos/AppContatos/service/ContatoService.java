package br.com.ehmf.AppContatos.AppContatos.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ehmf.AppContatos.AppContatos.exception.BadRequestException;
import br.com.ehmf.AppContatos.AppContatos.exception.ResourceNotFoundException;
import br.com.ehmf.AppContatos.AppContatos.model.Contato;
import br.com.ehmf.AppContatos.AppContatos.model.Pessoa;
import br.com.ehmf.AppContatos.AppContatos.repository.ContatoRepository;
import br.com.ehmf.AppContatos.AppContatos.repository.PessoaRepository;
import br.com.ehmf.AppContatos.AppContatos.service.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface {

	private ContatoRepository contatoRepository;
	private PessoaRepository pessoaRepository;
	
	@Autowired
	public ContatoService(ContatoRepository contatoRepository, PessoaRepository pessoaRepository)
	{
		this.contatoRepository = contatoRepository;
		this.pessoaRepository = pessoaRepository;
	}
	
	@Override
	public Contato criar(Contato contato) {
		if (contato.getPessoa() == null || contato.getPessoa().getId() == null)
		{
			throw new BadRequestException("[Criar Contato] - necessário pessoa com id válido");
		}
		ValidarContato(contato);
		Optional<Pessoa> consultaPessoa = pessoaRepository.findById(contato.getPessoa().getId());
		if (!consultaPessoa.isPresent())
		{
			throw new ResourceNotFoundException("[Criar Contato] - pessoa " + contato.getPessoa().getId() + " não encontrada");
		}	
		Contato contatoRetorno = contatoRepository.save(contato);
		contatoRetorno.setPessoa(consultaPessoa.get());
		return contatoRetorno;
	}

	@Override
	public Contato atualizar(Contato contato) {
		if (contato.getId() == null)
		{
			throw new BadRequestException("[Atualizar Contato] - id não pode ser nulo");
		}
		ValidarContato(contato);
		Optional<Contato> consultaContato = contatoRepository.findById(contato.getId());
		if (!consultaContato.isPresent())
		{
			throw new ResourceNotFoundException("[Atualizar Contato] - Contato " + contato.getId() + " não encontrato");
		}		
		Contato atualizaContato = consultaContato.get();
		if (contato.getPessoa() != null)
		{
			if (!contato.getPessoa().getId().equals(atualizaContato.getPessoa().getId()))
			{
				throw new BadRequestException("[Atualizar Contato] - não é permitido alterar a pessoa");
			}			
		}
		atualizaContato.setTipoContato(contato.getTipoContato());
		atualizaContato.setContato(contato.getContato());
		return contatoRepository.save(atualizaContato);
	}

	@Override
	public void excluir(Long idContato) {
		contatoRepository.deleteById(idContato);			
	}

	@Override
	public Optional<Contato> consultar(Long idContato) {
		return contatoRepository.findById(idContato);
	}

	@Override
	public List<Contato> consultar() {
		return contatoRepository.findAll();
	}

	private void ValidarContato(Contato contato)
	{
		if (contato.getContato().isBlank())
		{
			throw new BadRequestException("[Contato] - contato não pode ser vazio");
		}
		if (contato.getTipoContato() == null || contato.getTipoContato() < 0 || contato.getTipoContato() > 3)
		{
			throw new BadRequestException("[Contato] - tipoContato deve ser um numero entre 0 e 3");
		}
	}

}
