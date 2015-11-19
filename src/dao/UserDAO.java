package dao;

import java.util.List;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import entity.Produto;
import entity.User;

@Stateless
public class UserDAO {
	
	@PersistenceContext(unitName = "livraria")
    private EntityManager entityManager;

    public void addUser(User user) throws Exception {
        entityManager.persist(user);
    }

    public void updateDate(User user) throws Exception {
        entityManager.merge(user);
    }

    public List<User> getUsers() throws Exception {
    	Query query = entityManager.createNamedQuery("User.findAll");
    	return query.getResultList();
    }
    
    public User getUserUpdate(String email) throws Exception{
    	Query query = entityManager.createNamedQuery("User.findByEmail").setParameter("email", email);
    	return (User) query.getSingleResult();
    }
    
    public List<User> getUserByEmail(String email) throws Exception{
    	Query query = entityManager.createNamedQuery("User.findByEmail").setParameter("email", email);
    	return query.getResultList();
    }
}
