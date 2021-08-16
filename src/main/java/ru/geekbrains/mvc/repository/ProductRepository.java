package ru.geekbrains.mvc.repository;

import ru.geekbrains.mvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findProductById(int id);

    boolean save(Product product);

    boolean deleteProduct(int id);
}
