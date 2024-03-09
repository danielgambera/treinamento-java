package br.com.ehmf.AppProdutos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ehmf.AppProdutos.dto.ProdutoDTO;
import br.com.ehmf.AppProdutos.dto.ProdutoSimplesDTO;
import br.com.ehmf.AppProdutos.model.Produto;
import br.com.ehmf.AppProdutos.repository.ProdutoRepository;
import br.com.ehmf.AppProdutos.service.interfaces.ProdutoServiceInterface;

@Service
public class ProdutoService implements ProdutoServiceInterface {

	private ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository)
	{
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Optional<Produto> getById(Long id) {
		return produtoRepository.findById(id);
	}

	@Override
	public List<Produto> getAll() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto update(Produto produto) {
		Optional<Produto> findProduto = produtoRepository.findById(produto.getId());
		if (findProduto.isPresent())
		{
			Produto updateProduto = findProduto.get();
			updateProduto.setCodigoBarras(produto.getCodigoBarras());
			updateProduto.setNome(produto.getNome());
			updateProduto.setPreco(produto.getPreco());	
			return produtoRepository.save(updateProduto);
		}
		return produto;
	}

	@Override
	public void delete(Long id) {
		produtoRepository.deleteById(id);	
	}

	@Override
	public List<ProdutoDTO> findProdutosAndQuantidade() {
		List<Object[]> listProdutoObj = produtoRepository.findProdutosAndQuantidade();
		
		List<ProdutoDTO> listProdutoDTO = new ArrayList<>();
		for(Object[] obj : listProdutoObj) {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO.setId(((Long)obj[0]).longValue());
			produtoDTO.setCodigoBarras(((String)obj[1]));
			produtoDTO.setNome(((String)obj[2]));
			produtoDTO.setPreco(((Double)obj[3]).doubleValue());
			produtoDTO.setQuantidade(((Integer)obj[4]).intValue());
			
			listProdutoDTO.add(produtoDTO);
		}
		
		if (listProdutoDTO.size() > 0)
			return listProdutoDTO;
		return null;
				
	}

	@Override
	public List<ProdutoSimplesDTO> findProdutoSimplesAndQuantidade() {
		List<Object[]> listProdutoSimplesObj = produtoRepository.findProdutosSimplesAndQuantidade();
		
		List<ProdutoSimplesDTO> listProdutoSimplesDTO = new ArrayList<>();
		for(Object[] obj : listProdutoSimplesObj) {
			ProdutoSimplesDTO produtoSimplesDTO = new ProdutoSimplesDTO();
			produtoSimplesDTO.setId(((Long)obj[0]).longValue());
			produtoSimplesDTO.setCodigoBarras(((String)obj[1]));
			produtoSimplesDTO.setPreco(((Double)obj[2]).doubleValue());
			produtoSimplesDTO.setQuantidade(((Integer)obj[3]).intValue());
			
			listProdutoSimplesDTO.add(produtoSimplesDTO);
		}
		
		if (listProdutoSimplesDTO.size() > 0)
			return listProdutoSimplesDTO;
		return null;
	}

}
