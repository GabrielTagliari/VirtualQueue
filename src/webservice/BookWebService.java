package webservice;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;

import dao.BookDAO;
import entity.Book;



@Path("/book")
@Produces("application/json")
public class BookWebService {

	@EJB
	private BookDAO bookDAO;

	@Path("/list")
	@GET 	
	public String getAllBooks() throws Exception{
		List<Book> books = bookDAO.getBooks();
		Gson gson = new Gson();
		return gson.toJson(books);
	}

	@Path("/list/{id}")
	@GET
	@Produces("application/json")
	public String getBook(@PathParam("id") String id){
		Book b =  new Book("deitel",  "1", 10,  "Alta Vista",  "Java how to program");
		Gson gson = new Gson();
		return gson.toJson(b);
	}
	
	@Path("/create")
	@POST
	@Consumes("application/json")
	public void setBook(String json) throws Exception {
		Gson gson = new Gson();
		Book b =  gson.fromJson(json, Book.class);
       bookDAO.addBook(b);
	}
	
	@Path("/createform")
	@GET
	public void createBook(@QueryParam("id") String id,
			@QueryParam("author") String author, 
			@QueryParam("price") Float price,
			@QueryParam("publisher") String publisher,
			@QueryParam("title") String title)
			throws Exception {
		    Book b =  new Book(author, id, price, publisher, title); 
            bookDAO.addBook(b);
	}
	
	
	}
	
	

