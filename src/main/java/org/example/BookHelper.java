package org.example;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.Book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Queue;

public class BookHelper {

    private final SessionFactory sessionFactory;

    public BookHelper(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBooks(){
        Session session = sessionFactory.openSession();

//       session.get(Book.class, 1L);


//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Book.class);
//        Root<Book> root = criteriaQuery.from(Book.class);
//        criteriaQuery.select(root);
//
//        Query query = session.createQuery(criteriaQuery);
//        List<Book> books = query.getResultList();
//        session.close();

        List<Book> books = session.createQuery("select book_1 from Book book_1 where book_1.name like :name",Book.class)
                .setParameter("name","%2%").getResultList();
        session.close();
        return books;

    }

    public boolean addBook(Book book){
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(book);

            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            return false;
        }

    }

}
