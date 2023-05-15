package com.upc.modelhouse.security.service;

import com.upc.modelhouse.security.domain.model.entity.Account;
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

    public Account login(AuthCredentialsResource credentials) {
        String email = credentials.getEmailAddress();
        String password = credentials.getPassword();

        Account account = userRepository.findByEmailAddress(email);
        if (account == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (!account.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        return account;
    }

    @Override
    public Account register(AuthCredentialsResource credentialsResource) {
        Account registeredAccount = new Account();
        registeredAccount.setEmailAddress(credentialsResource.getEmailAddress());
        registeredAccount.setPassword(encoder.encode(credentialsResource.getPassword()));
        registeredAccount = userRepository.save(registeredAccount);
        return registeredAccount;
    }
}
