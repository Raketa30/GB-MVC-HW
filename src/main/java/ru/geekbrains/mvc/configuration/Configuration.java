package ru.geekbrains.mvc.configuration;


import org.springframework.context.annotation.Bean;
import ru.geekbrains.mvc.dao.CategoryDB;
import ru.geekbrains.mvc.dao.ProductDB;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ProductDB getProductDBConnection() {
        return new ProductDB();
    }

    @Bean
    public CategoryDB getCategoryDBConnection() {
        return new CategoryDB();
    }
}
