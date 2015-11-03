package webservice;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.google.gson.Gson;
import dao.ItemPedidoDAO;
import entity.ItemPedido;

@Path("/itempedido")
@Produces("application/json")
public class ItemPedidoWebService {

	@EJB
	private ItemPedidoDAO itempedidoDAO;

	@Path("/list")
	@GET 	
	public String getAllItemPedidos() throws Exception{
		List<ItemPedido> itempedido = itempedidoDAO.getItemPedido();
		System.out.println(itempedido);
		Gson gson = new Gson();
		return gson.toJson(itempedido);
	}

	@Path("/create")
	@POST
	@Consumes("application/json")
	public void setItemPedido(String json) throws Exception {
		Gson gson = new Gson();
		ItemPedido b =  gson.fromJson(json, ItemPedido.class);
		itempedidoDAO.addItem(b);
	}
	
	/*@Path("/createform")
	@GET
	public void createItemPedido(@QueryParam("id") String id, 
			@QueryParam("nome") String nome,
			@QueryParam("descricao") String descricao,
			@QueryParam("tipo") String tipo,
			@QueryParam("preco") Float preco,
			@QueryParam("quantidade") int quantidade,
			@QueryParam("data_exclusao") Date data_exclusao)
			throws Exception {
		 	ItemPedido b =  new ItemPedido(nome, descricao, tipo, preco, quantidade, data_exclusao); 
            produtoDAO.addItemPedido(b);
	}
	
	@Path("/deleta")
	@POST
	@Consumes("application/json")
	public void deleteItemPedido(long id) throws Exception {
		ItemPedido produto = produtoDAO.getItemPedidoById(id);
		produto.setdata_exclusao(new Date());
		System.out.println(produto);
		produtoDAO.updateDate(produto);
	}	*/
}
	
	

