package com.upc.modelhouse.security.api;

import com.upc.modelhouse.security.domain.service.BusinessProfileService;
import com.upc.modelhouse.security.mapping.BusinessProfileMapper;
import com.upc.modelhouse.security.resource.BusinessProfile.BusinessProfileDto;
import com.upc.modelhouse.security.resource.BusinessProfile.CreateBusinessProfileDto;
import com.upc.modelhouse.security.resource.BusinessProfile.UpdateBusinessProfileDto;
import com.upc.modelhouse.security.resource.UserProfile.CreateUserProfileDto;
import com.upc.modelhouse.security.resource.UserProfile.UpdateUserProfileDto;
import com.upc.modelhouse.security.resource.UserProfile.UserProfileDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("/api/v1/business_profile")
public class BusinessProfileController {
    private final BusinessProfileService businessProfileService;
    private final BusinessProfileMapper mapper;

    public BusinessProfileController(BusinessProfileService businessProfileService, BusinessProfileMapper mapper) {
        this.businessProfileService = businessProfileService;
        this.mapper = mapper;
    }
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<BusinessProfileDto> getAll(){
        return mapper.listToResource(businessProfileService.findAll());
    }
    @GetMapping("{accountId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public BusinessProfileDto getUserProfileByAccount(@PathVariable("accountId") Long id){
        return mapper.toResource(businessProfileService.findByAccountId(id));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public BusinessProfileDto createFavorite(@RequestBody CreateBusinessProfileDto resource){
        return mapper.toResource(businessProfileService.create(mapper.toModel(resource)));
    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public BusinessProfileDto updateUserProfile(@PathVariable("id") Long id, @RequestBody UpdateBusinessProfileDto updateBusinessProfileDto){
        return mapper.toResource(businessProfileService.update(id, mapper.toModel(updateBusinessProfileDto)));
    }
}
