package br.com.ehmf.AppProdutos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ehmf.AppProdutos.model.Produto;

@SpringBootApplication
public class AppProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppProdutosApplication.class, args);
		
		//Produto p = new Produto();
		//p.setCodigoBarras("123456");
		//p.setNome("Produtão");
		//p.setPreco(22.30);			
		
	}

}
