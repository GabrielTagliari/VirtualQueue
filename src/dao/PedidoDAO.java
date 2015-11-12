package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Pedido;

@Stateless
public class PedidoDAO {
	
	 @PersistenceContext(unitName = "livraria")
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
	    	String query = "SELECT i FROM Pedido p INNER JOIN p.id i";
	    	TypedQuery<Pedido> typedQuery = entityManager.createQuery(query , Pedido.class);
	    	return typedQuery.getResultList();
	    	
	    	/*Query query = entityManager.createNamedQuery("Pedido.findAll");
	    	return query.getResultList();*/
			
	    }
	    
}
