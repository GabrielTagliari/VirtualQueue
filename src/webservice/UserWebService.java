package webservice;

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

import dao.UserDAO;
import entity.User;

@Path("/user")
@Produces("application/json")
public class UserWebService {

		@EJB
		private UserDAO userDAO;

		@Path("/list")
		@GET 	
		public String getAllBooks() throws Exception{
			List<User> books = userDAO.getUsers();
			Gson gson = new Gson();
			return gson.toJson(books);
		}

		@Path("/list/{email}")
		@GET
		@Produces("application/json")
		public String getUser(@PathParam("email") String email){
			User b =  new User("plucas@lala.com", "lala", "Rua 1234");
			Gson gson = new Gson();
			return gson.toJson(b);
		}
		
		@Path("/create")
		@POST
		@Consumes("application/json")
		public void setUser(String json) throws Exception {
			Gson gson = new Gson();
			User b =  gson.fromJson(json, User.class);
	        userDAO.addUser(b);
		}
		
		@Path("/createform")
		@GET
		public void createBook(@QueryParam("email") String email,
				@QueryParam("password") String password, 
				@QueryParam("address") String address)
				throws Exception {
			    User b =  new User(email, password, address); 
	            userDAO.addUser(b);
		     }
		}

	