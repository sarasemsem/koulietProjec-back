package com.livraison.kouliet.service;

import com.livraison.kouliet.model.Role;
import com.livraison.kouliet.model.User;
import com.livraison.kouliet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(String email, String password, Role role) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public Map<String, String> login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return Map.of("token", jwtService.generateToken(user));
    }
}
