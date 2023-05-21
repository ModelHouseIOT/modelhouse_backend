package com.upc.modelhouse.ServiceManagement.resource.Proposal;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDto {
    private Long id;
    private Date proposalDate;
    private String description;
    private Float price;
    private String status;
    private Boolean isResponse;
    private Date responseDate;
    private Long request_id;
}
