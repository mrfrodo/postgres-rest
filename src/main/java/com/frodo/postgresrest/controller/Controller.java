package com.frodo.postgresrest.controller;

import com.frodo.postgresrest.domain.Customer;
import com.frodo.postgresrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    CustomerService customerService;

    @GetMapping("/greeting")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/")
    public List<Customer> findAllCustomer() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findOne(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PostMapping("/")
    public Customer saveOne(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public Customer deleteOne(@PathVariable Long id) {
        return customerService.delete(id);
    }
}
