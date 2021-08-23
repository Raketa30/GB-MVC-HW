package ru.geekbrains.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mvc.domain.Category;
import ru.geekbrains.mvc.domain.Product;
import ru.geekbrains.mvc.service.CategoryService;
import ru.geekbrains.mvc.service.ProductService;

import java.util.*;

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
                             @RequestParam Long categoryId) {
        Optional<Category> category = categoryService.findCategoryById(categoryId);
        if (category.isPresent()) {
            Product product = new Product(title, Integer.parseInt(cost));
            product.setCategory(category.get());
            productService.addProduct(product);
        }
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
        if (productById.isPresent()) {
            List<Category> categories = categoryService.findAll();
            model.addAttribute("product", productById.get());
            model.addAttribute("categories", categories);
            return "product_update";
        }
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@ModelAttribute Product product,
                                @RequestParam Long categoryId) {
        Optional<Category> category = categoryService.findCategoryById(categoryId);
        category.ifPresent(product::setCategory);
        productService.updateProduct(product);
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

    @PostMapping("/addCategory")
    public String addNewCategory(@RequestParam String categoryName) {
        categoryService.addCategory(categoryName);
        return "redirect:/products";
    }

    @GetMapping("/categoryFilter")
    public String getFilterByCategory(Model model, @RequestParam List<Long> categoryId) {
        if (categoryId.isEmpty()) {
            return "redirect:/products";
        }

        List<Category> categories = categoryService.findAll();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < categoryId.size(); i++) {
            List<Product> productsByCategory = productService.findProductByCategoryId(categoryId.get(i));
            products.addAll(productsByCategory);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "products";
    }
}
