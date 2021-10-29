package com.org.rjankowski.ms.restexample;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Get list of customers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "firstName", value = "First name", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "lastName", value = "Last name", dataType = "String", paramType = "query")
    })
    public ResponseEntity<List> getCustomers(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName){
        if(firstName != null || lastName!= null){
            return new ResponseEntity<>(customerRepository.getListByFirstNameAndLastName(firstName,lastName), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.getList(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get customer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Id of customer", dataType = "Long", paramType = "path")
    })
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
    @ApiOperation(value = "Add customer")
    public ResponseEntity createCustomer(@RequestBody Customer customer){
        customerRepository.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("customers/{id}")
    @ApiOperation(value = "Delete customer")
    public ResponseEntity deleteCustomer(@PathVariable Long id){
        Customer itemById = customerRepository.getItemById(id);
        customerRepository.removeCustomer(itemById);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
