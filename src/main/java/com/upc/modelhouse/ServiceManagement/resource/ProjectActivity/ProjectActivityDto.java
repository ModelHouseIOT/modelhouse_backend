package com.upc.modelhouse.ServiceManagement.resource.ProjectActivity;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProjectActivityDto {
    private Long id;
    private String status;
    private String name;
    private String description;
    private Date startedAt;
    private Date completedAt;
    private Long proposal_id;
}
