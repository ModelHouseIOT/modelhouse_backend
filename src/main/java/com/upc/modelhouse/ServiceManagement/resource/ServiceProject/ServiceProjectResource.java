package com.upc.modelhouse.ServiceManagement.resource.ServiceProject;

import com.upc.modelhouse.ServiceManagement.resource.Proposal.ProposalDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProjectResource {
    private Long id;
    private Date scheduleAt;
    private Date estimateCompletionAt;
    private Date startedAt;
    private Boolean featured;
    private ProposalDto proposal;
}
