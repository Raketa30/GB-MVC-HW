package ru.geekbrains.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.mvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findProductById(Long id);

    List<Product> findProductsByCategoryId(Long id);
}
