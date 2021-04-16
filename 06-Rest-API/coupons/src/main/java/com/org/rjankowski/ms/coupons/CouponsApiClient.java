package com.org.rjankowski.ms.coupons;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "Customers")
public interface CouponsApiClient {
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    ResponseEntity<Customer> getCustomerById(@PathVariable Long id);
}
