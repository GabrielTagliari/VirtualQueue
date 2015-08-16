package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Book database table.
 * 
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	private String author;

	@Id
	private String id;

	private float price;

	private String publisher;

	private String title;

	public Book() {
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Book(String author, String id, float price, String publisher, String title) {
		super();
		this.author = author;
		this.id = id;
		this.price = price;
		this.publisher = publisher;
		this.title = title;
	}

	
}