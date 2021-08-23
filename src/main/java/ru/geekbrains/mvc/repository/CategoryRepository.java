package ru.geekbrains.mvc.repository;

import ru.geekbrains.mvc.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> findAll();

    Optional<Category> findCategoryById(Long id);

    Category addCategory(Category category);

    Category deleteCategory(Category category);

    Optional<Category> findCategoryByTitle(String category);

    boolean save(Category category);
}
