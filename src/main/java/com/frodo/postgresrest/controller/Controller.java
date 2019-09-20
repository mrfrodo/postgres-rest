package com.frodo.postgresrest.controller;

import com.frodo.postgresrest.domain.Customer;
import com.frodo.postgresrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
