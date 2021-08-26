package ru.geekbrains.mvc.util;

import ru.geekbrains.mvc.domain.Product;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductUtil {
    private ProductUtil() {
    }

    public static List<Product> getFilteredProducts(Collection<Product> products, Integer minPrice, Integer maxPrice) {
        int max = maxPrice == null ? Integer.MAX_VALUE : maxPrice;
        int min = minPrice == null ? 0 : minPrice;
        return products.stream().filter(product -> product.getCost() >= min && product.getCost() <= max)
                .collect(Collectors.toList());
    }
}
