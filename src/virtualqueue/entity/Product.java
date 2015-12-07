package virtualqueue.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Product p WHERE p.data_exclusao IS NULL")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private String descricao;
	private String tipo;
	private float preco;
	private Date data_exclusao;
	
	public Date getdata_exclusao() {
		return data_exclusao;
	}

	public void setdata_exclusao(Date data_exclusao) {
		this.data_exclusao = data_exclusao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Product(String nome, String descricao, String tipo, float preco, Date data_exclusao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
		this.preco = preco;
		this.data_exclusao = data_exclusao;
	}

	public Product() {
		super();
	}		
}
