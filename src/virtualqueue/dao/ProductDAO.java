package virtualqueue.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import virtualqueue.entity.Product;

@Stateless
public class ProductDAO {

	 @PersistenceContext(unitName = "virtualqueue")
	    private EntityManager entityManager;

	    public void addProduto(Product produto) throws Exception {
	    	entityManager.merge(produto);
	    }

	    public void updateDate(Product produto) throws Exception {
	    	entityManager.merge(produto);
	    }
	    
	    public void updateProduto(Product produto) throws Exception {
	    	entityManager.merge(produto);
	    }

	    public List<Product> getProdutos() throws Exception {
	    	Query query = entityManager.createNamedQuery("Produto.findAll");
	    	return query.getResultList();
	    }
	    
	    public Product getProdutoById(long id) throws Exception{
	    	return entityManager.find(Product.class, id);
	    }
}
