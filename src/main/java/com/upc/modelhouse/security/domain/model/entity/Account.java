package com.upc.modelhouse.security.domain.model.entity;

import com.upc.modelhouse.shared.domain.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "is_active")
    private Boolean isActive;
    private String role;
    private String password;
    //@OneToMany
    //private List<UserProfile> userProfiles;
}