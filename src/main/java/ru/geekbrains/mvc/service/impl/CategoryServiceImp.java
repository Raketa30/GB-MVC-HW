package ru.geekbrains.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.mvc.domain.Category;
import ru.geekbrains.mvc.repository.CategoryRepository;
import ru.geekbrains.mvc.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImp(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return repository.findCategoryById(id);
    }

    @Override
    public boolean addCategory(String title) {
        Optional<Category> optionalCategory = repository.findCategoryByTitle(title);
        if (optionalCategory.isEmpty()) {
            repository.save(new Category(title));
            return true;
        }
        return false;
    }

    @Override
    public Category deleteCategory(Category category) {
        return null;
    }
}
