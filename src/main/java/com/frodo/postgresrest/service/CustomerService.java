package com.frodo.postgresrest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frodo.postgresrest.domain.Customer;
import com.frodo.postgresrest.domain.CustomerDTO;
import com.frodo.postgresrest.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDTO> findAll() {
        List<Customer> all = customerRepository.findAll();
        List<CustomerDTO> allDTOs = new ArrayList<>();
        for (Customer c : all) {
            CustomerDTO customerDTO = convertToDto(c);
            allDTOs.add(customerDTO);
        }
        //return all;
        return allDTOs;
    }

    public CustomerDTO findById(Long id) {
        Optional<Customer> one = customerRepository.findById(id);
        CustomerDTO customerDTO = convertToDto(one.get());
        //return one.get();
        return customerDTO;
    }

    public Customer save(Customer customer) {
        Customer created = customerRepository.save(customer);
        return created;
    }

    public Customer delete(Long id) {
        Optional<Customer> customerToDelete = customerRepository
                .findById(id);

        customerRepository.delete(customerToDelete.get());
        return customerToDelete.get();
    }

    private CustomerDTO convertToDto(Customer customer) {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        String jsonAsString = customer.getInfo();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Object o = objectMapper.readValue(jsonAsString, Object.class);
            customerDTO.setInfo(o);
        } catch (IOException e) {
            System.out.println("ERROR PARSING JSON STRING");
        }

        return customerDTO;
    }
}
