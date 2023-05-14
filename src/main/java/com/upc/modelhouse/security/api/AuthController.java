package com.upc.modelhouse.security.api;

import com.upc.modelhouse.security.domain.persistence.UserRepository;
import com.upc.modelhouse.security.domain.service.AuthService;
import com.upc.modelhouse.security.resource.AuthCredentialsResource;
import com.upc.modelhouse.security.resource.JwtResponse;
import com.upc.modelhouse.security.resource.ResponseErrorResource;
import com.upc.modelhouse.security.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    private AuthenticationManager manager;
    @Autowired
    private UserRepository userRepository;
    private static final String statusBody = "User already exists";

    @PostMapping("/login")
    @Operation(summary = "Login", tags = {"Auth"})
    public ResponseEntity<?> login(@Valid @RequestBody AuthCredentialsResource user) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    @Operation(summary = "Register", tags = {"Auth"})
    public ResponseEntity<?> register(@Valid @RequestBody AuthCredentialsResource credentials) {

        ResponseErrorResource errorResource = new ResponseErrorResource();
        errorResource.setMessage(statusBody);

        if(userRepository.findByEmail(credentials.getEmail()) != null) {
            return ResponseEntity.badRequest().body(errorResource);
        }

        return ResponseEntity.ok(authService.register(credentials));
    }
}
