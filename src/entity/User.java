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
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String nome;
	private String email;
	private String password;
	private Date data_exclusao;
	
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
	public User(String email, String nome, String password, Date data_exclusao) {
		super();
		this.email = email;
		this.nome = nome;
		this.password = password;
		this.data_exclusao = data_exclusao;
	}
}
