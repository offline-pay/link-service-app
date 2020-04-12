package com.offlinepay.link.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @NotNull
    Prefix prefix;

    @NotNull
    String firstName;

    @NotNull
    String familyName;

    @NotNull @Email
    String email;
}
