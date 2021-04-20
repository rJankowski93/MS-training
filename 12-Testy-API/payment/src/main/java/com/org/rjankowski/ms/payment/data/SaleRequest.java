package com.org.rjankowski.ms.payment.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleRequest {
    Long customerId;
    Long cartId;

}
