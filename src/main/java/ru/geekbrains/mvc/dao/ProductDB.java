package ru.geekbrains.mvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.geekbrains.mvc.domain.Product;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductDB {
    private final SessionFactory sessionFactory;

    public ProductDB() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Product> findAll() {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<Product> all = session.createQuery("SELECT p FROM Product p").getResultList();
            tx.commit();
            return all;
        } catch (Exception e) {

            return Collections.emptyList();
        }
    }

    public Optional<Product> findById(Long id) {
        Optional<Product> optionalProduct;
        try (Session session = sessionFactory.openSession()) {
            session.get(Product.class, 1L);

            Query query = session.createQuery("from Product as p where p.id = :id");
            query.setParameter("id", id);
            optionalProduct = Optional.of((Product) query.getSingleResult());
            return optionalProduct;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean save(Product product) {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            session.get(Product.class, 2L);
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
            return true;
        }
    }

    public boolean delete(Product product) {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            session.get(Product.class, 3L);
            tx = session.beginTransaction();
            session.delete(product);
            tx.commit();
            return true;
        }
    }

    public boolean update(Product product) {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            session.get(Product.class, 4L);
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
            return true;
        }
    }
}
