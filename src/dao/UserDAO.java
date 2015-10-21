package dao;

import java.util.List;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

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
        CriteriaQuery<User> cq = entityManager.getCriteriaBuilder().createQuery(User.class);
        cq.select(cq.from(User.class));
        return entityManager.createQuery(cq).getResultList();
    }
    public User getUserById(long id) throws Exception{
    	return entityManager.find(User.class, id);
    }

}
