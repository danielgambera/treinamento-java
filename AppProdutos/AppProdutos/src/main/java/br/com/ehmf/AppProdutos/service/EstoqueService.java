package br.com.ehmf.AppProdutos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ehmf.AppProdutos.exception.ResourceNotFoundException;
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

		
	@Override
	public Estoque addQuantidade(Produto produto, int quantidade) {
		Optional<Produto> findProduto = produtoRepository.findById(produto.getId());
		if (!findProduto.isPresent())
		{
			return null;
		}
		
		Optional<Estoque> findEstoque = estoqueRepository.findByProduto(findProduto);
		if (!findEstoque.isPresent())
		{
			return null;
		}
		
		Estoque updateEstoque = findEstoque.get();
		updateEstoque.setProduto(findEstoque.get().getProduto());
		updateEstoque.setQuantidade(findEstoque.get().getQuantidade() + quantidade);	
		
		return estoqueRepository.save(updateEstoque);		
	}
	

	@Override
	public Estoque delQuantidade(Produto produto, int quantidade) {
		
		Optional<Produto> findProduto = produtoRepository.findById(produto.getId());
		if (!findProduto.isPresent())
		{
			throw new ResourceNotFoundException("[Estoque] Produto não encontrado : " + produto.getId());
		}
		
		Optional<Estoque> findEstoque = estoqueRepository.findByProduto(findProduto);
		if (!findEstoque.isPresent())
		{
			throw new ResourceNotFoundException("[Estoque] Estoque não encontrado para o produto: " + produto.getId());

		}
		
		Estoque updateEstoque = findEstoque.get();
		updateEstoque.setProduto(findEstoque.get().getProduto());
		updateEstoque.setQuantidade(findEstoque.get().getQuantidade() - quantidade);
		
		if (updateEstoque.getQuantidade() < 0)
		{
			System.out.println("Não há itens suficientes no estoque para essa operação.");
			return null;
		}
		
		return estoqueRepository.save(updateEstoque);		
	}

	@Override
	public List<Estoque> findEstoqueQuantidade(Integer quantidade) {
		List<Estoque> listEstoque = estoqueRepository.findEstoqueQuantidadeLessThan(quantidade);
		if (listEstoque.size() < 1)
			return null;
		return listEstoque;
	}

	@Override
	public List<Estoque> findEstoqueQuantidadeGreaterThan(Integer quantidade) {
		List<Estoque> listEstoque = estoqueRepository.findEstoqueQuantidadeGreaterThan(quantidade);
		if (listEstoque.size() < 1)
			return null;
		return listEstoque;
	}

}
