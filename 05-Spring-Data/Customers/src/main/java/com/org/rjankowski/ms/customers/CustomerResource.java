package com.org.rjankowski.ms.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/")
@RequiredArgsConstructor
public class CustomerResource {
    private final CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List> getCustomers(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName != null && lastName != null) {
            return new ResponseEntity(customerRepository.findAllByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
        }
        if (firstName != null) {
            return new ResponseEntity(customerRepository.findAllByFirstName(firstName), HttpStatus.OK);
        }
        if (lastName != null) {
            return new ResponseEntity(customerRepository.findAllByLastName(lastName), HttpStatus.OK);
        }
        return new ResponseEntity(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> itemById = customerRepository.findById(id);
        if (itemById.isPresent()) {
            return new ResponseEntity<>(itemById.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/customers")
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        customerRepository.saveAndFlush(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/customers")
    public ResponseEntity deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("customers/{id}/addresses/{idAdr}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id, @PathVariable Long idAdr) {
        Optional<Customer> itemById = customerRepository.findById(id);
        if (itemById.isPresent()) {
            return new ResponseEntity<>(itemById.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
