package com.upc.modelhouse.ServiceManagement.service;

import com.upc.modelhouse.ServiceManagement.domain.model.entity.ServiceProject;
import com.upc.modelhouse.ServiceManagement.domain.persistence.ProposalRepository;
import com.upc.modelhouse.ServiceManagement.domain.persistence.ServiceProjectRepository;
import com.upc.modelhouse.ServiceManagement.domain.service.ServiceProjectService;
import com.upc.modelhouse.shared.exception.ResourceNotFoundException;
import com.upc.modelhouse.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ServiceProjectServiceImpl implements ServiceProjectService {
    private final ServiceProjectRepository serviceProjectRepository;
    private final ProposalRepository proposalRepository;
    private final Validator validator;
    private static final String ENTITY = "ServiceProject";

    @Override
    public List<ServiceProject> getAll() {
        return serviceProjectRepository.findAll();
    }

    @Override
    public ServiceProject findByRequestId(Long id) {
        return serviceProjectRepository.findByProposalId(id);
    }

    @Override
    public ServiceProject create(Long proposalId, ServiceProject serviceProject) {
        Set<ConstraintViolation<ServiceProject>> violations = validator.validate(serviceProject);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        serviceProject.setScheduleAt(new Date());

        ServiceProject serviceProjectExist = serviceProjectRepository.findByProposalId(proposalId);
        if(serviceProjectExist != null)
            throw new ResourceNotFoundException("ServiceProject is exist");
        return proposalRepository.findById(proposalId).map(proposal -> {
            serviceProject.setProposal(proposal);
            return serviceProjectRepository.save(serviceProject);
        }).orElseThrow(() -> new ResourceNotFoundException("Proposal", proposalId));
    }

    @Override
    public ResponseEntity<?> delete(Long  id) {
        return serviceProjectRepository.findById(id).map(serviceProject -> {
            serviceProjectRepository.delete(serviceProject);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ServiceProject update(Long id, ServiceProject proposal) {
        return null;
    }
}
