package com.org.rjankowski.ms.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Override
    @Query("SELECT distinct c FROM Customer c join fetch c.addresses")
    List<Customer> findAll();

    @Query("SELECT distinct c FROM Customer c join fetch c.addresses where c.firstName = ?1 and c.lastName = ?2")
    List<Customer> findAllByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT distinct c FROM Customer c join fetch c.addresses where c.firstName = ?1")
    List<Customer> findAllByFirstName(String firstName);

    @Query("SELECT distinct c FROM Customer c join fetch c.addresses where c.lastName = ?1")
    List<Customer> findAllByLastName(String lastName);
}
