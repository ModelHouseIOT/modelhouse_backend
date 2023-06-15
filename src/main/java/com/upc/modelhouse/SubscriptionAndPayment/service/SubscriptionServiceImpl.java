package com.upc.modelhouse.SubscriptionAndPayment.service;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Subscription;
import com.upc.modelhouse.SubscriptionAndPayment.domain.persistence.SubscriptionRepository;
import com.upc.modelhouse.SubscriptionAndPayment.domain.service.SubscriptionService;
import com.upc.modelhouse.security.domain.persistence.UserRepository;
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
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private Validator validator;

    private static final String ENTITY = "Subscription";
    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription findById(Long id) {
        return subscriptionRepository.findSubscriptionById(id);
    }

    @Override
    public Subscription findByAccountId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public Subscription create(Long userId, Subscription subscription) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(subscription);
        if(!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        Subscription subscriptionExist = subscriptionRepository.findByUserId(userId);
        if(subscriptionExist != null) {
            throw new ResourceNotFoundException("This user is already subscribed");
        }
        return userRepository.findById(userId).map(user -> {
            subscription.setUser(user);
            return subscriptionRepository.save(subscription);
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    @Override
    public Subscription update(Long id, Subscription subscription) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return subscriptionRepository.findById(id).map(subscription -> {
            subscriptionRepository.delete(subscription);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
