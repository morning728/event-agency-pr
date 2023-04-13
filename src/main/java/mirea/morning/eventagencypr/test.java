package mirea.morning.eventagencypr;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


https://stackoverflow.com/questions/72761919/class-springhibernatejpapersistenceprovider-does-not-implement-the-requested-int
@Service
public class test {
    @Autowired
    private EntityManagerFactory mObjEntityManagerFactory;
    public List<Book> getAllBooks()
    {
        EntityManager objEntityManager = getEntityManagerFactory().createEntityManager();
        List<Book> lstBooks = objEntityManager.createQuery("from Books", Book.class).getResultList();

        return(lstBooks);
    }
}
