package com.upc.modelhouse.ServiceManagement.service;

import com.upc.modelhouse.ServiceManagement.domain.model.entity.ProjectActivity;
import com.upc.modelhouse.ServiceManagement.domain.persistence.ProjectActivityRepository;
import com.upc.modelhouse.ServiceManagement.domain.persistence.ProposalRepository;
import com.upc.modelhouse.ServiceManagement.domain.service.ProjectActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectActivityServiceImpl implements ProjectActivityService {
    private final ProposalRepository proposalRepository;
    private final ProjectActivityRepository projectActivityRepository;
    private final Validator validator;

    @Override
    public List<ProjectActivity> findAllProposalId(Long id) {
        return null;
    }

    @Override
    public ProjectActivity create(Long proposalId, ProjectActivity projectActivity) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }

    @Override
    public ProjectActivity update(Long id, ProjectActivity proposal) {
        return null;
    }
}
