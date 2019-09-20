package com.frodo.postgresrest.service;

import com.frodo.postgresrest.domain.Customer;
import com.frodo.postgresrest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll() {
        List<Customer> all = customerRepository.findAll();
        return all;
    }

    public Customer findById(Long id) {
        Optional<Customer> one = customerRepository.findById(id);
        return one.get();
    }

    public Customer save(Customer customer) {
        Customer created = customerRepository.save(customer);
        return created;
    }
}
