package ru.geekbrains.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.mvc.domain.Category;
import ru.geekbrains.mvc.domain.Product;
import ru.geekbrains.mvc.repository.CategoryRepository;
import ru.geekbrains.mvc.repository.ProductRepository;
import ru.geekbrains.mvc.service.ProductService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository repository) {
        this.productRepository = productRepository;
        this.repository = repository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findProductByCategoryId(Long id) {
        return productRepository.findProductsByCategoryId(id);
    }

    @Override
    public List<Category> findAllCategories() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return repository.findCategoryById(id);
    }

    @Override
    @Transactional
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

    @Override
    public List<Product> getProductsUsingFilters(List<Long> categoryId, String productName, Integer minPrice, Integer maxPrice) {
        if (Objects.isNull(minPrice)) {
            minPrice = 0;
        }

        if (Objects.isNull(maxPrice)) {
            maxPrice = Integer.MAX_VALUE;
        }

        if (Objects.isNull(productName)) {
            productName = "";
        }

        if (Objects.isNull(categoryId)) {
            return productRepository.findProductsByTitleContainingAndCostBetween(productName, minPrice, maxPrice);
        }
        return productRepository.findProductsByTitleContainingAndCostBetweenAndCategoryIdIn(productName, minPrice, maxPrice, categoryId);
    }
}
