package mirea.morning.eventagencypr;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//https://stackoverflow.com/questions/72761919/class-springhibernatejpapersistenceprovider-does-not-implement-the-requested-int
@Service
public class test {
    @Autowired
    private EntityManagerFactory mObjEntityManagerFactory;
    @Autowired
    private static SessionFactory factory;

    @Autowired
    EntityManager entityManager;
    public List getAllBooks()
    {
        //EntityManager objEntityManager = getEntityManagerFactory().createEntityManager();
        List lstBooks = entityManager.createQuery("select book from Book book where book.msBookName = ?1")
                .setParameter(1, "testName")
                .getResultList();

        return(lstBooks);
    }
    public void listCustomers() {
//        Session session = factory.openSession();
//        List<Book> customers = session.createQuery("FROM Book", Book.class).list();
//        System.out.println(customers.toString());
//        session.close();
        Transaction transaction = null;
        try (
                Session session = HibernateUtil.getSessionFactory().openSession()
        ) {
            Book author = new Book(1, "name", "author");
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return mObjEntityManagerFactory;
    }
}
