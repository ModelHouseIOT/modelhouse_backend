package com.upc.modelhouse.security.service;

import com.upc.modelhouse.security.domain.model.entity.User;
import com.upc.modelhouse.security.domain.persistence.UserRepository;
import com.upc.modelhouse.security.domain.service.AuthService;
import com.upc.modelhouse.security.resource.AuthCredentialsResource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User login(AuthCredentialsResource credentials) {
        String email = credentials.getEmail();
        String password = credentials.getPassword();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        return user;
    }

    @Override
    public User register(AuthCredentialsResource credentialsResource) {
        User registeredUser = new User();
        registeredUser.setEmail(credentialsResource.getEmail());
        registeredUser.setPassword(encoder.encode(credentialsResource.getPassword()));
        registeredUser = userRepository.save(registeredUser);
        return registeredUser;
    }

}
