package com.upc.modelhouse.ServiceManagement.domain.model.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ServiceProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "schedule_at")
    private Date scheduleAt;
    @Column(name = "estiamate_completion_at")
    private Date estimateCompletionAt;
    @Column(name = "started_at")
    private Date startedAt;
    @Column(name = "featured")
    private Boolean featured;

    @OneToOne
    @JoinColumn(name = "request_id")
    private Proposal proposal;

}
