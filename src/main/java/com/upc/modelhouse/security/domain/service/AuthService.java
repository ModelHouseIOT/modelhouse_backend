package com.upc.modelhouse.security.domain.service;

import com.upc.modelhouse.security.domain.model.entity.Account;
import com.upc.modelhouse.security.resource.AuthCredentialsResource;

public interface AuthService {
    Account login (AuthCredentialsResource credentials);
    Account register(AuthCredentialsResource credentialsResource);
}
