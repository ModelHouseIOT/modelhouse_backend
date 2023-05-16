package com.upc.modelhouse.security.service;

import com.upc.modelhouse.security.domain.model.entity.Account;
import com.upc.modelhouse.security.domain.model.entity.UserProfile;
import com.upc.modelhouse.security.domain.persistence.UserProfileRepository;
import com.upc.modelhouse.security.domain.persistence.UserRepository;
import com.upc.modelhouse.security.domain.service.UserProfileService;
import com.upc.modelhouse.shared.exception.ResourceNotFoundException;
import com.upc.modelhouse.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository  userRepository;
    private final Validator validator;
    private static final String ENTITY = "UserProfile";
    @Override
    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile findByAccountId(Long accountId) {
        return userProfileRepository.findUserProfileById(accountId);
    }

    @Override
    public UserProfile create(UserProfile userProfile) {
        //Account account =
        Set<ConstraintViolation<UserProfile>> violations = validator.validate(userProfile);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update(Long id, UserProfile userProfile) {
        Set<ConstraintViolation<UserProfile>> violations = validator.validate(userProfile);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return userProfileRepository.findById(id).map(profile ->
                        userProfileRepository.save(profile.withFirstName(profile.getFirstName())
                                .withImage(profile.getImage())
                                .withLastName(profile.getLastName())
                                .withGender(profile.getGender())
                                .withPhoneNumber(profile.getPhoneNumber())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
