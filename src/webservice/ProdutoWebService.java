package webservice;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.google.gson.Gson;
import dao.ProdutoDAO;
import entity.Produto;

@Path("/produto")
@Produces("application/json")
public class ProdutoWebService {

	@EJB
	private ProdutoDAO produtoDAO;

	@Path("/list")
	@GET 	
	public String getAllProdutos() throws Exception{
		List<Produto> produtos = produtoDAO.getProdutos();
		Gson gson = new Gson();
		return gson.toJson(produtos);
	}

	@Path("/produtocreate")
	@POST
	@Consumes("application/json")
	public void setProduto(String json) throws Exception {
		Gson gson = new Gson();
		Produto b =  gson.fromJson(json, Produto.class);
		produtoDAO.addProduto(b);
	}
	
	@Path("/createform")
	@GET
	public void createProduto(@QueryParam("id") String id, 
			@QueryParam("nome") String nome,
			@QueryParam("descricao") String descricao,
			@QueryParam("tipo") String tipo,
			@QueryParam("preco") Float preco,
			@QueryParam("quantidade") int quantidade)
			throws Exception {
		 	Produto b =  new Produto(nome, descricao, tipo, preco, quantidade); 
            produtoDAO.addProduto(b);
	}
}
	
	

