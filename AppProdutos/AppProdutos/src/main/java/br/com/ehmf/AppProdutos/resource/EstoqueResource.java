package br.com.ehmf.AppProdutos.resource;

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

import br.com.ehmf.AppProdutos.model.Estoque;
import br.com.ehmf.AppProdutos.model.Produto;
import br.com.ehmf.AppProdutos.service.EstoqueService;


@RestController
@RequestMapping("/api/estoque")

public class EstoqueResource {

	private EstoqueService estoqueService;
	
	@Autowired
	public EstoqueResource(EstoqueService estoqueService)
	{
		this.estoqueService = estoqueService;
	}
	
	@PostMapping

	public ResponseEntity<Estoque> save(@RequestBody Estoque estoque)
	{
		Estoque newEstoque = estoqueService.save(estoque);
		if (newEstoque == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newEstoque);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Estoque>> getById(@PathVariable Long id)
	{
		Optional<Estoque> findEstoque = estoqueService.getById(id);
		if (findEstoque == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(findEstoque);
	}
	
	@GetMapping
	public ResponseEntity<List<Estoque>> getAll()
	{
		List<Estoque> estoques = estoqueService.getAll();
		if (estoques == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(estoques);
	}
		
	@PutMapping
	public ResponseEntity<Estoque> update(@RequestBody Estoque estoque)
	{
		Estoque findEstoque = estoqueService.update(estoque);
		if (findEstoque == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(findEstoque);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		estoqueService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/addQuantidade/{qtde}")
	public ResponseEntity<Estoque> addQuantidade(@RequestBody Produto produto, @PathVariable int qtde)
	{
		Estoque findEstoque = estoqueService.addQuantidade(produto, qtde);
		if (findEstoque == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(findEstoque);
	}
	
	@PostMapping("/delQuantidade/{qtde}")
	public ResponseEntity<Estoque> delQuantidade(@RequestBody Produto produto, @PathVariable int qtde)
	{
		Estoque findEstoque = estoqueService.delQuantidade(produto, qtde);
		if (findEstoque == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(findEstoque);
	}
	
	@GetMapping("/findEstoqueQuantidade/{qtde}")
	public ResponseEntity<List<Estoque>> findEstoqueQuantidade(@PathVariable Integer qtde)
	{
		List<Estoque> listEstoque = estoqueService.findEstoqueQuantidade(qtde);
		if (listEstoque == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(listEstoque);
	
	}
	
	@GetMapping("/findEstoqueQuantidadeGreaterThan/{qtde}")
	public ResponseEntity<List<Estoque>> findEstoqueQuantidadeGreaterThan(@PathVariable Integer qtde)
	{
		List<Estoque> listEstoque = estoqueService.findEstoqueQuantidadeGreaterThan(qtde);
		if (listEstoque == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(listEstoque);	
	}
	
}
