package ru.geekbrains.mvc.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.geekbrains.mvc.MvcApplicationTests;
import ru.geekbrains.mvc.domain.Product;
import ru.geekbrains.mvc.repository.ProductRepository;
import ru.geekbrains.mvc.service.impl.ProductServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceImplTest extends MvcApplicationTests {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @Test
    @DisplayName("<-test saving product->")
    void shouldSavedProductSuccessfully() {
        Product product = new Product("test", 1);
        when(repository.save(product)).thenReturn(product);
        service.addProduct(product);
        verify(repository).save(product);
    }

    @Test
    @DisplayName("<-test deleting product->")
    void shouldDeleteProductSuccessfully() {
//        Product product = new Product(1L, "test", 1);
//        when(repository.deleteById(1L)).thenReturn(product);
//        service.deleteProduct(product);
//        verify(repository).delete(1);
    }

    @Test
    @DisplayName("<-test finding product by id->")
    void shouldFindProductByIdSuccessfully() {
        Product product = new Product("test", 1);
        when(repository.findProductById(1L)).thenReturn(Optional.of(product));
        Optional<Product> found = service.findProductById(1L);
        assertThat(found.get()).isNotNull();
        verify(repository).findProductById(1L);
    }

    @Test
    @DisplayName("<-test finding all products->")
    void shouldFindALlProducts() {
        Product product = new Product("test", 1);
        when(repository.findAll()).thenReturn(Collections.singletonList(product));
        List<Product> found = service.findAllProducts();
        assertThat(found).isNotNull();
        verify(repository).findAll();
    }
}