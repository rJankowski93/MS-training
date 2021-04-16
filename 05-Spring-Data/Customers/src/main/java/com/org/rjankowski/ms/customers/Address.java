package com.org.rjankowski.ms.customers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    Long id;
    Long customerId;
    String city;
    String street;
}
