package virtualqueue.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import virtualqueue.entity.Pedido;

@Stateless
public class PedidoDAO {
	
	 @PersistenceContext(unitName = "virtualqueue")
	    private EntityManager entityManager;

	    public void addPedido(Pedido pedido) throws Exception {
	    	try {
	    		entityManager.merge(pedido);
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }

	    public void deletePedido(Pedido pedido) throws Exception {
	        entityManager.remove(pedido);
	    }
	    
	    public List<Pedido> getPedido() throws Exception {
	    	 Query query = entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class);
	    	 return query.getResultList();

	    }
	    
}
