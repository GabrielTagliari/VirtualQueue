package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;



import entity.Book;

@Stateless
public class BookDAO {

	 @PersistenceContext(unitName = "livraria")
	    private EntityManager entityManager;

	    public void addBook(Book book) throws Exception {
	        entityManager.persist(book);
	    }

	    public void deleteMovie(Book book) throws Exception {
	        entityManager.remove(book);
	    }

	    public List<Book> getBooks() throws Exception {

            CriteriaQuery<Book> cq = entityManager.getCriteriaBuilder().createQuery(Book.class);
            cq.select(cq.from(Book.class));
            return entityManager.createQuery(cq).getResultList();
	    }
	    
}
