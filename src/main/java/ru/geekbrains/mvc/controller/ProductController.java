package ru.geekbrains.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mvc.domain.Category;
import ru.geekbrains.mvc.domain.Product;
import ru.geekbrains.mvc.service.CategoryService;
import ru.geekbrains.mvc.service.ProductService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        Collections.sort(products, Comparator.comparingLong(Product::getId));
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String title,
                             @RequestParam String cost,
                             @RequestParam String category) {
        Product product = new Product(title, Integer.parseInt(cost));
        product.setCategory(new Category(category));
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
            List<Category> categories = categoryService.findAll();
            model.addAttribute("product", productById.get());
            model.addAttribute("categories", categories);
            return "product_update";
        }
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @GetMapping("/findById")
    public String filterById(@RequestParam String id, Model model) {
        Optional<Product> productById = productService.findProductById(Long.parseLong(id));
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        if (productById.isPresent()) {
            model.addAttribute("products", Collections.singletonList(productById.get()));
        } else {
            model.addAttribute("products", Collections.emptyList());
        }
        return "products";
    }

    @GetMapping("/products/categories")
    public String showProductCategories() {
        return "categories";
    }


}
