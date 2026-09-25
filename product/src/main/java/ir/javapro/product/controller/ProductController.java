package ir.javapro.product.controller;

import ir.javapro.product.model.Product;
import ir.javapro.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("/get-all")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/get-by-id/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }
}