package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
	private int cod_produto;
	private String nome;
	private String descricao;
	private String tipo;
	private float preco;
	
	public int getCod_produto() {
		return cod_produto;
	}
	public void setCod_produto(int cod_produto) {
		this.cod_produto = cod_produto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public Produto(int cod_produto, String nome, String descricao, String tipo, float preco) {
		super();
		this.cod_produto = cod_produto;
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
		this.preco = preco;
	}
	
	
	
	public Produto() {
		super();
	}
	
	@Override
	public String toString() {
		return "Produto [cod_produto=" + cod_produto + ", nome=" + nome + ", descricao=" + descricao + ", tipo=" + tipo
				+ ", preco=" + preco + "]";
	}
		
}
