package com.app.ecom_application.controller;

import com.app.ecom_application.dto.ProductRequest;
import com.app.ecom_application.dto.ProductResponse;
import com.app.ecom_application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<ProductResponse>createProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest),
                HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());

    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse>updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest){
        return productService.updateProduct(id,productRequest)
                .map(ResponseEntity :: ok)
                .orElseGet(()->ResponseEntity.notFound().build());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long id){
       boolean deleted = productService.deleteProduct(id);
       return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchProducts(keyword));

    }
}
