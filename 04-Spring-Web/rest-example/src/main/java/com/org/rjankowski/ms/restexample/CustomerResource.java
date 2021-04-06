package com.org.rjankowski.ms.restexample;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
@RequiredArgsConstructor
public class CustomerResource {
    private final CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List> getCustomers(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName){
        if(firstName != null || lastName!= null){
            return new ResponseEntity<>(customerRepository.getListByFirstNameAndLastName(firstName,lastName), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.getList(), HttpStatus.OK);
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        try{
            Customer itemById = customerRepository.getItemById(id);
            return new ResponseEntity<>(itemById, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity createCustomer(@RequestBody Customer customer){
        customerRepository.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/customers")
    public ResponseEntity deleteCustomer(Customer customer){
        customerRepository.removeCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
