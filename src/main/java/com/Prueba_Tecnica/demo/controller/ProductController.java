package com.Prueba_Tecnica.demo.controller;

import com.Prueba_Tecnica.demo.entity.Product;
import com.Prueba_Tecnica.demo.service.ProductService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController 
@RequestMapping ("api/product")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() { return productService.getAllProducts(); }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) { return productService.saveProduct(product); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/menos-consonantes")
    public ResponseEntity<Product> getProductsByLessConsonants() {
        return ResponseEntity.ok(productService.getProductsByLessConsonants());
    }
    
    @GetMapping("/primo-cercano")
    public ResponseEntity<Integer> getCloserPrimeLessMoney() {
        return ResponseEntity.ok(productService.getCloserPrimeLessMoney());
    }

    @GetMapping("/total-recursos")
    public ResponseEntity<Integer> sumTotalResources() {
        return ResponseEntity.ok(productService.sumTotalResources());
    }

}
