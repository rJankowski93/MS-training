package com.org.rjankowski.ms.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    Long id;
    Long customerId;
    String city;
    String street;
}
