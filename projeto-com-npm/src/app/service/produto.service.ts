import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstants } from '../app-constants';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private http: HttpClient) { }

  getStudentListProduto(): Observable<any> {
    return this.http.get<any>(AppConstants.baseProduto);
  }

  getCidadeListProduto(): Observable<any> {
    return this.http.get<any>(AppConstants.getBaseUrlPath + 'cidade/');
  }

  getStudentListPageProduto(pagina): Observable<any> {
    return this.http.get<any>(AppConstants.baseProduto + 'page/' + pagina);
  }


  getStudantProduto(id): Observable<any> {
    return this.http.get<any>(AppConstants.baseProduto + id);
  }


  deletarProduto(id: Number): Observable<any> {
    return this.http.delete(AppConstants.baseProduto + id, { responseType: 'text' });
  }


  consultarProduto(nomeProduto: String): Observable<any> {
    return this.http.get(AppConstants.baseProduto + "produtoPorNome/" + nomeProduto);

  }

  consultarProdutoPorPage(nomeProduto: String, page: Number): Observable<any> {
    return this.http.get(AppConstants.baseProduto + "produtoPorNome/" + nomeProduto + "/page/" + page);

  }

  salvarProduto(produto): Observable<any> {
    return this.http.post<any>(AppConstants.baseProduto, produto);
  }

  updateProduto(produto): Observable<any> {
    return this.http.put<any>(AppConstants.baseProduto, produto);
  }
  

}

