package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Pedido {
	
	@Id
	private int id_pedido;
	@ManyToMany
	private List<Produto> Produtos;
	private float valor;
	private int senha;
	
	public int getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}
	public List<Produto> getProdutos() {
		return Produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		Produtos = produtos;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	public Pedido(int id_pedido, List<Produto> produtos, float valor, int senha) {
		super();
		this.id_pedido = id_pedido;
		Produtos = produtos;
		this.valor = valor;
		this.senha = senha;
	}
	
	public Pedido() {
		super();
	}	
}
