package ru.geekbrains.mvc.repository.impl;

import org.springframework.stereotype.Repository;
import ru.geekbrains.mvc.domain.Product;
import ru.geekbrains.mvc.repository.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> products;

    public ProductRepositoryImpl() {
        this.products = new ArrayList<>();
    }

    @PostConstruct
    private void init() {
        save(new Product("test1", 223));
        save(new Product("test2", 2223));
        save(new Product("test3", 23423));
        save(new Product("test4", 234223));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Optional<Product> findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean save(Product product) {
        if(products.isEmpty()) {
            product.setId(1);
            return products.add(product);
        }

        int id = products.get(products.size() - 1).getId() + 1;
        product.setId(id);
        return products.add(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        return products.removeIf(p -> p.getId() == id);
    }
}
