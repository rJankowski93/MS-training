package com.org.rjankowski.ms.restexample;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {
    List<Customer> db = new LinkedList<>(Arrays.asList(
            new Customer(1L,"Piotr","Nowak"),
            new Customer(2L,"Adam","Kowalski"),
            new Customer(3L,"Sebastian","Czarny")
    ));

    public List<Customer> getList() {
        return db;
    }

    public List<Customer> getListByFirstNameAndLastName(String firstName, String lastName) {
        List<Customer> list = db;
        if(firstName != null){
            list = list.stream().filter(customer -> customer.getFirstName().equals(firstName)).collect(Collectors.toList());
        }
        if(lastName != null){
            list = list.stream().filter(customer -> customer.getLastName().equals(lastName)).collect(Collectors.toList());

        }
        return list;
    }

    public Customer getItemById(Long id) {
        return db.stream().filter(customer -> customer.getId().equals(id)).findFirst().get();
    }

    public void createCustomer(Customer customer){
        db.add(customer);
    }

    public void removeCustomer(Customer customer){
       db.remove(customer);
    }
}
