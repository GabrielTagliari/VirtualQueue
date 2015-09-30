package entity;

import java.io.Serializable;
 
public class PedidoProdutoId implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private int pedido;
    private int produto;
 
    public int getPerson() {
        return pedido;
    }
 
    public void setPerson(int person) {
        this.pedido = person;
    }
 
    public int getDog() {
        return produto;
    }
 
    public void setDog(int dog) {
        this.produto = dog;
    }
 
    @Override
    public int hashCode() {
        return pedido + produto;
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