package virtualqueue.webservice;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.google.gson.Gson;

import virtualqueue.dao.ProductDAO;
import virtualqueue.entity.Product;

@Path("/produto")
@Produces("application/json")
public class ProductWebService {

	@EJB
	private ProductDAO productDAO;

	@Path("/list")
	@GET 	
	public String getAllProdutos() throws Exception{
		List<Product> produtos = productDAO.getProdutos();
		Gson gson = new Gson();
		return gson.toJson(produtos);
	}

	@Path("/produtocreate")
	@POST
	@Consumes("application/json")
	public void setProduto(String json) throws Exception {
		Gson gson = new Gson();
		Product b =  gson.fromJson(json, Product.class);
		productDAO.addProduto(b);
	}
	
	@Path("/createform")
	@GET
	public void createProduto(@QueryParam("id") String id, 
			@QueryParam("nome") String nome,
			@QueryParam("descricao") String descricao,
			@QueryParam("tipo") String tipo,
			@QueryParam("preco") Float preco,
			@QueryParam("data_exclusao") Date data_exclusao)
			throws Exception {
		 	Product b =  new Product(nome, descricao, tipo, preco, data_exclusao); 
            productDAO.addProduto(b);
	}
	
	@Path("/deleta")
	@POST
	@Consumes("application/json")
	public void deleteProduto(long id) throws Exception {
		Product produto = productDAO.getProdutoById(id);
		produto.setdata_exclusao(new Date());
		productDAO.updateDate(produto);
	}	
	
	@Path("/update")
	@POST
	@Consumes("application/json")
	public void updateProduto(String json) throws Exception {
		Gson gson = new Gson();
		Product b =  gson.fromJson(json, Product.class);
		productDAO.updateProduto(b);
	}
}
	
	

