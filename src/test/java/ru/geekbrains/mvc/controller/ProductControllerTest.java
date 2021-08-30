package ru.geekbrains.mvc.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.mvc.MvcApplicationTests;
import ru.geekbrains.mvc.domain.Product;
import ru.geekbrains.mvc.service.ProductService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest extends MvcApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        Product product = new Product("test", 123);
        when(productService.findAllProducts())
                .thenReturn(Collections.singletonList(product));
    }

    @Test
    @DisplayName("<- test show products page ->")
    void showProductsPage() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attribute("products", productService.findAllProducts()));
    }

    @Test
    @DisplayName("<-test add new product ->")
    void addNewProductShouldCreateNewProductInTheDatabase() {
        Product product = new Product("test2", 123);
    }

    @Test
    @DisplayName("<- test post product valid ->")
    void testNewProductPostValid() throws Exception {
        this.mockMvc.perform(post("/add")
                .param("title", "test_product")
                .param("cost", "222"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("<-test delete product by id->")
    void deleteProduct() throws Exception {
        this.mockMvc.perform(get("/delete/{id}", 1))
                .andExpect(status().is3xxRedirection());
    }

}