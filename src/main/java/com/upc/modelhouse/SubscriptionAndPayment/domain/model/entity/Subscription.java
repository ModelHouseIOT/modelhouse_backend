package com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity;

import com.upc.modelhouse.security.domain.model.entity.Account;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date activatedAt;
    private Date updatedAt;
    private Boolean activated;
    private Boolean autoRenewal;

    @OneToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
