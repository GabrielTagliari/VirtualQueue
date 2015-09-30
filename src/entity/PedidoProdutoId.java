package entity;

import java.io.Serializable;
 
public class PedidoProdutoId implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private int pedido;
    private int produto;
 
    public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}

	public int getProduto() {
		return produto;
	}

	public void setProduto(int produto) {
		this.produto = produto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public boolean equals(Object obj) {
        if(obj instanceof PedidoProdutoId){
        	PedidoProdutoId PedidoProdutoId = (PedidoProdutoId) obj;
            return PedidoProdutoId.produto == produto && PedidoProdutoId.pedido == pedido;
        }
 
        return false;
    }
}