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
 
    // get and set
}