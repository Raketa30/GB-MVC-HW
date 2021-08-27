package ru.geekbrains.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.mvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findProductById(Long id);

    List<Product> findProductsByCategoryId(Long id);

    List<Product> findProductsByTitleContainingAndCostBetweenAndCategoryIdIn(String title, Integer min, Integer max, List<Long> ids);

    List<Product> findProductsByTitleContainingAndCostBetween(String title, Integer min, Integer max);
}
