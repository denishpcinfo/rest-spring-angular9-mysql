import { Component, OnInit } from '@angular/core';
import { ProdutoService } from 'src/app/service/produto.service';
import { Produto } from 'src/app/model/produto';
import { Cidade } from 'src/app/model/cidade';


@Component({
  selector: 'app-root',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {

  cidadeProdutos : Array<Cidade>;

  studentsProduto: Array<Produto[]>;
  nomeProduto: String;
  totalProduto: number;
  pProduto: number;

  constructor( private produtoService: ProdutoService) { }

  ngOnInit() {

    this.produtoService.getCidadeListProduto().subscribe(dataProduto => {
      this.cidadeProdutos = dataProduto;
    });

    this.produtoService.getStudentListProduto().subscribe(dataProduto => {
      this.studentsProduto = dataProduto.content;
      this.totalProduto = dataProduto.totalElements;
    });
  }

  deleteProduto(id: Number, index) {

    if (confirm('Deseja mesmo remover?')) {

      this.produtoService.deletarProduto(id).subscribe(dataProduto => {

        this.studentsProduto.splice(index, 1);

      });
    }
  }

  consultarProduto() {

    if (this.nomeProduto === '') {
      this.produtoService.getStudentListProduto().subscribe(dataProduto => {
        this.studentsProduto = dataProduto.content;
        this.totalProduto = dataProduto.totalElements;
      });
    } else {

      this.produtoService.consultarProduto(this.nomeProduto).subscribe(dataProduto => {
        this.studentsProduto = dataProduto.content;
        this.totalProduto = dataProduto.totalElements;
      });
    }
  }


  carregarPaginaProduto(pagina) {
    if (this.nomeProduto !== '') {
      this.produtoService.consultarProdutoPorPage(this.nomeProduto, (pagina - 1)).subscribe(dataProduto => {
        this.studentsProduto = dataProduto.content;
        this.totalProduto = dataProduto.totalElements;
      });
    }
    else {
      this.produtoService.getStudentListPageProduto(pagina - 1).subscribe(dataProduto => {
        this.studentsProduto = dataProduto.content;
        this.totalProduto = dataProduto.totalElements;
      });
    }

  }

}
