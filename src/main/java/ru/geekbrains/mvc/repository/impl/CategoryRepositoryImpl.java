package ru.geekbrains.mvc.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mvc.dao.CategoryDB;
import ru.geekbrains.mvc.domain.Category;
import ru.geekbrains.mvc.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryDB databaseConnection;

    @Autowired
    public CategoryRepositoryImpl(CategoryDB databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public List<Category> findAll() {
        return databaseConnection.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return databaseConnection.findCategoryById(id);
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category deleteCategory(Category category) {
        return null;
    }

    @Override
    public Optional<Category> findCategoryByTitle(String category) {
        return databaseConnection.findCategoryByTitle(category);
    }

    @Override
    public boolean save(Category category) {
        return databaseConnection.save(category);
    }
}
