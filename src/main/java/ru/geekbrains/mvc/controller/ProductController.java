package ru.geekbrains.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mvc.domain.Product;
import ru.geekbrains.mvc.service.ProductService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        List<Product> products = productService.findAll();
        Collections.sort(products, Comparator.comparingLong(Product::getId));
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String showUpdateProductPage(@PathVariable Long id, Model model) {
        Optional<Product> productById = productService.findProductById(id);
        if(productById.isPresent()) {
            model.addAttribute("product", productById.get());
            return "product_update";
        }
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

}
