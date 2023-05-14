package com.upc.modelhouse.security.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResource {
    private String email;
    private String password;
}