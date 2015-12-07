package virtualqueue.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

@Entity
@NamedQuery(name="ItemPedido.findAll", query="SELECT u FROM ItemPedido u")
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @TableGenerator(name="ITEM_GENERATOR", table="ID_TABLE", pkColumnName="ID_TABLE_NAME", pkColumnValue="ITEM_ID", valueColumnName="ID_TABLE_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator="ITEM_GENERATOR")
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	private Product produto;
	private long quantidade;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduto() {
		return produto;
	}
	
	public void setProduto(Product produto) {
		this.produto = produto;
	}
	
	public long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	
	public ItemPedido(Product produto, long quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public ItemPedido() {
		super();
	}
}
