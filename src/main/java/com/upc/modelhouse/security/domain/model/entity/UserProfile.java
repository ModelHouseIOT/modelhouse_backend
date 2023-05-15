package com.upc.modelhouse.security.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "first_name")
    private String firstName;
    private String image;
    @JoinColumn(name = "last_name")
    private String lastName;
    private String gender;
    @JoinColumn(name = "phone_number")
    private String phoneNumber;
    @JoinColumn(name = "registration_date")
    private Date registrationDate;
    @JoinColumn(name = "last_login")
    private Date lastLogin;
    @JoinColumn(name = "account_status")
    private Integer accountStatus;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
