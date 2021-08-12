package ru.geekbrains.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mvc.domain.Product;
import ru.geekbrains.mvc.service.ProductService;

import java.util.List;

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
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(value = "title") String title, @RequestParam(value = "cost") String cost) {
        Product product = new Product(title, Integer.parseInt(cost));
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }


}
