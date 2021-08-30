package ru.geekbrains.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.mvc.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();

    Optional<Category> findCategoryById(Long id);

    Optional<Category> findCategoryByTitle(String category);
}
