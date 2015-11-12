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
       /* CriteriaQuery<User> cq = entityManager.getCriteriaBuilder().createQuery(User.class);
        Root<User> User = cq.from(User.class);
        cq.where(User.get("data_exclusao").isNull());
        return entityManager.createQuery(cq).getResultList();*/
    }
    
    public User getUserUpdate(String email) throws Exception{
    	Query query = entityManager.createNamedQuery("User.findByEmail").setParameter("email", email);
    	return (User) query.getSingleResult();
    	/*CriteriaQuery<User> cq = entityManager.getCriteriaBuilder().createQuery(User.class);
    	Root<User> user = cq.from(User.class);
    	cq.where(user.get("email").in(email));
        return entityManager.createQuery(cq).getSingleResult();*/
    }
    
    public List<User> getUserByEmail(String email) throws Exception{
    	CriteriaQuery<User> cq = entityManager.getCriteriaBuilder().createQuery(User.class);
    	Root<User> user = cq.from(User.class);
    	cq.where(user.get("email").in(email));
    	return entityManager.createQuery(cq).getResultList();
    }
}
