package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

@Entity
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p WHERE p.data_exclusao IS NULL")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name="PRODUTO_GENERATOR", table="ID_TABLE", pkColumnName="ID_TABLE_NAME", pkColumnValue="PRODUTO_ID", valueColumnName="ID_TABLE_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator="PRODUTO_GENERATOR")
	private long id;
	private String nome;
	private String descricao;
	private String tipo;
	private float preco;
	private int quantidade;
	private Date data_exclusao;
	
	public Date getdata_exclusao() {
		return data_exclusao;
	}

	public void setdata_exclusao(Date data_exclusao) {
		this.data_exclusao = data_exclusao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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

	public Produto(String nome, String descricao, String tipo, float preco, int quantidade, Date data_exclusao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
		this.preco = preco;
		this.quantidade = quantidade;
		this.data_exclusao = data_exclusao;
	}

	public Produto() {
		super();
	}		
}
