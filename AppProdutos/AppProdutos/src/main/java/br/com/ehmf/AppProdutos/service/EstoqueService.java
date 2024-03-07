package br.com.ehmf.AppProdutos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ehmf.AppProdutos.model.Estoque;
import br.com.ehmf.AppProdutos.model.Produto;
import br.com.ehmf.AppProdutos.repository.EstoqueRepository;
import br.com.ehmf.AppProdutos.repository.ProdutoRepository;
import br.com.ehmf.AppProdutos.service.interfaces.EstoqueServiceInterface;

@Service
public class EstoqueService implements EstoqueServiceInterface {

	private EstoqueRepository estoqueRepository;
	
	private ProdutoRepository produtoRepository;
	
	
	public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository)
	{
		this.estoqueRepository = estoqueRepository;
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public Estoque save(Estoque estoque) {
		
		if (estoque.getId() == null)
		{
			System.out.println("Produto não encontrado");
			return null;			
		}
		
		Optional<Produto> findProduto = produtoRepository.findById(estoque.getProduto().getId());
		if (findProduto == null || findProduto.isEmpty())
		{
			System.out.println("Produto não encontrado");
			return null;
		}
		else
		{
			estoque.setProduto(findProduto.get());
			return estoqueRepository.save(estoque);
		}
	}

	@Override
	public Optional<Estoque> getById(Long id) {
		return estoqueRepository.findById(id);
	}

	@Override
	public List<Estoque> getAll() {
		return estoqueRepository.findAll();
	}

	@Override
	public Estoque update(Estoque estoque) {
		Optional<Estoque> findEstoque = estoqueRepository.findById(estoque.getId());
		if (findEstoque.isPresent())
		{
			Estoque updateEstoque = findEstoque.get();
			updateEstoque.setProduto(findEstoque.get().getProduto());
			updateEstoque.setQuantidade(estoque.getQuantidade());
			return estoqueRepository.save(updateEstoque);
		}
		return estoque;
	}

	@Override
	public void delete(Long id) {
		estoqueRepository.deleteById(id);
		
	}

}
