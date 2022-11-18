package com.org.rjankowski.ms.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("/")
@RequiredArgsConstructor
public class CustomerResource {
    private final CustomerRepository customerRepository;

    private final Environment environment;

    @GetMapping("/customers")
    public ResponseEntity<List> getCustomers(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName) {
        String property = environment.getProperty("server.port");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-port", property);
        if (firstName != null && lastName != null) {
            return new ResponseEntity(customerRepository.findAllByFirstNameAndLastName(firstName, lastName), httpHeaders, HttpStatus.OK);
        }
        if (firstName != null) {
            return new ResponseEntity(customerRepository.findAllByFirstName(firstName), httpHeaders, HttpStatus.OK);
        }
        if (lastName != null) {
            return new ResponseEntity(customerRepository.findAllByLastName(lastName), httpHeaders, HttpStatus.OK);
        }
        return new ResponseEntity(customerRepository.findAll(), httpHeaders, HttpStatus.OK);
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

    @DeleteMapping("customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("customers/{id}/addresses/{idAdr}")
    public ResponseEntity<List> getCustomerAdresse(@PathVariable Long id, @PathVariable Long idAdr) {
        Optional<Customer> itemById = customerRepository.findById(id);
        if (itemById.isPresent()) {
            return new ResponseEntity<List>(itemById.get().getAddresses().stream().filter(address -> address.getId() == idAdr).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("customers/{id}/addresses")
    public ResponseEntity<List> getCustomerAdresses(@PathVariable Long id) {
        Optional<Customer> itemById = customerRepository.findById(id);
        if (itemById.isPresent()) {
            return new ResponseEntity<List>(itemById.get().getAddresses(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
