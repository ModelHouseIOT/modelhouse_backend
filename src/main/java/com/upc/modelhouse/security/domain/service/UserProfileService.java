package com.upc.modelhouse.security.domain.service;


import com.upc.modelhouse.security.domain.model.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> findAll();
    UserProfile findByAccountId(Long accountId);
    UserProfile create(UserProfile userProfile);
    UserProfile update(Long id, UserProfile userProfile);

}
