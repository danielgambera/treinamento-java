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

import br.com.ehmf.AppContatos.AppContatos.dto.MalaDiretaDTO;
import br.com.ehmf.AppContatos.AppContatos.model.Contato;
import br.com.ehmf.AppContatos.AppContatos.model.Pessoa;
import br.com.ehmf.AppContatos.AppContatos.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/pessoas") //http://localhost:8080/api/pessoas
public class PessoaResource {
	
	private PessoaService pessoaService;
	
	@Autowired
	public PessoaResource(PessoaService pessoaService)
	{
		this.pessoaService = pessoaService;
	}
	
	@Operation(summary = "Criar pessoa")
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
	
	@Operation(summary = "Atualizar pessoa")
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
	
	@Operation(summary = "Excluir pessoa")
	@DeleteMapping("/{idPessoa}")
	public ResponseEntity<?> excluir(@PathVariable Long idPessoa)
	{
		pessoaService.excluir(idPessoa);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = "Consultar todas as pessoas")
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
	
	@Operation(summary = "Consultar uma pessoa")
	@GetMapping("/{idPessoa}")
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long idPessoa)
	{
		Optional<Pessoa> pessoa = pessoaService.consultar(idPessoa);
		if (pessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoa);
	}
	
	@Operation(summary = "Consultar todos os contatos de uma pessoa")
	@GetMapping("/{idPessoa}/contatos")
	public ResponseEntity<List<Contato>> getContatosById(@PathVariable Long idPessoa)
	{
		List<Contato> listaContatos = pessoaService.consultarContatos(idPessoa);
		if (listaContatos.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(listaContatos);
	}
	
	@Operation(summary = "Consultar mala direta de uma pessoa")
	@GetMapping("maladireta/{idPessoa}")
	public ResponseEntity<MalaDiretaDTO> getMalaDiretaById(@PathVariable Long idPessoa)
	{
		MalaDiretaDTO malaDireta = pessoaService.consultarMalaDiretaPessoa(idPessoa);
		if (malaDireta == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(malaDireta);
	}
}
