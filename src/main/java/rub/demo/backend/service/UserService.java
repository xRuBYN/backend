package rub.demo.backend.service;

import rub.demo.backend.domain.dto.AuthRequest;
import rub.demo.backend.domain.dto.AuthResponse;
import rub.demo.backend.domain.dto.RegisterRequest;
import rub.demo.backend.domain.dto.UserRequest;
import rub.demo.backend.domain.model.User;

public interface UserService {
    void register(RegisterRequest registerRequest);
    AuthResponse login(AuthRequest authRequest);
    void edit(UserRequest userRequest,String email);
    User findByEmail(String email);
}
