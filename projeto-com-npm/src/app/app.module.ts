import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, } from '@angular/forms'

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';

import { NgxMaskModule, IConfig } from 'ngx-mask';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxCurrencyModule} from "ngx-currency";
import {ChartsModule} from 'ng2-charts';
import { ProdutoComponent } from './componente/produto/produto/produto.component';
import { ProdutoAddComponent } from './componente/produto/produto-add/produto-add.component';


export const optionsMask: Partial<IConfig> | (() => Partial<IConfig>) = {};

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LayoutComponent,
    ProdutoComponent,
    ProdutoAddComponent,
   
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    TemplateModule,
    NgxMaskModule.forRoot(optionsMask),
    NgxPaginationModule,
    NgbModule,
    NgxCurrencyModule,
    ChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
