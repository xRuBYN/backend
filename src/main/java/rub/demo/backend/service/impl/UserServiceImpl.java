package rub.demo.backend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rub.demo.backend.domain.dto.AuthRequest;
import rub.demo.backend.domain.dto.AuthResponse;
import rub.demo.backend.domain.dto.RegisterRequest;
import rub.demo.backend.domain.dto.UserRequest;
import rub.demo.backend.domain.model.Bin;
import rub.demo.backend.domain.model.User;
import rub.demo.backend.repository.BinRepository;
import rub.demo.backend.repository.UserRepository;
import rub.demo.backend.service.UserService;
import rub.demo.backend.web.security.JwtProvider;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    private final BinRepository binRepository;
    public void register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .role("USER_ROLE")
                .password(registerRequest.getPassword()).build();
        userRepository.save(user);
        Bin bin = new Bin(user);
        binRepository.save(bin);
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByEmailAndPassword(authRequest.getEmail(),authRequest.getPassword());
        //JwtProvider jwtProvider = new JwtProvider();
        return new AuthResponse(jwtProvider.generateToken(user.getEmail(), user.getName()));
    }

    @Override
    public void edit(UserRequest userRequest, String email) {
        User user = userRepository.findByEmail(email);

        if(userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if(userRequest.getName() != null) {
            user.setName(userRequest.getName());
        }
        if(userRequest.getAge() != null) {
            user.setAge(userRequest.getAge());
        }
        if(userRequest.getPassword() != null) {
            user.setPassword(userRequest.getPassword());
        }
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email);

        log.info("IN findByEmail - user: {} found by email: {}", result, email);

        return result;
    }
}
