import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Produto } from 'src/app/model/produto';
import { ProdutoService } from 'src/app/service/produto.service';
import { Cidade } from 'src/app/model/cidade';


@Component({
  selector: 'app-produto-add',
  templateUrl: './produto-add.component.html',
  styleUrls: ['./produto-add.component.css']
})
export class ProdutoAddComponent implements OnInit {

  produto = new Produto();

    cidadeProdutos : Array<Cidade>;

    constructor(private routeActive: ActivatedRoute, private produtoService: ProdutoService) {
    }
  
  
    ngOnInit() {
      
      this.produtoService.getCidadeListProduto().subscribe(dataFornecedor => {
        this.cidadeProdutos = dataFornecedor;
      });

      let id = this.routeActive.snapshot.paramMap.get('id');
  
      if (id != null) {
        this.produtoService.getStudantProduto(id).subscribe(dataProduto => {
          this.produto = dataProduto;
        });
  
      }
  
    }
  
  
    salvarProduto() {
      if (this.produto.id != null && this.produto.id.toString().trim() != null) { 
        this.produtoService.updateProduto(this.produto).subscribe(data => {
          this.novoProduto();
          alert("Produto Atualizado!");
        });
      } else {
        this.produtoService.salvarProduto(this.produto).subscribe(data => { 
          this.novoProduto();
          alert("Criado novo Produto! ");
        });
      }
    }

    novoProduto() {
      this.produto = new Produto();
    }
  
  }