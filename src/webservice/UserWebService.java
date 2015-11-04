package webservice;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
		public String getAllUsers() throws Exception{
			List<User> users = userDAO.getUsers();
			Gson gson = new Gson();
			return gson.toJson(users);
		}

		@Path("/list/{email}")
		@GET
		@Produces("application/json")
		public String getUser(@PathParam("email") String email){
			User b =  new User("","plucas@lala.com", "lala", null, "");
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
		@POST
		public boolean createUser(String json) throws Exception {
				Gson gson = new Gson();
				User b =  gson.fromJson(json, User.class);
				b = hash(b);
			 	List<User> user = userDAO.getUserByEmail(b.getEmail());
				if (user == null || user.isEmpty() || user.get(0).getData_exclusao() != null) {
					userDAO.addUser(b);
					return true;
				}else {
					return false;
				}
		}
		
		@Path("/deleta")
		@POST
		@Consumes("application/json")
		public void deleteUser(String email) throws Exception {
			User user = userDAO.getUserUpdate(email);
			user.setData_exclusao(new Date());
			user.setEmail(user.getEmail()+"*");
			userDAO.updateDate(user);
		}
		
		@Path("/login")
		@POST
		@Consumes("application/json")
		public String loginUser(String json) throws Exception {
			Gson gson = new Gson();
			User b =  gson.fromJson(json, User.class);
			b = hash(b);
			List<User> user = userDAO.getUserByEmail(b.getEmail());
				if (user == null || user.isEmpty()) {
					return "false";
				}else if (user.get(0).getData_exclusao() == null && user.get(0).getEmail().equals(b.getEmail()) && user.get(0).getPassword().equals(b.getPassword())){ 
					return user.get(0).getPrivilegio();
				} else {
					return "false";
				}
		}
		
		public User hash(User b) throws Exception {
			MessageDigest m = MessageDigest.getInstance("MD5");
		    m.update(b.getPassword().getBytes(),0,b.getPassword().length());
		    b.setPassword(new BigInteger(1,m.digest()).toString(16));
		    return b;
		}
}


	