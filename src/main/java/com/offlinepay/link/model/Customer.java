package com.offlinepay.link.model;

import com.googlecode.jmapper.annotations.JMap;
import com.offlinepay.link.entity.Order;
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
    String name;
    @NotNull
    String familyName;
    @NotNull @Email
    String email;

}
