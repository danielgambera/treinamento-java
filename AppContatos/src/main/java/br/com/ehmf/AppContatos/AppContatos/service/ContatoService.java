package br.com.ehmf.AppContatos.AppContatos.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ehmf.AppContatos.AppContatos.model.Contato;
import br.com.ehmf.AppContatos.AppContatos.repository.ContatoRepository;
import br.com.ehmf.AppContatos.AppContatos.service.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface {

	private ContatoRepository contatoRepository;
	
	@Autowired
	public ContatoService(ContatoRepository contatoRepository)
	{
		this.contatoRepository = contatoRepository;
	}
	
	@Override
	public Contato criar(Contato contato) {
		return contatoRepository.save(contato);
	}

	@Override
	public Contato atualizar(Contato contato) {
		Optional<Contato> consultaContato = contatoRepository.findById(contato.getId());
		if (!consultaContato.isPresent())
		{
			return null;
		}
		Contato atualizaContato = consultaContato.get();
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

	

}
