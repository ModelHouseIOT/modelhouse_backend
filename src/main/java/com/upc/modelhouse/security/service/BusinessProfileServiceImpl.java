package com.upc.modelhouse.security.service;

import com.upc.modelhouse.security.domain.model.entity.BusinessProfile;
import com.upc.modelhouse.security.domain.model.entity.UserProfile;
import com.upc.modelhouse.security.domain.persistence.BusinessProfileRepository;
import com.upc.modelhouse.security.domain.persistence.UserRepository;
import com.upc.modelhouse.security.domain.service.BusinessProfileService;
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
public class BusinessProfileServiceImpl implements BusinessProfileService {
    private final BusinessProfileRepository businessProfileRepository;
    private final UserRepository userRepository;
    private final Validator validator;
    private static final String ENTITY = "BusinessProfile";
    @Override
    public List<BusinessProfile> findAll() {
        return businessProfileRepository.findAll();
    }

    @Override
    public BusinessProfile findByAccountId(Long accountId) {
        return businessProfileRepository.findBusinessProfileById(accountId);
    }

    @Override
    public BusinessProfile create(BusinessProfile businessProfile) {
        Set<ConstraintViolation<BusinessProfile>> violations = validator.validate(businessProfile);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return businessProfileRepository.save(businessProfile);
    }

    @Override
    public BusinessProfile update(Long id, BusinessProfile businessProfile) {
        Set<ConstraintViolation<BusinessProfile>> violations = validator.validate(businessProfile);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return businessProfileRepository.findById(id).map(profile ->
                        businessProfileRepository.save(profile.withName(profile.getName())
                                .withDescription(profile.getDescription())
                                .withImage(profile.getImage())
                                .withAddress(profile.getAddress())
                                .withWebSite(profile.getWebSite())
                                .withPhoneBusiness(profile.getPhoneBusiness())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
