import { Cidade } from './cidade';

export class Produto {

    id: Number;
	nomeProduto: String;
	valorProduto : DoubleRange;
	estoqueProduto: Number;
	data_cadastroProduto: String;
	cidadeProduto : Cidade = new Cidade();
}
