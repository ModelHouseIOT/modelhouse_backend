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

import javax.annotation.security.PermitAll;
import java.util.List;

//@SecurityRequirement(name = "acme")
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
    public List<BusinessProfileDto> getAll(){
        return mapper.listToResource(businessProfileService.findAll());
    }
    @GetMapping("user/{userId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public BusinessProfileDto getUserProfileByuser(@PathVariable("userId") Long id){
        return mapper.toResource(businessProfileService.findByAccountId(id));
    }
    @GetMapping("profile/{userId}")
    public BusinessProfileDto getUserProfileById(@PathVariable("userId") Long id){
        return mapper.toResource(businessProfileService.findById(id));
    }
    @PostMapping("{userId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public BusinessProfileDto createBusinessProfile(@PathVariable("userId") Long userId,@RequestBody CreateBusinessProfileDto resource){
        return mapper.toResource(businessProfileService.create(userId, mapper.toModel(resource)));
    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public BusinessProfileDto updateBusinessProfile(@PathVariable("id") Long id, @RequestBody UpdateBusinessProfileDto updateBusinessProfileDto){
        return mapper.toResource(businessProfileService.update(id, mapper.toModel(updateBusinessProfileDto)));
    }
}
