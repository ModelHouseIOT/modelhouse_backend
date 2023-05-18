package com.upc.modelhouse.ServiceManagement.domain.persistence;

import com.upc.modelhouse.ServiceManagement.domain.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findRequestById(Long id);
    List<Request> findAll();
    List<Request> findAllByUserProfileId(Long id);
    List<Request> findAllByBusinessProfileId(Long id);
}
