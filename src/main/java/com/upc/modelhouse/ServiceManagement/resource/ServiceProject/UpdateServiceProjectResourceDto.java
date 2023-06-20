package com.upc.modelhouse.ServiceManagement.resource.ServiceProject;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateServiceProjectResourceDto {
    private Date scheduleAt;
    private Date estimateCompletionAt;
    private Date startedAt;
    private Boolean featured;
}
