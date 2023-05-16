package com.upc.modelhouse.security.domain.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BusinessProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;
    private String address;
    @Column(name = "web_site")
    private String webSite;
    @Column(name = "phone_business")
    private String phoneBusiness;
    @Column(name = "foundation_date")
    private String foundationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
