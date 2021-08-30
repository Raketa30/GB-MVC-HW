package ru.geekbrains.mvc.service;

import ru.geekbrains.mvc.domain.Category;
import ru.geekbrains.mvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    Optional<Product> findProductById(Long id);

    void addProduct(Product product);

    void deleteProduct(Long id);

    void updateProduct(Product product);

    List<Product> findProductByCategoryId(Long aLong);

    List<Category> findAllCategories();

    Optional<Category> findCategoryById(Long id);

    boolean addCategory(String category);

    Category deleteCategory(Category category);

    List<Product> getProductsUsingFilters(List<Long> categoryId, String productName, Integer minPrice, Integer maxPrice);
}
