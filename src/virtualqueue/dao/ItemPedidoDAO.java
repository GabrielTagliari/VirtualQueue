package virtualqueue.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import virtualqueue.entity.ItemPedido;

@Stateless
public class ItemPedidoDAO {
	
	 @PersistenceContext(unitName = "virtualqueue")
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

	    @SuppressWarnings("unchecked")
		public List<ItemPedido> getItemPedido() throws Exception {
	    	Query query = entityManager.createNamedQuery("ItemPedido.findAll");
	    	return query.getResultList();
	    }
}
