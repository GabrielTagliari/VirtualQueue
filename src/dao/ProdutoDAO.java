package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import entity.Produto;

@Stateless
public class ProdutoDAO {

	 @PersistenceContext(unitName = "livraria")
	    private EntityManager entityManager;

	    public void addProduto(Produto produto) throws Exception {
	    	entityManager.merge(produto);
	    }

	    public void deleteProduto(Produto produto) throws Exception {
	    	System.out.println(produto.getNome());
	        entityManager.remove(produto);
	    }

	    public List<Produto> getProdutos() throws Exception {
            CriteriaQuery<Produto> cq = entityManager.getCriteriaBuilder().createQuery(Produto.class);
            cq.select(cq.from(Produto.class));
            return entityManager.createQuery(cq).getResultList();
	    }
	    
}
