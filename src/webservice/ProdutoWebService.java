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
	public String getAllBooks() throws Exception{
		List<Produto> books = produtoDAO.getProdutos();
		Gson gson = new Gson();
		return gson.toJson(books);
	}

	@Path("/produtocreate")
	@POST
	@Consumes("application/json")
	public void setBook(String json) throws Exception {
		Gson gson = new Gson();
		Produto b =  gson.fromJson(json, Produto.class);
		produtoDAO.addProduto(b);
	}
	
	@Path("/createform")
	@GET
	public void createProduto(@QueryParam("cod_produto") int cod_produto, 
			@QueryParam("nome") String nome,
			@QueryParam("descricao") String descricao,
			@QueryParam("tipo") String tipo,
			@QueryParam("preco") float preco)
			throws Exception {
		 	Produto b =  new Produto(cod_produto, nome, descricao, tipo, preco); 
            produtoDAO.addProduto(b);
	}
}
	
	

