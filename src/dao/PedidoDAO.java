package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import entity.Pedido;

@Stateless
public class PedidoDAO {

	 @PersistenceContext(unitName = "livraria")
	    private EntityManager entityManager;

	    public void addPedido(Pedido books) throws Exception {
	    	entityManager.persist(books);
	    }

	    public void deletePedido(Pedido books) throws Exception {
	        entityManager.remove(books);
	    }

	    public List<Pedido> getProdutos() throws Exception {

            CriteriaQuery<Pedido> cq = entityManager.getCriteriaBuilder().createQuery(Pedido.class);
            cq.select(cq.from(Pedido.class));
            return entityManager.createQuery(cq).getResultList();
	    }
	    
}
