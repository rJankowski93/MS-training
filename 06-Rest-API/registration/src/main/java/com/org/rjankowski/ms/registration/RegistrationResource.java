package com.org.rjankowski.ms.registration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegistrationResource {

    private final RestTemplate restTemplate;

    List<RegistrationRequest> registrationRequests = new ArrayList<>();
    List<ClosingRequest> closingRequests = new ArrayList<>();


    @Autowired
    private Environment env;

    @PostMapping("/register")
    @HystrixCommand(fallbackMethod = "fallbackRegistration")
    public ResponseEntity registration(@RequestBody RegistrationRequest registrationRequest) {
        ResponseEntity<Customer[]> forEntity = restTemplate.getForEntity("http://localhost:8081/customers", Customer[].class);
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
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "90"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "50000"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000")
    })
    public ResponseEntity active(@RequestBody ActivationRequest activationRequest) {
        ResponseEntity<Customer[]> forEntity = restTemplate.getForEntity("http://localhost:8081/customers", Customer[].class);
        for (Customer customer : forEntity.getBody()) {
            if (customer.getFirstName().equals(activationRequest.getFirstName()) && customer.getLastName().equals(activationRequest.getLastName())) {
                customer.setActive(true);
                restTemplate.postForEntity("http://Customers/customers", customer, ResponseEntity.class);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/close")
    @HystrixCommand(fallbackMethod = "fallbackClose")
    public ResponseEntity close(@RequestBody ClosingRequest closingRequest) {
        ResponseEntity<Customer[]> forEntity = restTemplate.getForEntity("http://localhost:8081/customers", Customer[].class);
        for (Customer customer : forEntity.getBody()) {
            if(customer.getFirstName().equals(closingRequest.getFirstName()) && customer.getLastName().equals(closingRequest.getLastName())){
                restTemplate.delete("http://Customers/customers" + customer.getId());
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity fallbackRegistration(@RequestBody RegistrationRequest registrationRequest) {
        registrationRequests.add(registrationRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity fallbackClose(@RequestBody ClosingRequest registrationRequest) {
        closingRequests.add(registrationRequest);
        return new ResponseEntity(HttpStatus.OK);
    }


    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedDelayTask() {
        if (registrationRequests.size() > 0) {
            RegistrationRequest registrationRequest = registrationRequests.get(0);
            registration(registrationRequest);
            registrationRequests.remove(registrationRequest);
        }
        if (closingRequests.size() > 0) {
            ClosingRequest closingRequest = closingRequests.get(0);
            close(closingRequest);
            closingRequests.remove(closingRequest);
        }
    }

    @GetMapping("/version")
    public String version(){
        return env.getProperty("custom.api.version");
    }
}
