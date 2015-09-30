package entity;

import javax.persistence.*;

@Entity
@IdClass(PedidoProdutoId.class)
public class PedidoProduto {
 
    @Id
    @ManyToOne
    @JoinColumn(name="id_pedido")
    private Pedido pedido;
 
    @Id
    @ManyToOne
    @JoinColumn(name="cod_produto")
    private Produto produto;
    
    private int quantidade;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}