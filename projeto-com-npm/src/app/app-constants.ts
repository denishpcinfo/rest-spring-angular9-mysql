export class AppConstants {

	public static get baseServidor(): string { return "http://localhost:8080/" }

	public static get baseProduto(): string {return this.baseServidor + "springrestapi/produto/"}

	public static get getBaseUrlPath() : string {return this.baseServidor + "springrestapi/"}


}
