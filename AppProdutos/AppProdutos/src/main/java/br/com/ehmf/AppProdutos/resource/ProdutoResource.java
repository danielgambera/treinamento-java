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

import br.com.ehmf.AppProdutos.dto.ProdutoDTO;
import br.com.ehmf.AppProdutos.dto.ProdutoSimplesDTO;
import br.com.ehmf.AppProdutos.model.Produto;
import br.com.ehmf.AppProdutos.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos") //http://localhost:8080/api/produtos
public class ProdutoResource {
	
	private ProdutoService produtoService;
	
	@Autowired
	public ProdutoResource(ProdutoService produtoService)
	{
		this.produtoService = produtoService;
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos()
	{
		List<Produto> produtos = produtoService.getAll();
		if(produtos == null || produtos.size()==0)
			return ResponseEntity.notFound().build();		
		return ResponseEntity.ok(produtos);
	}
	
	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody Produto produto)
	{
		Produto newProduto = produtoService.save(produto);
		if (newProduto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newProduto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> getById(@PathVariable Long id)
	{
		Optional<Produto> produto = produtoService.getById(id);
		if (produto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(produto);
	}
	
	@PutMapping
	public ResponseEntity<Produto> update(@RequestBody Produto produto)
	{
		Produto upProduto = produtoService.save(produto);
		if (upProduto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(upProduto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		produtoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/findProdutosAndQuantidade")
	public ResponseEntity<List<ProdutoDTO>> findProdutosAndQuantidade(){
		List<ProdutoDTO> listProdutoDTO = produtoService.findProdutosAndQuantidade();
		if (listProdutoDTO == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(listProdutoDTO);
	}
	
	@GetMapping("/findProdutosSimplesAndQuantidade")
	public ResponseEntity<List<ProdutoSimplesDTO>> findProdutosSimplesAndQuantidade(){
		List<ProdutoSimplesDTO> listProdutoSimplesDTO = produtoService.findProdutoSimplesAndQuantidade();
		if (listProdutoSimplesDTO == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(listProdutoSimplesDTO);
	}
	
}
