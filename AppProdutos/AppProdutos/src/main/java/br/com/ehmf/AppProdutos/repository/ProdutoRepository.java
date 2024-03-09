package br.com.ehmf.AppProdutos.repository;

import org.springframework.stereotype.Repository;

import br.com.ehmf.AppProdutos.dto.ProdutoDTO;
import br.com.ehmf.AppProdutos.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query(value = "select 	p.id,\r\n"
			+ "		p.codigo_barras,\r\n"
			+ "		p.nome,\r\n"
			+ "		p.preco,\r\n"
			+ "		e.quantidade \r\n"
			+ "from produto p \r\n"
			+ "inner join estoque e \r\n"
			+ "on p.id = e.produto_id \r\n"
			+ "", nativeQuery = true)
	List<Object[]> findProdutosAndQuantidade();
	
	@Query(value = "SELECT * FROM public.buscar_produtos_estoque();", nativeQuery = true)
	List<Object[]> findProdutosSimplesAndQuantidade();

}
