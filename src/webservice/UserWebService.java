package webservice;

import java.util.Date;
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
import entity.Produto;
import entity.User;

@Path("/user")
@Produces("application/json")
public class UserWebService {

		@EJB
		private UserDAO userDAO;

		@Path("/list")
		@GET 	
		public String getAllUsers() throws Exception{
			List<User> users = userDAO.getUsers();
			Gson gson = new Gson();
			return gson.toJson(users);
		}

		@Path("/list/{email}")
		@GET
		@Produces("application/json")
		public String getUser(@PathParam("email") String email){
			User b =  new User("","plucas@lala.com", "lala", null);
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
		public void createUser(@QueryParam("nome") String nome,
				@QueryParam("email") String email, 
				@QueryParam("password") String password,
				@QueryParam("data_exclusao") Date data_exclusao)
				throws Exception {
			 	User b =  new User(email, nome, password, data_exclusao);
	            userDAO.addUser(b);
		}
		
		@Path("/deleta")
		@POST
		@Consumes("application/json")
		public void deleteUser(String email) throws Exception {
			User user = userDAO.getUserUpdate(email);
			user.setData_exclusao(new Date());
			userDAO.updateDate(user);
		}
		
		@Path("/login")
		@POST
		@Consumes("application/json")
		public boolean loginUser(String json) throws Exception {
			Gson gson = new Gson();
			User b =  gson.fromJson(json, User.class);
			List<User> user = userDAO.getUserByEmail(b.getEmail());
					if (user.get(0).getData_exclusao() == null && user.get(0).getEmail().equals(b.getEmail()) && user.get(0).getPassword().equals(b.getPassword())){ 
						return true;
					} else {
						return false;
					}
				
			/*if (user == null || user.isEmpty()) {
				return false;
			} else {
				if (user.get(0).getEmail().equals(b.getEmail()) && user.get(0).getPassword().equals(b.getPassword())) {
					return true;
				}
			}
			return false;*/
		}
}


	