package ru.geekbrains.mvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.geekbrains.mvc.domain.Category;

import java.util.Collections;
import java.util.List;

public class CategoryDB {
    private final SessionFactory sessionFactory;

    public CategoryDB() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Category> findAll() {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<Category> all = session.createQuery("SELECT p FROM Category p", Category.class).getResultList();
            tx.commit();
            return all;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
