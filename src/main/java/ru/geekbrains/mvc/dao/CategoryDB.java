package ru.geekbrains.mvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.geekbrains.mvc.domain.Category;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public Optional<Category> findCategoryById(Long id) {
        Optional<Category> categoryOptional;
        try (Session session = sessionFactory.openSession()) {
            session.get(Category.class, id);

            Query query = session.createQuery("from Category as p where p.id = :id");
            query.setParameter("id", id);
            categoryOptional = Optional.of((Category) query.getSingleResult());
            return categoryOptional;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Category> findCategoryByTitle(String title) {
        Optional<Category> categoryOptional;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Category as c where c.title = :title", Category.class);
            query.setParameter("title", title);
            categoryOptional = Optional.of((Category) query.getSingleResult());
            return categoryOptional;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean save(Category category) {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(category);
            tx.commit();
            return true;
        }
    }
}
