package com.frodo.postgresrest.controller;

import com.frodo.postgresrest.domain.Customer;
import com.frodo.postgresrest.domain.CustomerDTO;
import com.frodo.postgresrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public List<CustomerDTO> findAllCustomer() {
        return customerService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = "application/json; charset=UTF-8")
    public CustomerDTO findOne(@PathVariable Long id) {
        CustomerDTO byId = customerService.findById(id);
        return  byId;
    }

    @PostMapping("/")
    public Customer saveOne(@RequestBody CustomerDTO customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public Customer deleteOne(@PathVariable Long id) {
        return customerService.delete(id);
    }
}
