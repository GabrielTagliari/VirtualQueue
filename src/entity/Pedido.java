package entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	private int id_pedido;
	private float valor;
	private int senha;
	
	@OneToMany(mappedBy = "pedido")
	private List<PedidoProduto> produtos;
	
	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
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

	public List<PedidoProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<PedidoProduto> produtos) {
		this.produtos = produtos;
	}

	public Pedido(int id_pedido, float valor, int senha, List<PedidoProduto> produtos) {
		super();
		this.id_pedido = id_pedido;
		this.valor = valor;
		this.senha = senha;
		this.produtos = produtos;
	}

	public Pedido() {
		super();
	}	
}
