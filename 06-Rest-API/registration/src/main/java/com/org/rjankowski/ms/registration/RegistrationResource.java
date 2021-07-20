package com.org.rjankowski.ms.registration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegistrationResource {

    private final RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @PostMapping("/register")
    @CircuitBreaker(name = "Register", fallbackMethod = "fallbackRegister")
    public ResponseEntity registration(@RequestBody RegistrationRequest registrationRequest) {
        ResponseEntity<Customer[]> forEntity = restTemplate.getForEntity("http://Customers/customers", Customer[].class);
        for (Customer customer : forEntity.getBody()) {
            if (customer.getFirstName().equals(registrationRequest.getFirstName()) && customer.getLastName().equals(registrationRequest.getLastName())) {
                throw new RuntimeException();
            }
        }
        Customer customer = new Customer(registrationRequest.getFirstName(), registrationRequest.getLastName(), false, registrationRequest.getAddresses());
        restTemplate.postForEntity("http://Customers/customers", customer, ResponseEntity.class);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/active")
        @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "90"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "50000"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000")
    })
    public ResponseEntity active(@RequestBody ActivationRequest activationRequest) {
        ResponseEntity<Customer[]> forEntity = restTemplate.getForEntity("http://Customers/customers", Customer[].class);
        for (Customer customer : forEntity.getBody()) {
            if (customer.getFirstName().equals(activationRequest.getFirstName()) && customer.getLastName().equals(activationRequest.getLastName())) {
                customer.setActive(true);
                restTemplate.postForEntity("http://Customers/customers", customer, ResponseEntity.class);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/close")
    @HystrixCommand(fallbackMethod = "fallbackClose", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    public ResponseEntity close(@RequestBody ClosingRequest closingRequest) {
        ResponseEntity<Customer[]> forEntity = restTemplate.getForEntity("http://Customers/customers", Customer[].class);
        for (Customer customer : forEntity.getBody()) {
            if (customer.getFirstName().equals(closingRequest.getFirstName()) && customer.getLastName().equals(closingRequest.getLastName())) {
                restTemplate.delete("http://Customers/customers", customer);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/version")
    public String version() {
        return env.getProperty("custom.api.version");
    }

    List<ClosingRequest> closingRequestLis = new ArrayList<>();

    public ResponseEntity fallbackClose(@RequestBody ClosingRequest closingRequest) {
        closingRequestLis.add(closingRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity fallbackRegister(RegistrationRequest request, Throwable e) {
        System.out.println("Fallback******");
        return new ResponseEntity(HttpStatus.OK);
    }
}
