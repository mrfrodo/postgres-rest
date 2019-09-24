package com.frodo.postgresrest.controller;

import com.frodo.postgresrest.domain.CustomerDTO;
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
    public CustomerDTO saveOne(@RequestBody CustomerDTO customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public CustomerDTO deleteOne(@PathVariable Long id) {
        return customerService.delete(id);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateOne(@PathVariable Long id, @RequestBody CustomerDTO customer) throws Exception {
        CustomerDTO updated = customerService.update(id, customer);
        if (updated == null) {
            throw new Exception("customer id not found: "+customer.getId());
        }
        return  updated;
    }

    @DeleteMapping("/all")
    public String deleteAll() {
        customerService.deleteAll();
        return "all deleted ok";
    }
}
