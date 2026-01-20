package com.next.routeMaster.controller;


import com.next.routeMaster.authDto.LoginRequest;
import com.next.routeMaster.entity.User;
import com.next.routeMaster.repository.UserRepo;
import com.next.routeMaster.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepo userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest loginRequest) {
        if (userRepository.findByUsername(loginRequest.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(loginRequest.username());
        user.setPassword(passwordEncoder.encode(loginRequest.password()));
        userRepository.save(user);
        return "User Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );
            return jwtUtil.generateToken(request.username());
        } catch (BadCredentialsException e) {
            return "Invalid username or password";
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return "logut successfull";
    }
}
