package br.com.ehmf.AppProdutos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.ehmf.AppProdutos.dto.ProdutoDTO;
import br.com.ehmf.AppProdutos.dto.ProdutoSimplesDTO;
import br.com.ehmf.AppProdutos.model.Produto;

public interface ProdutoServiceInterface {
	

	Produto save(Produto produto);
	Optional<Produto> getById(Long id);
	List<Produto> getAll();	
	Produto update(Produto produto);	
	void delete(Long id);
	List<ProdutoDTO> findProdutosAndQuantidade();
	List<ProdutoSimplesDTO> findProdutoSimplesAndQuantidade();

}
