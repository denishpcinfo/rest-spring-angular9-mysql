import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';
import { ProdutoComponent } from './componente/produto/produto/produto.component';
import { ProdutoAddComponent } from './componente/produto/produto-add/produto-add.component';

const routes: Routes = [
  { path: '', component: LayoutComponent, children: [
  { path : 'home', component: HomeComponent},
	{ path: 'produtoList', component: ProdutoComponent },
  { path: 'produtoAdd', component: ProdutoAddComponent },
  { path: 'produtoAdd/:id', component: ProdutoAddComponent }

]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
