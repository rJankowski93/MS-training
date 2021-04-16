package com.org.rjankowski.ms.coupons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean active;
//
//    List<Address> addresses;
//
//    public Customer(String firstName, String lastName, Boolean active, List<Address> addresses) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.active = active;
//        this.addresses = addresses;
//    }
}

