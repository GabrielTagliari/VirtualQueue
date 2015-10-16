package webservice;

import java.lang.reflect.Type;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dao.PedidoDAO;
import entity.Pedido;
import entity.Produto;

@Path("/pedido")
@Produces("application/json")
public class PedidoWebService {

	@EJB
	private PedidoDAO pedidoDAO;

	@Path("/list")
	@GET 	
	public String getAllPedidos() throws Exception{
		List<Pedido> produtos = pedidoDAO.getPedido();
		Gson gson = new Gson();
		return gson.toJson(produtos);
	}
	
	@Path("/pedidocreate")
	@POST
	@Consumes("application/json")
	public void setPedido(String json) throws Exception {
		Gson gson = new Gson();
		Pedido pedido =  gson.fromJson(json, Pedido.class);
		pedidoDAO.addPedido(pedido);	
	}
	
	/*@Path("/createform")
	@GET
	public void createPedido(@QueryParam("id_pedido") int id_pedido, 
			@QueryParam("Produtos") List<Produto> Produtos,
			@QueryParam("valor") float valor,
			@QueryParam("senha") int senha)
					throws Exception {
		Pedido b =  new Pedido(id_pedido, valor, senha, Produtos); 
		pedidoDAO.addPedido(b);
	}*/
}



