package webservice;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.google.gson.Gson;
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
		List<Pedido> books = pedidoDAO.getPedido();
		Gson gson = new Gson();
		return gson.toJson(books);
	}
	
	@Path("/pedidocreate")
	@POST
	@Consumes("application/json")
	public void setPedido(String json) throws Exception {
		System.out.println("Processando pedido...");
		System.out.println(json);
		/*Gson gson = new Gson();
		Pedido p = new Pedido();
		PedidoProduto pedprod = new PedidoProduto();
		Produto prod = new Produto(1, "Lanche", "Lanche", "Alimento", 10,	null);
		pedprod.setProduto(prod);
		List<PedidoProduto> lista = new LinkedList<PedidoProduto>();
		p.setProdutos(lista);*/
		/*
		Type listType = new TypeToken<List<Produto>>() {}.getType();
		List<Produto> lista =  gson.fromJson(json, listType);
		System.out.println(lista.size() + lista.get(0).getNome());
		Pedido p = new Pedido();
		p.setProdutos(lista);
		*/
		/*pedidoDAO.addPedido(p);*/
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



