package com.org.rjankowski.ms.coupons;

import lombok.*;

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
}

