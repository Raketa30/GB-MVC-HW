package ru.geekbrains.mvc.service;

import ru.geekbrains.mvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findProductById(Long id);

    void addProduct(Product product);

    void deleteProduct(Long id);

    void updateProduct(Product product);

    List<Product> findProductByCategoryId(Long aLong);
}
