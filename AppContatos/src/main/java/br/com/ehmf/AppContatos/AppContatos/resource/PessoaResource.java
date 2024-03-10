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

import br.com.ehmf.AppContatos.AppContatos.model.Pessoa;
import br.com.ehmf.AppContatos.AppContatos.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas") //http://localhost:8080/api/pessoas
public class PessoaResource {
	
	private PessoaService pessoaService;
	
	@Autowired
	public PessoaResource(PessoaService pessoaService)
	{
		this.pessoaService = pessoaService;
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa)
	{
		Pessoa pessoaCriada = pessoaService.criar(pessoa);
		if (pessoaCriada == null)
		{
			return null;
		}

		return ResponseEntity.ok(pessoaCriada);
	}
	
	@PutMapping
	public ResponseEntity<Pessoa> atualizar(@RequestBody Pessoa pessoa)	
	{
		Pessoa pessoaAtualizada= pessoaService.atualizar(pessoa);
		if (pessoaAtualizada == null)
		{
			return null;
		}

		return ResponseEntity.ok(pessoaAtualizada);
	}
	
	@DeleteMapping("/{idPessoa}")
	public ResponseEntity<?> excluir(@PathVariable Long idPessoa)
	{
		pessoaService.excluir(idPessoa);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> Consultar()
	{
		List<Pessoa> listaPessoas = pessoaService.consultar();
		if (listaPessoas.isEmpty())
		{
			return null;
		}
		
		return ResponseEntity.ok(listaPessoas);
	}
	
	@GetMapping("/{idPessoa}")
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long idPessoa)
	{
		Optional<Pessoa> pessoa = pessoaService.consultar(idPessoa);
		if (pessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoa);
	}
}
