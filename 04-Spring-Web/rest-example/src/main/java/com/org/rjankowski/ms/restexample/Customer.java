package com.org.rjankowski.ms.restexample;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
}
