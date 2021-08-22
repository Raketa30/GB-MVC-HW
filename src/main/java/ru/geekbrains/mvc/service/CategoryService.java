package ru.geekbrains.mvc.service;

import ru.geekbrains.mvc.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findCategoryById(Long id);

    Category addCategory(Category category);

    Category deleteCategory(Category category);
}
