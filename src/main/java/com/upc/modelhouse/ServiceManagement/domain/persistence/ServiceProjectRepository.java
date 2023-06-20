package com.upc.modelhouse.ServiceManagement.domain.persistence;

import com.upc.modelhouse.ServiceManagement.domain.model.entity.ServiceProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProjectRepository extends JpaRepository<ServiceProject, Long> {
    ServiceProject findServiceProjectById(Long id);
    List<ServiceProject> findAll();
    ServiceProject findByProposalId(Long id);
}
