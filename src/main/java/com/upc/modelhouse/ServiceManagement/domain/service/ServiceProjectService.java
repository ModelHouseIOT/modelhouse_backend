package com.upc.modelhouse.ServiceManagement.domain.service;

import com.upc.modelhouse.ServiceManagement.domain.model.entity.ServiceProject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceProjectService {
    List<ServiceProject> getAll();
    ServiceProject findByRequestId(Long id);
    ServiceProject create(Long proposalId, ServiceProject serviceProject);
    ResponseEntity<?> delete(Long id);
    ServiceProject update(Long id, ServiceProject proposal);
}
