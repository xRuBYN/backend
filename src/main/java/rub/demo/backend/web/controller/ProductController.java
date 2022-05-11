package rub.demo.backend.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rub.demo.backend.domain.dto.ProductRequest;
import rub.demo.backend.domain.model.Product;
import rub.demo.backend.service.ProductService;
@RestController
@AllArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService service;
    @GetMapping("show/{id}")
    public ResponseEntity<ProductRequest> showProductById(@PathVariable(value = "id") Long id) {
        ProductRequest product = service.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("{id}/{email}")
    public void addProductToBinUser(@PathVariable(value = "id") Long id, @PathVariable(value = "email") String email) {
        service.addProductFromUser(id,email);
    }

    @PutMapping("edit/{id}")
    public void editProduct(@PathVariable(value = "id") Long id, @RequestBody ProductRequest productRequest) {
        service.edit(id,productRequest);
    }
}
