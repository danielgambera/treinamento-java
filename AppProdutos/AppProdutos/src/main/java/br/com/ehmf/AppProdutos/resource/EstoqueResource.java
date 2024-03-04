package br.com.ehmf.AppProdutos.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ehmf.AppProdutos.model.Estoque;
import br.com.ehmf.AppProdutos.service.EstoqueService;

@Controller
@RequestMapping("/api/estoque") //http://localhost:8080/api/estoque
public class EstoqueResource {

	private EstoqueService estoqueService;
	
	@Autowired
	public EstoqueResource(EstoqueService estoqueService)
	{
		this.estoqueService = estoqueService;		
	}
	
	@PostMapping("/gravar")
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
}
