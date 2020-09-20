package api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Produto implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String nomeProduto;
	
	@Column
	private BigDecimal valorProduto;
	
	@Column
	private Integer estoqueProduto;
	
	@ManyToOne
	private Cidade cidadeProduto;
	
	@Column
	private String data_cadastroProduto;

	
	@PrePersist
	public void prePersist() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		data_cadastroProduto = today.format(formatado);
		setData_cadastroProduto(data_cadastroProduto);	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}


	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}


	public BigDecimal getValorProduto() {
		return valorProduto;
	}


	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
	}

	public Integer getEstoqueProduto() {
		return estoqueProduto;
	}


	public void setEstoqueProduto(Integer estoqueProduto) {
		this.estoqueProduto = estoqueProduto;
	}


	public Cidade getCidadeProduto() {
		return cidadeProduto;
	}


	public void setCidadeProduto(Cidade cidadeProduto) {
		this.cidadeProduto = cidadeProduto;
	}


	public String getData_cadastroProduto() {
		return data_cadastroProduto;
	}


	public void setData_cadastroProduto(String data_cadastroProduto) {
		this.data_cadastroProduto = data_cadastroProduto;
	}

	
}
