package virtualqueue.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;


@Entity
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PEDIDO_ID")
    @TableGenerator(name="PEDIDO_GENERATOR", table="ID_TABLE", pkColumnName="ID_TABLE_NAME", pkColumnValue="PEDIDO_ID", valueColumnName="ID_TABLE_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator="PEDIDO_GENERATOR")
	private long id;
	private float valor;
	
	private long senha;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable
	 (
	      name="PEDIDO_ITEMPEDIDO",
	      joinColumns={ @JoinColumn(name="PEDIDO_ID", referencedColumnName="PEDIDO_ID") }
	  )
	List<ItemPedido> itempedido = new LinkedList<ItemPedido>();
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public long getSenha() {
		return senha;
	}

	public void setSenha(long senha) {
		this.senha = senha;
	}

	public Pedido() {
		super();
	}

	public List<ItemPedido> getItempedido() {
		return itempedido;
	}

	public void setItempedido(List<ItemPedido> itempedido) {
		this.itempedido = itempedido;
	}

	public Pedido(float valor, long senha, List<ItemPedido> itempedido) {
		super();
		this.valor = valor;
		this.senha = senha;
		this.itempedido = itempedido;
	}
}
