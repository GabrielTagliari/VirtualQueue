package webservice;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import com.google.gson.Gson;
import dao.PedidoDAO;
import entity.Pedido;

public class PedidoWebService {

	@EJB
	private PedidoDAO pedidoDAO;

	@Path("/pedidolist")
	@GET 	
	public String getAllBooks() throws Exception{
		List<Pedido> books = pedidoDAO.getProdutos();
		Gson gson = new Gson();
		return gson.toJson(books);
	}

	@Path("/pedidocreate")
	@POST
	@Consumes("application/json")
	public void setBook(String json) throws Exception {
		Gson gson = new Gson();
		Pedido b =  gson.fromJson(json, Pedido.class);
		pedidoDAO.addPedido(b);
	}

	@Path("/createform")
	@GET
	public void createProduto(@QueryParam("id_pedido") int id_pedido, 
			@QueryParam("Produtos") List Produtos,
			@QueryParam("valor") float valor,
			@QueryParam("senha") int senha)
					throws Exception {
		Pedido b =  new Pedido(id_pedido, Produtos, valor, senha); 
		pedidoDAO.addPedido(b);
	}
}



