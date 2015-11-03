package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name="ITEM_ID")
    @TableGenerator(name="ITEM_GENERATOR", table="ID_TABLE", pkColumnName="ID_TABLE_NAME", pkColumnValue="ITEM_ID", valueColumnName="ID_TABLE_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator="ITEM_GENERATOR")
	private long id;
	
	@OneToOne
	private Produto produto;
	private long quantidade;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	
	public ItemPedido(Produto produto, long quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public ItemPedido() {
		super();
	}
}
