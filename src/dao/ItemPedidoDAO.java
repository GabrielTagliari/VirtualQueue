package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import entity.ItemPedido;

@Stateless
public class ItemPedidoDAO {
	
	 @PersistenceContext(unitName = "livraria")
	    private EntityManager entityManager;

	    public void addItem(ItemPedido item) throws Exception {
	    	try {
	    		entityManager.merge(item);
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }

	    public void deleteItemPedido(ItemPedido item) throws Exception {
	        entityManager.remove(item);
	    }

	    public List<ItemPedido> getItemPedido() throws Exception {
            CriteriaQuery<ItemPedido> cq = entityManager.getCriteriaBuilder().createQuery(ItemPedido.class);
            cq.select(cq.from(ItemPedido.class));
            return entityManager.createQuery(cq).getResultList();
	    }
	    
}
