package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name="USER_GENERATOR", table="ID_TABLE", pkColumnName="ID_TABLE_NAME", pkColumnValue="USER_ID", valueColumnName="ID_TABLE_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator="USER_GENERATOR")
	private Long id;
	private String nome;
	private String email;
	private String password;
	private Date data_exclusao;
	private String privilegio; 
	
	public String getPrivilegio() {
		return privilegio;
	}
	
	public void setPrivilegio(String privilegio) {
		this.privilegio = privilegio;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getData_exclusao() {
		return data_exclusao;
	}
	
	public void setData_exclusao(Date data_exclusao) {
		this.data_exclusao = data_exclusao;
	}
	
	public User() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(String nome, String email, String password, Date data_exclusao, String privilegio) {
		super();
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.data_exclusao = data_exclusao;
		this.privilegio = privilegio;
	}
	
}
