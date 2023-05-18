package com.upc.modelhouse.ServiceManagement.mapping;

import com.upc.modelhouse.security.domain.model.entity.Project;
import com.upc.modelhouse.security.resource.Project.CreateProjectDto;
import com.upc.modelhouse.security.resource.Project.ProjectDto;
import com.upc.modelhouse.security.resource.Project.UpdateProjectDto;
import com.upc.modelhouse.shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class RequestMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ProjectDto toResource(Project model){
        return mapper.map(model, ProjectDto.class);
    }
    public List<ProjectDto> listToResource(List<Project> model){
        return mapper.mapList(model, ProjectDto.class);
    }
    public Project toModel(ProjectDto resource) {
        return mapper.map(resource, Project.class);
    }

    public Project toModel(CreateProjectDto resource) {
        return mapper.map(resource, Project.class);
    }

    public Project toModel(UpdateProjectDto resource) {
        return mapper.map(resource, Project.class);
    }

}
