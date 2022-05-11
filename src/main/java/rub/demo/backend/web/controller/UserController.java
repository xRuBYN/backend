package rub.demo.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rub.demo.backend.domain.dto.*;
import rub.demo.backend.service.ProductService;
import rub.demo.backend.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    @Autowired
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(userService.login(authRequest));
    }


    @PutMapping("edit/{email}")
    public void edit(@RequestBody UserRequest userRequest, @PathVariable(name = "email") String email) {
        userService.edit(userRequest,email);
    }

    @GetMapping("bin/{id}")
    public ResponseEntity<List<ProductFromUser>> viewProduct(@PathVariable(name = "id") Long id) {
        List<ProductFromUser> list = productService.getProductFromUser(id);
        return ResponseEntity.ok(list);
    }


}
