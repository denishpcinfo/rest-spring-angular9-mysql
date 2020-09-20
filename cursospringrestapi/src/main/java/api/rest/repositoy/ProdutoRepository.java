package api.rest.repositoy;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import api.rest.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	default Page<Produto> findProdutoByNamePage(String nomeProduto, PageRequest pageRequest) {
		
		Produto produto = new Produto();
		produto.setNomeProduto(nomeProduto);
		
		/*Configurando para pesquisar por nomeCliente e paginação*/
		ExampleMatcher exampleMatcherCliente = ExampleMatcher.matchingAny()
				.withMatcher("nomeProduto", ExampleMatcher.GenericPropertyMatchers
						.contains().ignoreCase());
		
		Example<Produto> exampleProduto = Example.of(produto, exampleMatcherCliente);
		
		Page<Produto> retornoProduto = findAll(exampleProduto, pageRequest);
		
		return retornoProduto;
		

	}
}