package com.org.rjankowski.ms.payment;

import com.org.rjankowski.ms.payment.data.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("Customers")
public interface CustomerApiClient {
    @RequestMapping(method = RequestMethod.GET, path = "/customers")
    ResponseEntity<List<Customer>> getCustomers();

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable Long id);
}
