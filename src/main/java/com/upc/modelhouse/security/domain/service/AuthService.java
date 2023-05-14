package com.upc.modelhouse.security.domain.service;

import com.upc.modelhouse.security.domain.model.entity.User;
import com.upc.modelhouse.security.resource.AuthCredentialsResource;

public interface AuthService {
    User login (AuthCredentialsResource credentials);
    User register(AuthCredentialsResource credentialsResource);
}
