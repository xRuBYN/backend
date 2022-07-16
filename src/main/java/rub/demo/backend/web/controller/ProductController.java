package rub.demo.backend.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rub.demo.backend.domain.AddProductResponse;
import rub.demo.backend.domain.dto.DeleteProductResponse;
import rub.demo.backend.domain.dto.ProductRequest;
import rub.demo.backend.domain.model.Product;
import rub.demo.backend.service.AdminService;
import rub.demo.backend.service.ProductService;
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    //@Autowired
    private final ProductService service;
    private final AdminService adminService;
    @GetMapping("/show/{id}")
    public ResponseEntity<ProductRequest> showProductById(@PathVariable(value = "id") Long id) {
        ProductRequest product = service.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/{id}/{email}")
    public void addProductToBinUser(@PathVariable(value = "id") Long id, @PathVariable(value = "email") String email) {
        service.addProductFromUser(id,email);
    }

    @PutMapping("/edit/{id}")
    public void editProduct(@PathVariable(value = "id") Long id, @RequestBody ProductRequest productRequest) {
        adminService.edit(id,productRequest);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<DeleteProductResponse> deleteProductAdmin( @PathVariable(value = "id") Long id ) {
        return ResponseEntity.ok(adminService.deleteProduct(id));
    }

    @PostMapping("/add")
    public ResponseEntity<AddProductResponse> addProductAdmin(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(adminService.addProduct(productRequest));
    }
}
