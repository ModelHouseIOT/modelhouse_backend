package com.upc.modelhouse.security.mapping;

import com.upc.modelhouse.security.domain.model.entity.UserProfile;
import com.upc.modelhouse.security.resource.UserProfile.CreateUserProfileDto;
import com.upc.modelhouse.security.resource.UserProfile.UpdateUserProfileDto;
import com.upc.modelhouse.security.resource.UserProfile.UserProfileDto;
import com.upc.modelhouse.shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@AllArgsConstructor
public class UserProfileMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public UserProfileDto toResource(UserProfile model){
        return mapper.map(model, UserProfileDto.class);
    }

    public UserProfileDto toModel(UserProfileDto resource) {
        return mapper.map(resource, UserProfileDto.class);
    }

    public UserProfileDto toModel(CreateUserProfileDto resource) {
        return mapper.map(resource, UserProfileDto.class);
    }

    public UserProfileDto toModel(UpdateUserProfileDto resource) {
        return mapper.map(resource, UserProfileDto.class);
    }
}
