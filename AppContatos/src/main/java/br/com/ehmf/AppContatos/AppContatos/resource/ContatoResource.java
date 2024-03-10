package br.com.ehmf.AppContatos.AppContatos.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ehmf.AppContatos.AppContatos.model.Contato;
import br.com.ehmf.AppContatos.AppContatos.service.ContatoService;

@RestController
@RequestMapping("/api/contatos") //http://localhost:8080/api/pessoas
public class ContatoResource {

	private ContatoService contatoService;
	
	@Autowired
	public ContatoResource(ContatoService contatoService)
	{
		this.contatoService = contatoService;
	}
	
	@PostMapping
	public ResponseEntity<Contato> criar(@RequestBody Contato contato)
	{
		Contato contatoCriado = contatoService.criar(contato);
		if (contatoCriado == null)
		{
			return null;
		}

		return ResponseEntity.ok(contatoCriado);
	}
	
	@PutMapping
	public ResponseEntity<Contato> atualizar(@RequestBody Contato contato)	
	{
		Contato contatoAtualizado = contatoService.atualizar(contato);
		if (contatoAtualizado == null)
		{
			return null;
		}

		return ResponseEntity.ok(contatoAtualizado);
	}
	
	@DeleteMapping("/{idContato}")
	public ResponseEntity<?> excluir(@PathVariable Long idContato)
	{
		contatoService.excluir(idContato);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<Contato>> Consultar()
	{
		List<Contato> listaContatos = contatoService.consultar();
		if (listaContatos.isEmpty())
		{
			return null;
		}
		
		return ResponseEntity.ok(listaContatos);
	}
	
	@GetMapping("/{idContato}")
	public ResponseEntity<Optional<Contato>> getById(@PathVariable Long idContato)
	{
		Optional<Contato> contato = contatoService.consultar(idContato);
		if (contato == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(contato);
	}
}
