package com.org.rjankowski.ms.customers;

import org.springframework.stereotype.Repository;

import java.util.Optional;

//change to interface with extends JpaRepository
// add new required correct methods
@Repository
public class CustomerRepository {


    public Customer findAllByFirstNameAndLastName(String firstName, String lastName) {
        throw new RuntimeException();
    }

    public Customer findAllByFirstName(String firstName) {
        throw new RuntimeException();
    }

    public Customer findAllByLastName(String lastName) {
        throw new RuntimeException();
    }

    public Customer findAll() {
        throw new RuntimeException();
    }

    public Optional<Customer> findById(Long id) {
        throw new RuntimeException();
    }

    public void saveAndFlush(Customer customer) {
        throw new RuntimeException();

    }

    public void delete(Customer customer) {
        throw new RuntimeException();
    }
}

