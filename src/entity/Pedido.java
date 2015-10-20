package entity;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;


@Entity
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PEDIDO_ID")
    @TableGenerator(name="TABLE_GENERATOR", table="ID_TABLE", pkColumnName="ID_TABLE_NAME", pkColumnValue="PEDIDO_ID", valueColumnName="ID_TABLE_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator="TABLE_GENERATOR")
	private long id;
	private float valor;
	
	private long senha;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable
	 (
	      name="PEDIDO_PRODUTO",
	      joinColumns={ @JoinColumn(name="PEDIDO_ID", referencedColumnName="PEDIDO_ID") }
	  )
	List<Produto> produtos = new LinkedList<Produto>();
	
	
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

	public void setProdutos(List<Produto> lista) {
		this.produtos = lista;
		
	}	
	public void addProduto(Produto b) {
    	this.produtos.add(b);
    }

	public List<Produto> getProdutos() {
		return produtos;
	}
	
}
