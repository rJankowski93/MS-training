package com.org.rjankowski.ms.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class RegistrationResource {

    private final RestTemplate restTemplate;

    @PostMapping("/register")
    public ResponseEntity registration(@RequestBody RegistrationRequest registrationRequest){
        ResponseEntity<Customer[]> forEntity = restTemplate.getForEntity("http://Customers/customers", Customer[].class);
        for (Customer customer : forEntity.getBody()) {
            if (customer.getFirstName().equals(registrationRequest.getFirstName()) && customer.getLastName().equals(registrationRequest.getLastName())) {
                throw new RuntimeException();
            }
        }
        Customer customer = new Customer(registrationRequest.getFirstName(),registrationRequest.getLastName(),false, registrationRequest.getAddresses());
        restTemplate.postForEntity("http://Customers/customers", customer, ResponseEntity.class);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/active")
    public ResponseEntity active(@RequestBody ActivationRequest activationRequest){
        ResponseEntity<Customer[]> forEntity = restTemplate.getForEntity("http://Customers/customers", Customer[].class);
        for (Customer customer : forEntity.getBody()) {
            if(customer.getFirstName().equals(activationRequest.getFirstName()) && customer.getLastName().equals(activationRequest.getLastName())){
                customer.setActive(true);
                restTemplate.postForEntity("http://Customers/customers", customer, ResponseEntity.class);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/close")
    public ResponseEntity close(@RequestBody ClosingRequest closingRequest){
        ResponseEntity<Customer[]> forEntity = restTemplate.getForEntity("http://Customers/customers", Customer[].class);
        for (Customer customer : forEntity.getBody()) {
            if(customer.getFirstName().equals(closingRequest.getFirstName()) && customer.getLastName().equals(closingRequest.getLastName())){
                restTemplate.delete("http://Customers/customers" + customer.getId());
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
