package org.example;

import org.example.entity.Author;
import org.example.entity.Book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UniversalDao {

    private final SessionFactory sessionFactory;

    public UniversalDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBooks() {
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

        List<Book> books = session.createQuery("select book_1 from Book book_1 where book_1.name like :name", Book.class)
                .setParameter("name", "%2%").getResultList();
        session.close();
        return books;

    }

    public boolean addBook(Book book) {
        boolean result = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            session.persist(book);

            transaction.commit();
            session.close();
            result = true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();

        }
        return result;
    }

    public boolean addAuthor(Author author) {
        boolean result = false;

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(author);
            author.getBookList().forEach(session::merge);
            transaction.commit();
            session.close();
            result = true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
        }
        return result;
    }

    public Book getBookById(long id) throws Exception {
        Session session = sessionFactory.openSession();
        try {

            Book book = session.find(Book.class,id);
            session.close();
            return book;
        } catch (Exception e) {
            session.close();
            throw new Exception("Something go wrong");
        }

    }


}
