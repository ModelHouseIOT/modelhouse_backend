package com.upc.modelhouse.security.resource.Project;

import com.upc.modelhouse.security.resource.BusinessProfile.BusinessProfileDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import javax.persistence.Entity;

@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private String title;
    private String description;
    private String image;
    private BusinessProfileDto businessProfileDto;
}
