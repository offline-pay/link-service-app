package com.offlinepay.link.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @NotNull

    Prefix prefix;

    @NotNull
    @Size(min = 1, max = 50, message
            = "First Name should be between 1 to 50 char")
    String firstName;

    @NotNull
    @Size(min = 1, max = 50, message
            = "Family Name should be between 1 to 50 char")
    String familyName;

    @NotNull @Email
    @Size(min = 3, max = 70, message
            = "Email should be between 1 to 50 char")
    String email;
}
