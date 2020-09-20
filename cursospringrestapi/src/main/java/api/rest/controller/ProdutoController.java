package api.rest.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.rest.ObjetoError;
import api.rest.model.Produto;
import api.rest.repositoy.ProdutoRepository;

@RestController
@RequestMapping(value = "/produto")
@CachePut("cacheproduto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	
	@GetMapping(value = "/{id}", produces = "application/json")
	@CachePut("cacheproduto")
	public ResponseEntity<Produto> initproduto(@PathVariable (value = "id") Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
	}
                
	
	
	@ResponseBody
	@PostMapping(value="/", produces = "application/json")
	@CachePut("cacheproduto")
	public ResponseEntity<ObjetoError> salvarproduto(@RequestBody @Valid Produto produto) throws Exception{
		
		ObjetoError objetoError = new ObjetoError();
			
			Produto produtosalvo = produtoRepository.save(produto);
		
		return new ResponseEntity<ObjetoError>(objetoError, HttpStatus.OK);
		
	}

	
	

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<ObjetoError> atualizarproduto (@RequestBody @Valid Produto produto) throws Exception {
		
		ObjetoError objetoError = new ObjetoError();
			
			Produto produtosalvo = produtoRepository.save(produto);
		
		return new ResponseEntity<ObjetoError>(objetoError, HttpStatus.OK);
		
	}
	
	
	
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deleteproduto(@PathVariable("id") Long id){
		
		produtoRepository.deleteById(id);
		
		return "ok";
	}
	
	
	
	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cacheproduto")
	public ResponseEntity<Page<Produto>> produto () throws InterruptedException{
		
		
		PageRequest page = PageRequest.of(0, 5, Sort.by("nomeProduto"));
		
		Page<Produto> listProduto = produtoRepository.findAll(page);
		
		return new ResponseEntity<Page<Produto>>(listProduto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/page/{pagina}", produces = "application/json")
	@CachePut("cacheproduto")
	public ResponseEntity<Page<Produto>> produtoPagina (@PathVariable("pagina") int pagina) throws InterruptedException{
		
		PageRequest page = PageRequest.of(pagina, 5, Sort.by("nomeProduto"));
		
		Page<Produto> listproduto = produtoRepository.findAll(page);
		
		return new ResponseEntity<Page<Produto>>(listproduto, HttpStatus.OK);
	}
	
	

	@GetMapping(value = "/produtoPorNome/{nomeProduto}", produces = "application/json")
	@CachePut("cacheproduto")
	public ResponseEntity<Page<Produto>> produtoPorNome (@PathVariable("nomeProduto") String nomeProduto) throws InterruptedException{
		
		PageRequest pageRequest = null;
		Page<Produto> listProduto = null;
		
		if (nomeProduto == null || (nomeProduto != null && nomeProduto.trim().isEmpty())
				|| nomeProduto.equalsIgnoreCase("undefined")) {
			
			pageRequest = PageRequest.of(0, 5, Sort.by("nomeProduto"));
			listProduto =  produtoRepository.findAll(pageRequest);
			
		}else {
			
			pageRequest = PageRequest.of(0, 5, Sort.by("nomeProduto"));
			listProduto = produtoRepository.findProdutoByNamePage(nomeProduto, pageRequest);
		
		}		
				
		return new ResponseEntity<Page<Produto>>(listProduto, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/produtoPorNome/{nomeProduto}/page/{page}", produces = "application/json")
	@CachePut("cacheproduto")
	public ResponseEntity<Page<Produto>> produtoPorNomePage (@PathVariable("nomeProduto") String nomeProduto, @PathVariable("page") int pageproduto) throws InterruptedException{
		
		PageRequest pageRequest = null;
		Page<Produto> listProduto = null;
		
		if (nomeProduto == null || (nomeProduto != null && nomeProduto.trim().isEmpty())
				|| nomeProduto.equalsIgnoreCase("undefined")) {
			
			pageRequest = PageRequest.of(pageproduto, 5, Sort.by("nomeProduto"));
			listProduto =  produtoRepository.findAll(pageRequest);
		
		}else {
			
			pageRequest = PageRequest.of(pageproduto, 5, Sort.by("nomeProduto"));
			listProduto = produtoRepository.findProdutoByNamePage(nomeProduto, pageRequest);
		
		}		
				
		return new ResponseEntity<Page<Produto>>(listProduto, HttpStatus.OK);
	}
	
	
	
}
