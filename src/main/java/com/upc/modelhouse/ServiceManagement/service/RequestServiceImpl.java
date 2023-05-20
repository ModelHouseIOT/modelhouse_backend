package com.upc.modelhouse.ServiceManagement.service;

import com.upc.modelhouse.ServiceManagement.domain.model.entity.Request;
import com.upc.modelhouse.ServiceManagement.domain.persistence.RequestRepository;
import com.upc.modelhouse.ServiceManagement.domain.service.RequestService;
import com.upc.modelhouse.security.domain.persistence.BusinessProfileRepository;
import com.upc.modelhouse.security.domain.persistence.UserProfileRepository;
import com.upc.modelhouse.shared.exception.ResourceNotFoundException;
import com.upc.modelhouse.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final BusinessProfileRepository businessProfileRepository;
    private final UserProfileRepository userProfileRepository;
    private final Validator validator;
    private static final String ENTITY = "Request";
    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> findAllBusinessProfileId(Long id) {
        return requestRepository.findAllByBusinessProfileId(id);
    }

    @Override
    public List<Request> findAllUserProfileId(Long id) {
        return requestRepository.findAllByUserProfileId(id);
    }

    @Override
    public Request create(Long userId, Long businessId, Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return businessProfileRepository.findById(businessId).map(businessProfile -> {
            request.setBusinessProfile(businessProfile);
            request.setUserProfile(userProfileRepository.findUserProfileById(userId));
            return requestRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("BusinessProfile", businessId));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return requestRepository.findById(id).map(project -> {
            requestRepository.delete(project);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Request changeStatus(Long id, Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return requestRepository.findById(id).map(change ->
                        requestRepository.save(change.withStatus(request.getStatus())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY , id));
    }
}
