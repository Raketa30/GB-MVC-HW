package ru.geekbrains.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.mvc.domain.Product;
import ru.geekbrains.mvc.repository.ProductRepository;
import ru.geekbrains.mvc.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public boolean addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }
}
